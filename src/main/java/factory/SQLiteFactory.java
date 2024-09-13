/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import persistence.INotificacaoDAO;
import persistence.IUserDAO;
import persistence.NotificacaoDAOSQLite;
import persistence.UserDAOSQLite;


/**
 *
 * @author Fpc
 */
public class SQLiteFactory implements IDAOFactory{
    
    @Override
    public IUserDAO getUserDAO(){
        return new UserDAOSQLite();
    }
    
    @Override
    public INotificacaoDAO getNotificacaoDAO(){
        return new NotificacaoDAOSQLite();
    }
}
