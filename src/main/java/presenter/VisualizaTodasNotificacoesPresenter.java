/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Notificacao;
import model.User;
import service.GerenciaNotificacaoService;
import service.Sessao;
import view.MenuView;
import view.VisualizaTodasNotificacoesView;
/**
 *
 * @author Fpc
 */
public class VisualizaTodasNotificacoesPresenter {
    private VisualizaTodasNotificacoesView view;
    private GerenciaNotificacaoService service;
    private List<Notificacao> notificacoes;

    public VisualizaTodasNotificacoesPresenter() {
        this.view = new VisualizaTodasNotificacoesView();
        this.service = new GerenciaNotificacaoService();
        this.notificacoes = new ArrayList<>();
        configurar();
    }
    
     public void atualizarTabela(DefaultTableModel modeloTabela){
        this.notificacoes = service.consultarTodasPorUsuario(Sessao.getInstancia().getUserLogado().getId());

        for(Notificacao notificacao:notificacoes){
            Object[] dados = {notificacao.getTitulo(), notificacao.isMensagemLida()};
            modeloTabela.addRow(dados);
        }
    }
     
    private void fechar(){
        view.setVisible(false); //alterar implementacao
    }
    
    private void detalhesNotificacao(){
        int linhaSelecionada = view.getTableNotificacao().getSelectedRow();
        
        if(linhaSelecionada == -1){
            throw new RuntimeException("Nenhuma linha selecionada.");
        }
        
        VisualizaNotificacaoPresenter detalhesPresenter= new VisualizaNotificacaoPresenter(notificacoes.get(linhaSelecionada));
        MenuView menuView = MenuPresenter.getInstancia().getView();
        menuView.getjDesktopPane1().add(detalhesPresenter.getView());
    }

    public VisualizaTodasNotificacoesView getView() {
        return view;
    }

    private void configurar(){
        DefaultTableModel modeloTabela = new DefaultTableModel();
        view.getTableNotificacao().setModel(modeloTabela);
        modeloTabela.addColumn("Titulo");
        modeloTabela.addColumn("lida");
        modeloTabela.setRowCount(0);
        
        
        atualizarTabela(modeloTabela);
        
        
        view.getBtnDetalhesNotificacao().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    detalhesNotificacao();
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
        
    }
        
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }

}
