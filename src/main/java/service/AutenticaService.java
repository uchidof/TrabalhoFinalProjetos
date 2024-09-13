/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.User;
import log.LogAdaptador;

/**
 *
 * @author Fpc
 */
public class AutenticaService {
    GerenciaUserService gerenciaUser;
    public static AutenticaService instancia;
    
    public AutenticaService(){
        this.gerenciaUser = new GerenciaUserService();
            //Instancia da SERVICE de Gerencia de User, para auxiliar na autenticacao
    }
    
    public User autenticar(String nome, String senha){
        try {
            if (gerenciaUser.listarTodos().isEmpty()) {
                throw new RuntimeException("Nao existem usuarios, faça o cadastro.");
                //Verifica-se se ja ha usuarios
            }
            User user = gerenciaUser.consultar(nome);
            //Ja havendo ira buscar pelo NOME do usuario
            
            if (user == null) {
                throw new RuntimeException("Erro no Login: Usuario nao encontrado.");
            }else if(!user.getAtivo()){
                throw new RuntimeException("Erro no Login: Usuario nao ativo.");
            }else if (senha.equals(user.getSenha())) {
                Sessao.getInstancia().setUserLogado(user);  
                /*
                - Senha colocada igual a senha do BD
                - User foi autenticado
                - Sessao UNICA(Singleton) recebe o User
                    - Sessao so eh mantida aberta enquanto o User estiver armazenado no Objeto Sessao
                */
                
                LogAdaptador.getInstancia().addLog("Autenticação: ","Sucesso!");
                
                return user;
                //Retorna o USER agora autenticado
                
            }else{
                throw new RuntimeException("Erro no Login: Senha incorreta.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
