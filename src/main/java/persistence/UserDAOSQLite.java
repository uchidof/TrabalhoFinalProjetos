/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.User;
import service.ConexaoService;

/**
 *
 * @author Fpc
 */
public class UserDAOSQLite implements IUserDAO{
    //TODO
    @Override 
    public boolean criar(User user){
        Connection conexao = ConexaoService.getConexao();    

        try {
            String sql = "INSERT INTO usuario (nome, senha, tipo, ativo, dataCadastro) VALUES (?, ?, ?, ?, ?)";
            String nome = user.getNome();
            String senha = user.getSenha();
            String tipo = user.getTipo();
            boolean ativo = user.getAtivo();
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql); 
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3,tipo);
            preparedStatement.setBoolean(4, ativo);
            preparedStatement.setString(5, LocalDate.now().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; //true é sucesso para insercao
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override 
    public User consultar(String nome){
        Connection conexao = ConexaoService.getConexao();  //conexao do banco
        try{          
            String sql = "SELECT * FROM usuario WHERE nome = ? LIMIT 1"; //sql generico para que retorne o primeiro usuario encontrado

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);  //insercao de where no sql
            
            ResultSet resultSet = preparedStatement.executeQuery(); 
            
            if(!resultSet.next()){
                return null;
            }else{
                User usuarioEncontrado = new User();
                usuarioEncontrado.setId(resultSet.getInt("id"));
                usuarioEncontrado.setNome(resultSet.getString("nome"));
                usuarioEncontrado.setSenha(resultSet.getString("senha"));
                usuarioEncontrado.setTipo(resultSet.getString("tipo"));
                usuarioEncontrado.setAtivo(resultSet.getBoolean("ativo"));
                usuarioEncontrado.setDtCadastro(resultSet.getString("dataCadastro"));
                return usuarioEncontrado;
            }

            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public  void atualizarUser (User user){ 
        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "UPDATE usuario SET nome = ?, senha = ?, tipo = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getSenha());
            preparedStatement.setString(3, user.getTipo());
            preparedStatement.setBoolean(4, user.getAtivo());
            preparedStatement.setInt(5, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("Nenhum usuário foi atualizado. Verifique o ID do usuário.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public void alterarSenha(String nome, String senha){
            Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "UPDATE usuario SET senha = ? WHERE nome = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, senha);
            preparedStatement.setString(2, nome);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("Erro ao tentar alterar senh");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public void autorizarUser(String nome){
            Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "UPDATE usuario SET ativo = ? WHERE nome = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, nome);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("Erro ao tentar alterar senha");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public boolean deletarUser (String nome) {
        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "DELETE FROM usuario WHERE nome = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, nome);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public List<User> listarTodos(){
        List<User> usuarios = new ArrayList<>();

        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement prepararLista = conexao.prepareStatement(sql);

            try (ResultSet resultSet = prepararLista.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    String tipo = resultSet.getString("tipo");
                    boolean ativo = resultSet.getBoolean("ativo");
                    
                    String dataCadastro = resultSet.getString("dataCadastro");
                    
                    User usuario = new User(nome, senha, tipo, dataCadastro);
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }

        return usuarios;
    }
    
    @Override
    public List<User> listarInativos(){
        List<User> usuarios = new ArrayList<>();

        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "SELECT * FROM usuario WHERE ativo=false";
            PreparedStatement prepararLista = conexao.prepareStatement(sql);

            try (ResultSet resultSet = prepararLista.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    String tipo = resultSet.getString("tipo");
                    boolean ativo = resultSet.getBoolean("ativo");
                    
                    String dataCadastro = resultSet.getString("dataCadastro");
                    
                    User usuario = new User(nome, senha, tipo, dataCadastro);
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }

        return usuarios;
    }

    
}
