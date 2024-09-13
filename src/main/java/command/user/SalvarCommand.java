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
public class SalvarCommand implements IUserCommand{
    private GerenciaUserService service;
    private String nome;
    private String senha;
    
    public SalvarCommand(String nome, String senha){
        service = new GerenciaUserService();
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public void executar(){
        service.inserir(nome, senha);
    }
}
