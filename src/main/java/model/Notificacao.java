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
public class Notificacao {
    private String titulo;
    private String mensagem;
    private boolean mensagemLida;
    private int id;
    
    public Notificacao(int id, String titulo, String mensagem){
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.id = id;
    }
    
    public Notificacao(String titulo, String mensagem){
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public Notificacao(){}
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMensagemLida() {
        return mensagemLida;
    }

    public void setMensagemLida(boolean msgLida) {
        this.mensagemLida = msgLida;
    }


}
