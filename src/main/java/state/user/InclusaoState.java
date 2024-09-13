/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presenter.UserPresenter;
import command.user.IUserCommand;
import command.user.FecharCommand;
import command.user.SalvarCommand;
import view.UserView;



/**
 *
 * @author Fpc
 */
public class InclusaoState extends UserState{
    private IUserCommand comando = null;
    UserView view;
    
    public InclusaoState(UserPresenter presenter){
        super(presenter);
        this.view = presenter.getView();
        configurarTela();
    }
    
    @Override
    public void salvar(){
        String nome = view.getTextFieldNome().getText();
        String senha = view.getTextFieldSenha().getText();
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();
        
        if(!senha.equals(confirmaSenha))
            throw new RuntimeException("Senhas não conferem!");
        
        comando = new SalvarCommand(nome,senha);
        comando.executar();
        
        view.getTextFieldNome().setText("");
        view.getTextFieldSenha().setText("");
        view.getTextFieldConfirmaSenha().setText("");
        
        presenter.setEstado(new VisualizacaoState(presenter, nome, senha));

    }
    
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executar();
    }
    
    @Override
    public void configurarTela(){
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.setTitle("Cadastro de Usuário");
        
        
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
        view.getLabelNotificacoesEnviadas().setVisible(false);
        view.getLabelNotificacoesLidas().setVisible(false);
        view.setVisible(true);
    }
}
