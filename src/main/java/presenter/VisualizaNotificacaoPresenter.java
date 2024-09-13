/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import service.GerenciaNotificacaoService;
import model.Notificacao;
import view.VisualizaNotificacaoView;

/**
 *
 * @author Fpc
 */
public class VisualizaNotificacaoPresenter {
    private VisualizaNotificacaoView view;
    private GerenciaNotificacaoService service;
    
    public VisualizaNotificacaoPresenter(Notificacao notificacao){
        this.view = new VisualizaNotificacaoView();
        this.service = new GerenciaNotificacaoService();
        configurar(notificacao);
    }
    
    private void fechar(){
        view.setVisible(false); //alterar implementacao
    }
    
    private void marcarMensagemLida(Notificacao notificacao){
        service.marcaLida(notificacao);
        exibirMensagem("Lida!", "Notificacao", 1);
    }

    public VisualizaNotificacaoView getView() {
        return view;
    }

    private void configurar(Notificacao notificacao){
        
        view.getTextFieldTitulo().setText("");
        view.getTextAreaMensagem().setText("");
        view.getTextFieldTitulo().setText(notificacao.getTitulo());
        view.getTextAreaMensagem().setText(notificacao.getMensagem());
        view.getTextFieldTitulo().setEditable(false);
        view.getTextAreaMensagem().setEditable(false);
        
        view.getBtnMarcaLida().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    marcarMensagemLida(notificacao);
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
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
        view.setVisible(true);
    }
     
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }

}
