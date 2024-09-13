/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalhofinalprojetos;

import persistence.InicializaBanco;
import presenter.LoginPresenter;

/**
 *
 * @author Fpc
 */
public class Main {
    public static void main(String[] args) {    
        InicializaBanco.inicializar();
            //Inicia o BD
        
        LoginPresenter presenter = new LoginPresenter();
    }
    
}
