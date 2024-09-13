/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fpc
 */
public class User {
    private String nome;
    private String senha;
    private int id;
    private String dataCadastro;
    private String tipo;
    private boolean ativo;
    
    public User(String nome, String senha, String tipo, String dtCad){
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.ativo = false;
        this.dataCadastro = dtCad;
    }
    
    public User(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        this.ativo = false;
    }

    public User(){
        this.nome = null;
        this.senha = null;
        this.tipo = null;
        this.ativo = false;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getDtCadastro(){
        return dataCadastro;
    }
    
    public void setDtCadastro(String dtCad){
        this.dataCadastro = dtCad;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(boolean ativado){
        this.ativo = ativado;
    }
}
