package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.User;
import properties.Configuracao;
import service.GerenciaUserService;
import service.Sessao;
import view.ConfiguracaoView;

/**
 * Classe responsável por gerenciar a interface de configuração da aplicação.
 */
public class ConfiguracaoPresenter {
    private User userLogado;  // Usuário logado atualmente
    private ConfiguracaoView view;  // A vista (interface gráfica) que será apresentada
    private GerenciaUserService service;  // Serviço de gerenciamento de usuários

    // Construtor
    public ConfiguracaoPresenter() {
        this.view = new ConfiguracaoView();  // Inicializa a view
        this.service = new GerenciaUserService();  // Inicializa o serviço de gerenciamento de usuários
        this.userLogado = Sessao.getInstancia().getUserLogado();  // Obtém o usuário logado da sessão
        configurar();  // Chama o método para configurar a interface
    }

    // Método responsável por configurar os componentes da interface
    private void configurar() {
        view.setVisible(false);  // Deixa a janela invisível inicialmente
        view.getTextFieldNome().setText(userLogado.getNome());  // Define o nome do usuário logado no campo de texto
        view.getTextFieldDataCadastro().setText(userLogado.getDtCadastro());  // Define a data de cadastro do usuário
        view.getBtnExcluir().setVisible(false);  // Oculta o botão "Excluir"
        view.getLabelSenhaAtual().setVisible(false);  // Oculta o rótulo "Senha Atual"
        view.getLabelNovaSenha().setVisible(false);  // Oculta o rótulo "Nova Senha"
        view.getLabelConfirmaSenha().setVisible(false);  // Oculta o rótulo "Confirma Senha"
        view.getTextFieldSenhaAtual().setVisible(false);  // Oculta o campo "Senha Atual"
        view.getTextFieldNovaSenha().setVisible(false);  // Oculta o campo "Nova Senha"
        view.getTextFieldConfirmaSenha().setVisible(false);  // Oculta o campo "Confirma Senha"
        view.getTextFieldNome().setEnabled(false);  // Desabilita o campo "Nome"
        view.getTextFieldDataCadastro().setEnabled(false);  // Desabilita o campo "Data de Cadastro"
        view.getLabelLog().setVisible(false);  // Oculta o rótulo "Log"
        view.getBoxLog().setVisible(false);  // Oculta o combobox de seleção de log

        // Se o usuário logado for admin, exibe o botão "Alterar Log"
        if ("admin".equals(userLogado.getTipo())) {
            view.getBtnAlterarLog().setVisible(true);
        }

        // Adiciona um listener ao botão "Alterar Senha"
        view.getBtnAlterarSenha().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    alterarSenha();  // Chama o método para alterar a senha
                } catch (Exception e) {
                    exibirMensagem(e.getMessage(), "Erro", 0);  // Exibe mensagem de erro
                }
            }
        });

        // Remove todos os ActionListeners do botão "Salvar"
        for (ActionListener listener : view.getBtnSalvar().getActionListeners()) {
            view.getBtnSalvar().removeActionListener(listener);
        }

        // Adiciona um listener ao botão "Salvar"
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    salvar();  // Chama o método para salvar as alterações
                } catch (Exception e) {
                    exibirMensagem(e.getMessage(), "Erro", 0);  // Exibe mensagem de erro
                }
            }
        });

        // Adiciona um listener ao botão "Fechar"
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    fechar();  // Fecha a interface
                } catch (Exception e) {
                    exibirMensagem(e.getMessage(), "Erro", 0);  // Exibe mensagem de erro
                }
            }
        });

        // Adiciona um listener ao botão "Alterar Log"
        view.getBtnAlterarLog().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    configAlterarLog();  // Chama o método para alterar o tipo de log
                } catch (Exception e) {
                    exibirMensagem(e.getMessage(), "Erro", 0);  // Exibe mensagem de erro
                }
            }
        });

        view.setVisible(true);  // Torna a janela visível novamente
    }

    // Método para retornar a view atual
    public ConfiguracaoView getView() {
        return view;
    }

    // Método para fechar a interface
    private void fechar() {
        view.setVisible(false);  // Oculta a janela
    }

    // Exibe uma mensagem ao usuário
    public void exibirMensagem(String mensagem, String titulo, int type) {
        JOptionPane.showMessageDialog(this.view, mensagem, titulo, type);  // Exibe um diálogo de mensagem
    }

    // Exibe uma confirmação ao usuário
    public int exibirConfirmacao(String mensagem, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);  // Exibe um diálogo de confirmação
    }

    // Salva o novo tipo de log no arquivo de configuração
    private void salvarNovoLog(String novoLog) {
        Configuracao.getInstancia().setProperties("LOG", novoLog);  // Altera a propriedade "LOG" na configuração
        exibirMensagem("Novo Log salvo!! Por favor reinicie a aplicação para a atualização.", "Log", 1);  // Exibe uma mensagem de sucesso
        fechar();  // Fecha a interface
    }

    // Configura a interface para alterar o tipo de log
    private void configAlterarLog() {
        // Oculta elementos da interface
        view.getLabelNome().setVisible(false);
        view.getLabelDataCadastro().setVisible(false);
        view.getTextFieldNome().setVisible(false);
        view.getTextFieldDataCadastro().setVisible(false);
        view.getBtnAlterarSenha().setVisible(false);
        view.getBtnAlterarLog().setVisible(false);

        view.getBoxLog().removeAllItems();  // Remove os itens atuais do combobox

        // Obtém as opções de log da configuração e adiciona ao combobox
        String tiposProperties = Configuracao.getInstancia().getProperties("LOGAVALIABLE");
        ArrayList<String> tiposConverter = new ArrayList<>();

        for (String item : tiposProperties.split("OR")) {  // Divide as opções por "OR"
            tiposConverter.add(item);
        }

        for (String elem : tiposConverter) {
            view.getBoxLog().addItem(elem);  // Adiciona cada opção ao combobox
        }

        // Remove listeners antigos do botão "Salvar"
        for (ActionListener listener : view.getBtnSalvar().getActionListeners()) {
            view.getBtnSalvar().removeActionListener(listener);
        }

        // Adiciona um novo listener ao botão "Salvar"
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    // Obtém o valor selecionado no ComboBox e concatena com "adapter.LogAdapter"
                    String logSelecionado = view.getBoxLog().getSelectedItem().toString();  // Lê o valor selecionado (ex: CSV ou JSON)
                    String novoLog = "adapter.LogAdapter" + logSelecionado;  // Concatena "adapter.LogAdapter" com o valor selecionado
                    System.out.println("Log selecionado:"+ novoLog);
                    salvarNovoLog(novoLog);  // Salva o novo tipo de log
                    
                    //String novoLog = view.getBoxLog().getSelectedItem().toString();  // Obtém o log selecionado
                    //salvarNovoLog(novoLog);  // Salva o novo tipo de log
                } catch (Exception e) {
                    exibirMensagem(e.getMessage(), "Erro", 0);  // Exibe mensagem de erro
                }
            }
        });

        // Torna visíveis os elementos relacionados à alteração de log
        view.getLabelLog().setVisible(true);
        view.getBoxLog().setVisible(true);
    }

    // Método para exibir os campos de alteração de senha
    private void alterarSenha() {
        view.getBtnAlterarSenha().setVisible(false);  // Oculta o botão "Alterar Senha"
        view.getLabelSenhaAtual().setVisible(true);  // Exibe o rótulo "Senha Atual"
        view.getLabelNovaSenha().setVisible(true);  // Exibe o rótulo "Nova Senha"
        view.getLabelConfirmaSenha().setVisible(true);  // Exibe o rótulo "Confirma Senha"
        view.getTextFieldSenhaAtual().setVisible(true);  // Exibe o campo "Senha Atual"
        view.getTextFieldNovaSenha().setVisible(true);  // Exibe o campo "Nova Senha"
        view.getTextFieldConfirmaSenha().setVisible(true);  // Exibe o campo "Confirma Senha"
    }

    // Método para salvar a nova senha do usuário
    private void salvar() {
        String nome = Sessao.getInstancia().getUserLogado().getNome();  // Obtém o nome do usuário logado
        String senha = Sessao.getInstancia().getUserLogado().getSenha();  // Obtém a senha atual
        String senhaAtual = view.getTextFieldSenhaAtual().getText();  // Obtém o valor do campo "Senha Atual"
        String novaSenha = view.getTextFieldNovaSenha().getText();  // Obtém o valor do campo "Nova Senha"
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();  // Obtém o valor do campo "Confirma Senha"

        if (!senha.equals(senhaAtual)) {  // Verifica se a senha atual está correta
            throw new RuntimeException("Senha atual incorreta!");
        }

        if (!novaSenha.equals(confirmaSenha)) {  // Verifica se as novas senhas coincidem
            throw new RuntimeException("Novas senhas não conferem");
        }

        service.alterarSenha(nome, novaSenha);  // Chama o serviço para alterar a senha
        exibirMensagem("Sua senha foi alterada com sucesso!", "Alteracao de Senha", 1);  // Exibe mensagem de sucesso
        configurar();  // Reconfigura a interface
    }

}
