/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import service.GerenciaUserService;
import view.CadastroView;

/**
 *
 * @author Fpc
 */
public class CadastroPresenter {
    private CadastroView view;
    private GerenciaUserService gerenciaUser;
    
    public CadastroPresenter(){
        this.view = new CadastroView();
        this.gerenciaUser = new GerenciaUserService();
        configurar();
    }
    
    private void cadastrar(){
        String nome = view.getTextFieldNome().getText();
        String senha = view.getTextFieldSenha().getText();
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();
        
        if ("".equals(nome) || "".equals(senha) || "".equals(confirmaSenha)) {
            throw new RuntimeException("Preencha os campos vazios.");
        }else if (!senha.equals(confirmaSenha)) {
            throw new RuntimeException("Senha com valores diferentes");         
        }else{
            gerenciaUser.inserir(nome, senha);
        }
        this.view.setVisible(false);
    }
    
    private void configurar(){
        view.getBtnCadastrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    cadastrar();
                }catch(Exception e){
                    System.out.println("Erro no cadastro!!!");
                    exibeMensagem(e.getMessage(), "Erro no Cadastro", 0);
                }
            }
        });
        
        view.setVisible(true);
    }
    
    private void exibeMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
