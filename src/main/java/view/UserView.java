/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author nitro5WIN10
 */
public class UserView extends javax.swing.JInternalFrame {

    /**
     * Creates new form UsuarioView
     */
    public UserView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textFieldSenha = new javax.swing.JTextField();
        labelConfirmaSenha = new javax.swing.JLabel();
        textFieldConfirmaSenha = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        labelNotificacoesEnviadas = new javax.swing.JLabel();
        textFieldNotificacoesEnviadas = new javax.swing.JTextField();
        textFieldNotificacoesLidas = new javax.swing.JTextField();
        labelNotificacoesLidas = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setForeground(java.awt.Color.lightGray);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuario");

        btnSalvar.setText("Salvar");

        btnFechar.setText("Fechar");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Usuarios");

        btnExcluir.setText("Excluir");

        labelNome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelNome.setText("Nome");

        labelSenha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSenha.setText("Senha");

        labelConfirmaSenha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelConfirmaSenha.setText("Confirma Senha");

        btnEditar.setText("Editar");

        labelNotificacoesEnviadas.setText("Notificações enviadas");

        labelNotificacoesLidas.setText("Notificações lidas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelNome)
                            .addComponent(labelSenha)
                            .addComponent(labelConfirmaSenha)
                            .addComponent(textFieldConfirmaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(textFieldSenha)
                            .addComponent(textFieldNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNotificacoesEnviadas)
                            .addComponent(textFieldNotificacoesEnviadas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNotificacoesLidas)
                            .addComponent(textFieldNotificacoesLidas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(262, 262, 262))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(labelNotificacoesEnviadas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldNotificacoesEnviadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNotificacoesLidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldNotificacoesLidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelConfirmaSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnFechar() {
        return btnFechar;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JTextField getTextFieldConfirmaSenha() {
        return textFieldConfirmaSenha;
    }

    public JTextField getTextFieldNome() {
        return textFieldNome;
    }

    public JTextField getTextFieldSenha() {
        return textFieldSenha;
    }

    public JLabel getLabelConfirmaSenha() {
        return labelConfirmaSenha;
    }

    public JLabel getLabelNome() {
        return labelNome;
    }

    public JLabel getLabelSenha() {
        return labelSenha;
    }

    public JLabel getLabelNotificacoesEnviadas() {
        return labelNotificacoesEnviadas;
    }

    public JLabel getLabelNotificacoesLidas() {
        return labelNotificacoesLidas;
    }

    public JTextField getTextFieldNotificacoesEnviadas() {
        return textFieldNotificacoesEnviadas;
    }

    public JTextField getTextFieldNotificacoesLidas() {
        return textFieldNotificacoesLidas;
    }

    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel labelConfirmaSenha;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNotificacoesEnviadas;
    private javax.swing.JLabel labelNotificacoesLidas;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JTextField textFieldConfirmaSenha;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldNotificacoesEnviadas;
    private javax.swing.JTextField textFieldNotificacoesLidas;
    private javax.swing.JTextField textFieldSenha;
    // End of variables declaration//GEN-END:variables
}
