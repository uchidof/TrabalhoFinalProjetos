/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Log;
import adapter.LogAdapter; // Vindo da Dependencia
import properties.Configuracao;
import service.Sessao;

/**
 * Adaptador de log para usar LogAdapter.
 */
public class LogAdaptador {
    private LogAdapter log;
    private static LogAdaptador instancia = null;
    
    private LogAdaptador() {
        this.log = criarLogAdapter();
    }
    
    private LogAdapter criarLogAdapter() {
    Configuracao configuracao = Configuracao.getInstancia();    
    String nomeClasse = configuracao.getProperties("LOG");   // Nome da classe concreta, por exemplo "adapter.LogAdapterCSV"
    
    try {
        // Carregar a classe concreta pelo nome obtido das propriedades
        Class<?> classe = Class.forName(nomeClasse);
        
        // Verificar se essa classe é uma subclasse de LogAdapter
        if (!LogAdapter.class.isAssignableFrom(classe)) {
            throw new IllegalArgumentException("A classe " + nomeClasse + " não é uma subclasse de LogAdapter.");
        }
        
        // Obter o arquivo de log
        String caminhoArquivo = configuracao.getProperties("LOG_FILE_PATH"); // O caminho do arquivo CSV
        File arquivo = new File(caminhoArquivo);
        
        // Instanciar o LogAdapter passando o arquivo no construtor
        LogAdapter logAdapter = (LogAdapter) classe.getDeclaredConstructor(File.class).newInstance(arquivo);
        
        return logAdapter;
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Classe de log não encontrada: " + nomeClasse, e);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao instanciar a classe de log: " + nomeClasse, e);
    }
}

    /*
    private LogAdapter criarLogAdapter() {
        Configuracao configuracao = Configuracao.getInstancia();    
        String caminhoArquivo = configuracao.getProperties("LOG");  
            //Da properties adquire qual LOG deve ser usado
        File arquivo = new File(caminhoArquivo);
        
        LogAdapter novoLog = new LogAdapterCSV(arquivo); 
        return novoLog;
    }
*/
    
    
    
    public static LogAdaptador getInstancia() {
        if (instancia == null)
            instancia = new LogAdaptador();
               
        return instancia;
    }
    
    public void addLog(String operacao, String mensagem) {
        String user = Sessao.getInstancia().getUserLogado().getNome();
        
        if (user == null) {
            user = "";
        }
        
        // Cria um objeto Log com os dados necessários
        Log logEntry = new Log(operacao, mensagem, LocalDate.now().atTime(LocalTime.now()), user);
        
        try {
            log.escrever(logEntry); // Escreve o log no arquivo CSV
        } catch (IOException e) {
            e.printStackTrace(); // Trata exceções de I/O
        }
    }
}
