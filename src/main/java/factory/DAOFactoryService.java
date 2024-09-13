/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import persistence.IUserDAO;
import persistence.INotificacaoDAO;
import properties.Configuracao;


/**
 *
 * @author Fpc
 */
public class DAOFactoryService {
    private IDAOFactory sgbd;
    private String propriedades;
    
    public DAOFactoryService(){
        this.sgbd = getDAOFactory();
    }
    
    public IUserDAO getUserDAO(){
        return this.sgbd.getUserDAO();
    }
    
    public INotificacaoDAO getNotificacaoDAO(){
        return this.sgbd.getNotificacaoDAO();
    }
    
    private void getPropriedades(){
        Configuracao configuracao = Configuracao.getInstancia();
        propriedades = configuracao.getProperties("SGBD");
    }
    
    private IDAOFactory getDAOFactory(){
        try {
            getPropriedades();
            Class<?> classeProp = Class.forName(propriedades);
            Object instanciaSGBD = classeProp.getDeclaredConstructor().newInstance();
            return (IDAOFactory)instanciaSGBD;
        } catch (Exception e) {
            throw new RuntimeException("Erro: SGBD não suportado! \n" + e.getMessage());
        }
    }
}
