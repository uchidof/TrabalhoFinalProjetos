/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import model.Notificacao;
import presenter.UserPresenter;
import service.GerenciaUserService;
import service.GerenciaNotificacaoService;
import command.user.IUserCommand;
import command.user.ExcluirCommand;
import command.user.FecharCommand;
import view.UserView;

/**
 *
 * @author Fpc
 */
public class VisualizacaoState extends UserState{
    private IUserCommand comando = null;
    private String nome;
    private String senha;
    private GerenciaUserService serviceUser;
    private GerenciaNotificacaoService serviceNotificacao;
    
    public VisualizacaoState (UserPresenter presenter, String nome, String senha){
        super(presenter);
        this.nome = nome;
        this.senha = senha;
        this.serviceUser = new GerenciaUserService();
        this.serviceNotificacao = new GerenciaNotificacaoService();
        configurarTela();
    }
    
    @Override
    public void excluir(){
        String nome = presenter.getView().getTextFieldNome().getText();
        int confirm = presenter.exibirConfirmacao("Deseja realmente excluir " + nome.toUpperCase() + "?", "Excluir", 0);
        
        if(confirm == JOptionPane.YES_OPTION){
            comando = new ExcluirCommand(nome);
            comando.executar();
            presenter.exibirMensagem("Excluido com sucesso", "Exclusão", 1);
        }
        presenter.setEstado(new InclusaoState(presenter));
        
    }
    
    @Override
    public void editar(){
        presenter.setEstado(new EdicaoState(presenter, nome, senha));
    }
  
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executar();
    }
    
    @Override
    public void configurarTela(){
        int id = serviceUser.consultar(nome).getId();
        int enviada = serviceNotificacao.consultarTodasPorUsuario(id).size();
        int lidas = 0;
        List<Notificacao> msgLidas = serviceNotificacao.consultarTodasLidas(id);
        if(msgLidas != null){
            lidas = msgLidas.size();
        }
        UserView view = presenter.getView();

        view.setTitle("Visualização de Usuário");
        
        view.getTextFieldNotificacoesEnviadas().setText(Integer.toString(enviada));
        view.getTextFieldNotificacoesLidas().setText(Integer.toString(lidas));
        view.getTextFieldNome().setText(nome);
        view.getTextFieldSenha().setText(senha);
        
        view.getTextFieldConfirmaSenha().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(false);

        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldSenha().setEnabled(false);
  

        for (ActionListener listener : view.getBtnExcluir().getActionListeners()) {
            view.getBtnExcluir().removeActionListener(listener);
        }
        
        view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    excluir();
                }catch(Exception e){
                    presenter.exibirMensagem(e.getMessage(), "Erro", 1);
                }
            }
        });
        
        view.getBtnEditar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    editar();
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
        
        
        view.getTextFieldNotificacoesEnviadas().setVisible(true);
        view.getTextFieldNotificacoesLidas().setVisible(true);
        view.getLabelNotificacoesEnviadas().setVisible(true);
        view.getTextFieldNotificacoesEnviadas().setEnabled(false);
        view.getTextFieldNotificacoesLidas().setEnabled(false);
        view.getLabelNotificacoesLidas().setVisible(true);
        view.getBtnExcluir().setVisible(true);
        view.getBtnEditar().setVisible(true);
        view.getBtnSalvar().setVisible(false);
        
        
        view.setVisible(true);
    }
    
}
