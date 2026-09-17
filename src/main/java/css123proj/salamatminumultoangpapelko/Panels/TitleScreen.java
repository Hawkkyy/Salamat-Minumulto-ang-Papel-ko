/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author hawk
 */
public class TitleScreen extends javax.swing.JPanel {

    
    private Consumer<String> goTo;
    
    public TitleScreen(Consumer<String> goTo) {
        this.goTo = goTo;
        
        add(new JLabel("This is the Title Screen"));
        add(new JButton("Start"));
        
        JButton openCutBtn = new JButton("Rewatch Opening Cutscene");
        add(openCutBtn);
        
        openCutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                goTo.accept("Opening Cutscene");
            }
        });
        
        initComponents();
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        setBtn = new javax.swing.JButton();
        opeBtn = new javax.swing.JButton();
        playBtn = new javax.swing.JButton();
        instBtn = new javax.swing.JButton();
        credBtn = new javax.swing.JButton();

        jLabel1.setText("Title Screen");

        setBtn.setText("Settings");
        setBtn.addActionListener(this::setBtnActionPerformed);

        opeBtn.setText("Rewatch Opening");
        opeBtn.addActionListener(this::opeBtnActionPerformed);

        playBtn.setText("Start Checking!");
        playBtn.addActionListener(this::playBtnActionPerformed);

        instBtn.setText("Instructions");
        instBtn.addActionListener(this::instBtnActionPerformed);

        credBtn.setText("Credits");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(487, 487, 487)
                .addComponent(jLabel1)
                .addContainerGap(499, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(credBtn)
                    .addComponent(instBtn)
                    .addComponent(playBtn)
                    .addComponent(opeBtn)
                    .addComponent(setBtn))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addComponent(playBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(credBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(setBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opeBtn)
                .addGap(72, 72, 72))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setBtnActionPerformed

    private void opeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opeBtnActionPerformed
        // TODO add your handling code here:
        goTo.accept("Opening Cutscene");

    }//GEN-LAST:event_opeBtnActionPerformed

    private void instBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_instBtnActionPerformed

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        // TODO add your handling code here:
        goTo.accept("Calendar");
        
    }//GEN-LAST:event_playBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton credBtn;
    private javax.swing.JButton instBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton opeBtn;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton setBtn;
    // End of variables declaration//GEN-END:variables
}
