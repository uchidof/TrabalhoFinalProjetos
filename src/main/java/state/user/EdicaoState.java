/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import presenter.UserPresenter;
import command.user.IUserCommand;
import command.user.EditarCommand;
import command.user.ExcluirCommand;
import command.user.FecharCommand;
import view.UserView;


/**
 *
 * @author Fpc
 */
public class EdicaoState extends UserState{
    private IUserCommand comando = null;
    private String nome;
    private String senha;
    
    public EdicaoState (UserPresenter presenter, String nome, String senha){
        super(presenter);
        this.nome = nome;
        this.senha = senha;
        configurarTela();
    }
    
    public void salvar(){
        String nome = presenter.getView().getTextFieldNome().getText();
        String senha = presenter.getView().getTextFieldSenha().getText();
        String confirmaSenha = presenter.getView().getTextFieldConfirmaSenha().getText();
        
        if(!senha.equals(confirmaSenha))
            throw new RuntimeException("Senhas não conferem!");
            
        comando = new EditarCommand(nome, senha);
        comando.executar();
        
        presenter.exibirMensagem("Senha alterada com sucesso!!","Sucesso", 2);
    }
    
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executar();
    }
    
    @Override
    public void configurarTela(){
        UserView view = presenter.getView();
        
        view.setTitle("Edição de Usuário");
        
        view.getTextFieldNome().setText(nome);
        view.getTextFieldSenha().setText(senha);
        
        view.getTextFieldConfirmaSenha().setVisible(true);
        view.getTextFieldConfirmaSenha().setVisible(true);
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.getBtnSalvar().setVisible(true);
        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldSenha().setEnabled(true);
        
        for (ActionListener listener : view.getBtnSalvar().getActionListeners()) {
            view.getBtnSalvar().removeActionListener(listener);
        }
        
        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    presenter.exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        view.getTextFieldNotificacoesEnviadas().setVisible(false);
        view.getTextFieldNotificacoesLidas().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(true);
        view.getLabelNotificacoesEnviadas().setVisible(false);
        view.getLabelNotificacoesLidas().setVisible(false);
        
        view.setVisible(true);
    }

}
