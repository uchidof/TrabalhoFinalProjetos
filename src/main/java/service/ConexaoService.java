package service;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Fpc
 */
public class ConexaoService {
    private static final String url = "jdbc:sqlite:trabalhoFinal.db";   //Constante que define o caminho para o BD SQLite
    private static Connection conexao;  //Atributo usado para armazenar a conexão com o banco de dados
    
    public ConexaoService(){}
    
    public static Connection getConexao(){  //Metodo para fornecer uma conexão com o banco de dados.
        if(conexao == null)
            try{
                conexao = DriverManager.getConnection(url); //Tentativa de realizar a conexao
            }catch(Exception e){
                throw new RuntimeException(e.getMessage()); //Excecao caso a tentativa tenha dado errado
            }
        return conexao;
    }
    
    public static void closeConexao(Connection connection){
        try{
            if (connection != null && !connection.isClosed())
                connection.isClosed();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
