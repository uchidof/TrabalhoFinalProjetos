/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.notificacao;

import java.util.List;
import model.Notificacao;
import model.User;
import service.GerenciaNotificacaoService;
import service.GerenciaUserService;


/**
 *
 * @author Fpc
 */
public class SalvarCommand implements INotificacaoCommand{
    private GerenciaNotificacaoService service;
    private Notificacao notificacao;
    private List<User> usuarios;
    
    public SalvarCommand(Notificacao notificacao, List<User>usuarios) {
        this.service = new GerenciaNotificacaoService();
        this.notificacao = notificacao;
        this.usuarios = usuarios;
    }
    
    @Override
    public void executar(){
        service.inserir(notificacao, usuarios);
    }
}
