/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;


import service.ConexaoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Fpc
 */
public class InicializaBanco {
    
    public static void inicializar() {
        Connection conexao = null;
        try{
            conexao = ConexaoService.getConexao();  //Armazena a conexao com o BD retornado pela ConexaoService
            Statement preparasql = conexao.createStatement();   //Objeto para enviar os comandos ao BD
            
            preparasql.execute(
                "CREATE TABLE IF NOT EXISTS notificacao (" +
                "id_notificacao INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR(255)," +
                "mensagem TEXT)"
            );  //Cria Tabela de notificacao
            
            preparasql.execute(
                "CREATE TABLE IF NOT EXISTS usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT UNIQUE," +
                "senha TEXT," +
                "tipo TEXT," +
                "ativo BOOLEAN," +
                "dataCadastro TEXT)"
            );  //Cria Tabela de usuario
            
            preparasql.execute(
                "CREATE TABLE IF NOT EXISTS notificacaoUsuario (" +
                "id_notificacao INT," +
                "id_usuario INT," +
                "lida BOOLEAN," +
                "PRIMARY KEY (id_notificacao, id_usuario)," +
                "FOREIGN KEY (id_notificacao) REFERENCES notificacao(id_notificacao)," +
                "FOREIGN KEY (id_usuario) REFERENCES usuario(id))"
            );  //Cria Tabela de notificacaoUsuario
            
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally{
            ConexaoService.closeConexao(conexao);
        }
        
    }
    
}
