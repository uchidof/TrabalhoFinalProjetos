/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state.notificacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import model.Notificacao;
import model.User;
import presenter.NotificacaoPresenter;
import service.GerenciaUserService;
import command.notificacao.INotificacaoCommand;
import command.notificacao.SalvarCommand;
import view.NotificacaoView;


/**
 *
 * @author Fpc
 */
public class InclusaoState extends NotificacaoState{
    private NotificacaoView view;
    private INotificacaoCommand comando = null;
    private GerenciaUserService serviceUser;
    private List<User> nomeUsuarios;
    private String usersAdicionados = "";
    
    public InclusaoState(NotificacaoPresenter presenter){
        super(presenter);
        this.serviceUser = new GerenciaUserService();
        this.view = presenter.getView();
        this.nomeUsuarios = new ArrayList<>();
        configurarTela();
    }
    
    @Override
    public void salvar(){
        String titulo = view.getTextFieldTitulo().getText();
        String mensagem = view.getTextAreaMensagem().getText();
        User temp;

        
        for(User elem: nomeUsuarios){
            elem.setId((serviceUser.consultar(elem.getNome()).getId()));
        }
       
        if("".equals(titulo) && "".equals(mensagem) && nomeUsuarios.isEmpty())
        throw new RuntimeException("Campos vazios! Verifique novamente.");
            
        Notificacao notificacao = new Notificacao(titulo, mensagem);
        
        comando = new SalvarCommand(notificacao, nomeUsuarios);
        comando.executar();
        
        presenter.exibirMensagem("Noficacao salva com sucesso!", "Notificacao", 1);
    }

    @Override
    public void fechar(){
        view.setVisible(false);
    }

    public void atualizaTabela(DefaultTableModel modeloTabela){
        List<User> usuarios = serviceUser.listarTodos();
        Object[] dado = {"TODOS"};
        modeloTabela.addRow(dado);
        int i = 0;
        
        for(User usuario:usuarios){
            Object[] dados = {usuario.getNome()};
            modeloTabela.addRow(dados);
            i++;
        }
    }
 
    private void adicionarUsuario(){
        int i = view.getTableUsuarios().getSelectedRow();
        String nome =view.getTableUsuarios().getValueAt(i,0).toString();
        nomeUsuarios.add(new User(nome, null));
        usersAdicionados += nome + "\n";
        view.getTextAreaUsuarios().setText(usersAdicionados);
    }
    
    @Override
    public void configurarTela(){
        view.setVisible(false);
        DefaultTableModel modeloTabela = new DefaultTableModel();
        view.getTableUsuarios().setModel(modeloTabela);
        modeloTabela.addColumn("Nome");
        modeloTabela.setRowCount(0);
        atualizaTabela(modeloTabela);
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.getTextAreaUsuarios().setEditable(false);
        
        
        view.getTableUsuarios().setRowSelectionAllowed(false);

        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    
                }
            }
        });
        
        
        view.getBtnAdicionar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    adicionarUsuario();
                }catch(Exception e){
                    
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    
                }
            }
        });
        
        view.setVisible(true);
    }
}
