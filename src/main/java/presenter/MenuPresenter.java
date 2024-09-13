/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.User;
import view.MenuView;

/**
 *
 * @author Fpc
 */
public class MenuPresenter {
    MenuView view;
    User userLogado;
    public static MenuPresenter instancia = null;
    
    private MenuPresenter(User user){
        this.view = new MenuView();
        this.userLogado = user;
        configurar();
    }
    
    public static MenuPresenter getInstancia(User user){
        if(instancia == null){
            instancia = new MenuPresenter(user);
        }
        return instancia;
    }
    
    public static MenuPresenter getInstancia(){
        return instancia;
    }
    
    public MenuView getView() {
        return view;
    }
    
        private void configurar(){
        this.view.setVisible(false);
        
        if(!"admin".equalsIgnoreCase(userLogado.getTipo())){
            view.getTextUsuarioMenuBar().setVisible(false);
            view.getTextNotificacoesMenuBar().setVisible(false);
            view.getMenuVisualizarTodasNotificacoes().setVisible(false);
            view.getBtnNotificacoes().setVisible(true); 
            view.getBtnNotificacoes().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                VisualizaTodasNotificacoesPresenter presenter = new VisualizaTodasNotificacoesPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        }else{
            view.getMenuVisualizarTodasNotificacoes().setVisible(true);
            view.getBtnNotificacoes().setVisible(false);
        }

        view.getLabelUsuarioPrivilegio().setText("usuario: " + userLogado.getNome() + " | privilégio: " + userLogado.getTipo());


        view.getMenuNovoUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                UserPresenter presenter = new UserPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        


        view.getMenuNovaNotificacao().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                NotificacaoPresenter presenter = new NotificacaoPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        view.getMenuConta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ConfiguracaoPresenter presenter = new ConfiguracaoPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        
        view.getMenuVisualizarTodosUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ListaUserPresenter presenter = new ListaUserPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        view.getMenuAutorizacaoPendente().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ListaInactivePresenter presenter = new ListaInactivePresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        this.view.setVisible(true);
    }

}
