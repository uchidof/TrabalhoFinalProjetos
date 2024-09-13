/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import javax.swing.JOptionPane;

import state.notificacao.InclusaoState;
import state.notificacao.NotificacaoState;
import view.NotificacaoView;


/**
 *
 * @author Fpc
 */
public class NotificacaoPresenter {
    private NotificacaoState state;
    private NotificacaoView view;

    public NotificacaoPresenter() {
        this.view = new NotificacaoView();
        this.state = new InclusaoState(this);
        configurarTela();
    }

    public NotificacaoView getView() {
        return view;
    }
    
    private void configurarTela(){
    
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
