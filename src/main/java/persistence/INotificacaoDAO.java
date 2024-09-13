/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.List;

import model.User;
import model.Notificacao;

/**
 *
 * @author Fpc
 */
public interface INotificacaoDAO {
    public boolean criar(Notificacao notificacao, List<User> usersPadrao);
    public Notificacao consultarNotificacao(int id);
    public void atualizarNotificacao(Notificacao notificacao);
    public boolean deletarNotificacao(int id);
    public List<Notificacao> listarTodos();
    public List<Notificacao> listByUsers(int idUsuario);
    public List<Notificacao> listarLidas(int idUsuario);
    public void marcarLida(Notificacao notificacao);

}
