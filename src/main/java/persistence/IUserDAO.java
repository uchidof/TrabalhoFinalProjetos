/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.List;

import model.User;

/**
 *
 * @author Fpc
 */
public interface IUserDAO {
    public boolean criar(User user);
    public User consultar(String nome); //Busca o User pelo nome, no BD
    public  void atualizarUser (User user);
    public void alterarSenha(String nome, String senha);
    public void autorizarUser(String nome);
    public boolean deletarUser (String nome);
    public List<User> listarTodos();
    public List<User> listarInativos();

}
