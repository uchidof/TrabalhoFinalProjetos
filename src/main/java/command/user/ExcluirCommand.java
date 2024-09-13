/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import service.GerenciaUserService;

/**
 *
 * @author Fpc
 */
public class ExcluirCommand implements IUserCommand{
    private GerenciaUserService service;
    private String nome;
    
    public ExcluirCommand(String nome){
        service = new GerenciaUserService();
        this.nome = nome;
    }
    
    @Override
    public void executar(){
        service.excluir(nome);
    }
}
