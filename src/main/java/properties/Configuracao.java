/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Fpc
 */

public class Configuracao {
    public static Configuracao instancia = null;
    private Properties propriedades = null;

    private Configuracao() {
        propriedades = new Properties();
    }

    public static Configuracao getInstancia(){
        if(instancia == null){
            instancia = new Configuracao();
        }
        
        return instancia;
    }
    
    public String getProperties(String chave){
        try (InputStream input = new FileInputStream("config.properties")) {
            propriedades.load(input);
            return propriedades.getProperty(chave.toUpperCase());
            
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    
    public void setProperties(String chave, String valor){
        try (OutputStream output = new FileOutputStream("config.properties")) {
            propriedades.setProperty(chave.toUpperCase(), valor);
            propriedades.store(output, null);
            System.out.println("Propriedade gravada com sucesso.");
            
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());

        }
    }

}
