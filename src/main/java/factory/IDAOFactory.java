/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import persistence.IUserDAO;
import persistence.INotificacaoDAO;

/**
 *
 * @author Fpc
 */
public interface IDAOFactory {
    public IUserDAO getUserDAO();
    public INotificacaoDAO getNotificacaoDAO();        
}
