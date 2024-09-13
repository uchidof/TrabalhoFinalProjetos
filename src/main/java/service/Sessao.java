/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.User;

/**
 *
 * @author Fpc
 */
public class Sessao {
    private static Sessao instancia;
    private User userLogado;
    
    private Sessao(){
    }
    
    public static Sessao getInstancia(){    //Singleton da Sessao de um usuario logado
        if (instancia == null) {
            instancia = new Sessao();
        }
        return instancia;
    }
    
    public User getUserLogado(){
        return userLogado;
    }
    
    public void setUserLogado(User user){
        this.userLogado = user;
    }
    
    public void fecharSessao(){
        this.userLogado = null;
    }
}
