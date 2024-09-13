/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

import model.Notificacao;
import model.User;
import factory.DAOFactoryService;
import persistence.INotificacaoDAO;

//RESOLVER LOG
//import com.log. *; // repositorio github projeto maven externo com jitpack
import log.LogAdaptador;


/**
 *
 * @author Fpc
 */
public class GerenciaNotificacaoService {
    private INotificacaoDAO notificacaoDAO;
    private DAOFactoryService factoryService;
    //private LogService log;

    public GerenciaNotificacaoService() {
        //this.log = new LogService("com.log.CSVLog");
        this.factoryService = new DAOFactoryService();
        this.notificacaoDAO = factoryService.getNotificacaoDAO();
    }

    public boolean inserir(Notificacao notificacao, List<User> usuariosPadrao){
        boolean retorno = notificacaoDAO.criar(notificacao, usuariosPadrao);
        LogAdaptador.getInstancia().addLog("Envio de Notificacao: ", notificacao.getTitulo());
        return retorno;
    }
    
    public List<Notificacao> listarTodas(){
        return null;
    }

    public void excluir(){
       
    }
    
    public void marcaLida(Notificacao notificacao){
        notificacaoDAO.marcarLida(notificacao);
        LogAdaptador.getInstancia().addLog("Leitura Notificacao: ", notificacao.getTitulo());
    }
    
    public List<Notificacao> consultarTodasLidas(int id){
        return notificacaoDAO.listarLidas(id);
    }
    
    public List<Notificacao> consultarTodasPorUsuario(int id){
        return notificacaoDAO.listByUsers(id);
    }

}
