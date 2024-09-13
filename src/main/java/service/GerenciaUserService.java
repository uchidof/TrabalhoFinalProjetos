/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import org.apache.maven.doxia.siterenderer.RendererException;

import model.User;
import factory.DAOFactoryService;
import persistence.IUserDAO;
import log.LogAdaptador;

/**
 *
 * @author Fpc
 */
public class GerenciaUserService {
    private IUserDAO userDAO;    //Interface para DAO da classe User
    private DAOFactoryService factoryService;   //Service para o Factory de um DAO
    private User userAutenticado;   //User que sera adquirido atraves da instancia UNICA da Sessao
    public static GerenciaUserService instance = null;
    
    public GerenciaUserService(){
        this.factoryService = new DAOFactoryService();
        this.userDAO = factoryService.getUserDAO();
        this.userAutenticado = Sessao.getInstancia().getUserLogado();
    }
    
    public User consultar(String nome){
        return userDAO.consultar(nome);
    }
    
    public List<User> listarTodos(){
        return userDAO.listarTodos();
    }
    
    public List<User> listarInativos(){
        return userDAO.listarInativos();
    }
    
    public void inserir(String nome, String senha){
        ValidadorSenha validador = new ValidadorSenha();
        List<String> resultadoValidador = validador.validar(senha);
        
        if (consultar(nome) != null) {
            //Usuario foi encontrado no BD
            System.out.println("Tentativa de LOG...");
            LogAdaptador.getInstancia().addLog("Insercao de Usuario: ", "Erro: Usuario já existe");
            throw new RuntimeException("Usuario já está cadastrado");
        }
        
        if (!resultadoValidador.isEmpty()) {
            //
            throw new RuntimeException("Erro: \n" + resultadoValidador.toString().replace(";", "\n").replace(",", "").replace("[", "").replace("]", ""));
        }
        
        User user = new User(nome, senha);
        if(listarTodos().isEmpty()){
            user.setTipo("admin");
            user.setAtivo(true);
        }else if(userAutenticado == null || !"admin".equalsIgnoreCase(userAutenticado.getTipo())){
            user.setAtivo(false);
            user.setTipo("padrao");
        }else{
            user.setAtivo(true);
            user.setTipo("padrao");
        }
        
        userDAO.criar(user);
        Sessao.getInstancia().setUserLogado(consultar(nome));
        LogAdaptador.getInstancia().addLog("Insercao de Usuario: ", "Sucesso Usuario "+ nome);
    }
    
    public boolean excluir(String nome){
        if("admin".equalsIgnoreCase(consultar(nome).getTipo())){
            throw new RuntimeException("Erro ao excluir o administrador.");
        }
        
        boolean resultadoExclusao = userDAO.deletarUser(nome);
        LogAdaptador.getInstancia().addLog("Exclusao de Usuario: ", "Sucesso Usuario "+ nome);
        return resultadoExclusao;
    }
    
    public void autorizar(String nome){
        userDAO.autorizarUser(nome);
        LogAdaptador.getInstancia().addLog("Autorizacao de Usuario: ", "Sucesso Usuario "+ nome);
    }    
    
    public void alterarSenha(String nome, String senha){
        ValidadorSenha validador = new ValidadorSenha();
        List <String> resultadoValidador = validador.validar(senha);
        
        if(!resultadoValidador.isEmpty()){
            throw new RuntimeException("Erro: \n" + resultadoValidador.toString().replace(";", "\n").replace(",", "").replace("[", "").replace("]", ""));
        }
        
        userDAO.alterarSenha(nome,senha);
        LogAdaptador.getInstancia().addLog("Alteração de Senha: ", "Sucesso Usuario "+ nome);
    }
}
