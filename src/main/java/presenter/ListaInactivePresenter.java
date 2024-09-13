/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.User;
import service.GerenciaUserService;
import state.user.VisualizacaoState;
import view.ListaUserView;

/**
 *
 * @author Fpc
 */
public class ListaInactivePresenter {
    private ListaUserView view;
    private GerenciaUserService service;
    
    public ListaInactivePresenter(){
        this.service = new GerenciaUserService();
        this.view = new ListaUserView();
        configurar();
        
    }
    
    private void configurar(){
        DefaultTableModel modeloTabela = new DefaultTableModel();
        view.setTitle("Listar Usuarios Inativos");
        view.getTableTodosUsuarios().setModel(modeloTabela);
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Data de Cadastro");
        modeloTabela.setRowCount(0);
        
        atualizarTabela(modeloTabela);
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getBtnAutorizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    autorizar(modeloTabela);
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        
        view.getBtnVisualizaDetalhes().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    visualizarDetalhes(modeloTabela);
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.setVisible(true);

    }
    
    private void autorizar(DefaultTableModel modeloTabela){
        int linhaSelecionada = view.getTableTodosUsuarios().getSelectedRow();
        
        if(linhaSelecionada == -1){
            throw new RuntimeException("Nenhuma linha selecionada.");
        }
        
        String nome = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
        service.autorizar(nome);
        exibirMensagem("Usuario autorizado!","Sucesso", 1);
        atualizarTabela(modeloTabela);
    }
    
    public void atualizarTabela(DefaultTableModel modeloTabela){
        List<User> usuarios = service.listarInativos();
        modeloTabela.setRowCount(0);

        for(User usuario:usuarios){
            Object[] dados = {usuario.getNome(), usuario.getDtCadastro()};
            modeloTabela.addRow(dados);
        }
    }
    
    private void fechar(){
        view.setVisible(false); //alterar implementacao
    }

    private void visualizarDetalhes(DefaultTableModel modeloTabela){
        int linhaSelecionada = view.getTableTodosUsuarios().getSelectedRow();
        
        if(linhaSelecionada == -1){
            throw new RuntimeException("Nenhuma linha selecionada.");
        }
        
        String nome = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
        UserPresenter visualizaPresenter = new UserPresenter();
        
        visualizaPresenter.setEstado(new VisualizacaoState(visualizaPresenter, nome,null));
        MenuPresenter.getInstancia().getView().getjDesktopPane1().add(visualizaPresenter.getView());


    }
    
    public ListaUserView getView() {
        return view;
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
