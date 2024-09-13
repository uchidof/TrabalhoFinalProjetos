/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import model.User;
import service.AutenticaService;
import view.LoginView;


/**
 *
 * @author Fpc
 */
public class LoginPresenter {
    private LoginView view;
    private AutenticaService service;
    
    public LoginPresenter(){
        this.view = new LoginView();    
            //Aciona a view de Login
        this.service = new AutenticaService();
            //Aciona a service de Autenticacao
        configurarLogin();
    }
    
    private void configurarLogin(){ 
    //Metodo que configura a TELA de Login
    
        this.view.setVisible(false);
        
        view.getBtnEntrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    autenticarLogin();  //Autenticacao do Login
                }catch(Exception e){
                    mostrarMensagem(e.getMessage(), "Erro em configurar  Login", 0);
                }
            }
        });
        
        view.getLabelTextCadastro().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                //Se o botao de cadastro foi clicado
                try{
                    CadastroPresenter cadastro = new CadastroPresenter();
                    view.getLabelTextCadastro().setForeground(Color.green);
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void autenticarLogin(){
        /* Apenas verifica erros durante o processo inicial de Login
        */
        
        String nome = view.getTextFieldUsuario().getText();
        String senha = view.getTextFieldSenha().getText();
        User usuario = service.autenticar(nome, senha);
            //autenticar vem da Service Autentica
            //Verifica se o User consta no BD e se sua senha eh a mesma
            
        if("".equals(nome) || "".equals(senha)){
            throw new RuntimeException("Preencha o campo");
        }//Campos de Login Vazios
        
        if (usuario != null) {  
            //usuario NAO vazio = usuario autenticado
            MenuPresenter menu = MenuPresenter.getInstancia(usuario);
            this.view.setVisible(false);
            
            /*
            Uma instancia de MenuView é adquirida,
            A instancia de LoginView NAO é mais Visivel
            */
        }
    }
    
    private void mostrarMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
}
