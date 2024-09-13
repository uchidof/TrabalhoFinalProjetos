/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import presenter.UserPresenter;

/**
 *
 * @author Fpc
 */
public class FecharCommand implements IUserCommand{
    private UserPresenter presenter;
    
    public FecharCommand(UserPresenter presenter){
        this.presenter = presenter;
    }
    
    @Override
    public void executar(){
        presenter.getView().setVisible(false);
    }
}
