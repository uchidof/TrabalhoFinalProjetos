/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.notificacao;

import presenter.NotificacaoPresenter;

/**
 *
 * @author Fpc
 */
public abstract class NotificacaoState {
    protected NotificacaoPresenter presenter;
    
    public NotificacaoState(NotificacaoPresenter presenter){
        this.presenter = presenter;
    }
    
    public void salvar(){
        throw new RuntimeException("Esta operação não pode ser efetuada.");
    }
    public void editar(){
        throw new RuntimeException("Esta operação não pode ser efetuada.");
    }
    public void excluir(){
        throw new RuntimeException("Esta operação não pode ser efetuada.");
    }
    public void fechar(){
        throw new RuntimeException("Esta operação não pode ser efetuada.");
    }
    
    public abstract void configurarTela();
}
