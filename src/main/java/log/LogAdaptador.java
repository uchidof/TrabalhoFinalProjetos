package log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Log;
import adapter.LogAdapter;
import properties.Configuracao;
import service.Sessao;

/**
 * Adaptador de log para usar LogAdapter.
 */
public class LogAdaptador {
    private LogAdapter logAdapter;
    private static LogAdaptador instancia = null;

    // Construtor privado (Singleton)
    private LogAdaptador() {
        this.logAdapter = criarLogAdapter();
    }

    /**
     * Método para criar o LogAdapter correto com base na configuração.
     * O padrão Adapter é aplicado aqui para adaptar diferentes formatos (CSV, JSON) para a mesma interface LogAdapter.
     */
    private LogAdapter criarLogAdapter() {
    Configuracao configuracao = Configuracao.getInstancia();
    String tipoLog = configuracao.getProperties("LOG"); // Nome do adaptador
    
    String completa = tipoLog;
    String chave = "adapter.LogAdapter";
    // Encontrar a posição da string chave
    int posicao = completa.indexOf(chave);
        
        
    String resultado = completa.substring(posicao + chave.length()).trim();
    // Converter a substring para letras minúsculas
    resultado = resultado.toLowerCase();            
        
    String caminhoArquivo = "logs."+resultado;
    //System.out.println(caminhoArquivo);
    
    
    // Verifica se o caminho do arquivo está presente
    if (caminhoArquivo == null || caminhoArquivo.isEmpty()) {
        throw new RuntimeException("Erro: Caminho do arquivo de log não especificado nas configurações.");
    }
    
    

    File arquivo = new File(caminhoArquivo);

    // Verificar se o diretório existe, se não, cria
    File parentDir = arquivo.getParentFile();
    if (parentDir != null && !parentDir.exists()) {
        parentDir.mkdirs(); // Cria o diretório se ele não existir
    }

    // Verificar se o arquivo existe, se não, cria o arquivo vazio
    try {
        if (!arquivo.exists()) {
            arquivo.createNewFile(); // Cria o arquivo se ele não existir
        }
    } catch (IOException e) {
        throw new RuntimeException("Erro ao criar o arquivo de log: " + caminhoArquivo, e);
    }

    // Aqui estamos utilizando o Adapter para fazer com que diferentes formatos se ajustem à interface LogAdapter.
    try {
        // Carregar dinamicamente a classe de log e criar o adaptador correto
        Class<?> classe = Class.forName(tipoLog);  // Carrega a classe da configuração (ex: adapter.LogAdapterCSV)
        
        // Verifica se a classe é um subtipo de LogAdapter
        if (!LogAdapter.class.isAssignableFrom(classe)) {
            throw new IllegalArgumentException("A classe " + tipoLog + " não é uma subclasse de LogAdapter.");
        }

        // Cria uma instância do adaptador passando o arquivo de log
        return (LogAdapter) classe.getDeclaredConstructor(File.class).newInstance(arquivo);

    } catch (Exception e) {
        throw new RuntimeException("Erro ao instanciar o adaptador de log: " + tipoLog, e);
    }
}


    // Método Singleton para obter a instância do LogAdaptador
    public static LogAdaptador getInstancia() {
        if (instancia == null) {
            instancia = new LogAdaptador();
        }
        return instancia;
    }

    /**
     * Adiciona um log ao adaptador de logs.
     *
     * @param operacao A operação realizada
     * @param mensagem A mensagem ou descrição
     */
    public void addLog(String operacao, String mensagem) {
        String user = Sessao.getInstancia().getUserLogado().getNome();

        if (user == null || user.isEmpty()) {
            user = "Usuário desconhecido";
        }

        // Cria um objeto Log com os dados necessários
        Log logEntry = new Log(operacao, mensagem, LocalDate.now().atTime(LocalTime.now()), user);

        try {
            logAdapter.escrever(logEntry);  // Escreve o log no arquivo através do adaptador
        } catch (IOException e) {
            System.err.println("Erro ao escrever o log: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
