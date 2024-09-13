/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import state.user.InclusaoState;
import state.user.UserState;
import view.UserView;

/**
 *
 * @author Fpc
 */
public class UserPresenter {
    private UserState state;
    private UserView view;
    
    public UserPresenter() {
        this.view = new UserView();
        this.state = new InclusaoState(this);
    }
    
    public UserPresenter(UserState estado) {
        this.view = new UserView();
        this.state = estado;
    }
    public UserState getEstado() {
        return state;
    }

    public void setEstado(UserState estado) {
        this.state = estado;
    } 

    public UserView getView() {
        return view;
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
    public int exibirConfirmacao(String mensagem, String titulo, int type){
        int retorno = JOptionPane.showConfirmDialog(this.view, mensagem, titulo,type);
        return retorno;
    }
}
