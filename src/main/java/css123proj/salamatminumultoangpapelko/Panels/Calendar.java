/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels;

import java.util.function.Consumer;

/**
 *
 * @author hawk
 */
public class Calendar extends javax.swing.JPanel {

    /**
     * Creates new form Calendar
     */
    
    private Consumer<String> goTo;
            
    public Calendar(Consumer<String> goTo) {
        
        this.goTo = goTo;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lvl1Btn = new javax.swing.JButton();
        lvl2Btn = new javax.swing.JButton();
        lvl3Btn = new javax.swing.JButton();

        lvl1Btn.setText("    ");
        lvl1Btn.setOpaque(true);
        lvl1Btn.addActionListener(this::lvl1BtnActionPerformed);

        lvl2Btn.setText("    ");
        lvl2Btn.setOpaque(true);
        lvl2Btn.addActionListener(this::lvl2BtnActionPerformed);

        lvl3Btn.setText("     ");
        lvl3Btn.setOpaque(true);
        lvl3Btn.addActionListener(this::lvl3BtnActionPerformed);

        jLayeredPane1.setLayer(lvl1Btn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lvl2Btn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lvl3Btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(lvl1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lvl2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lvl3Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(580, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(512, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lvl3Btn, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lvl2Btn, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addComponent(lvl1Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(147, 147, 147))
        );

        /*
        lvl1Btn.setOpaque(false);
        lvl1Btn.setContentAreaFilled(false);
        lvl1Btn.setBorderPainted(false);
        */
        /*
        lvl2Btn.setOpaque(false);
        lvl2Btn.setContentAreaFilled(false);
        lvl2Btn.setBorderPainted(false);
        */
        /*
        lvl3Btn.setOpaque(false);
        lvl3Btn.setContentAreaFilled(false);
        lvl3Btn.setBorderPainted(false);
        */

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lvl1BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl1BtnActionPerformed
        // TODO add your handling code here:
        
        System.out.println("Redirecting to Level 1...");
        goTo.accept("Level 1");
        
    }//GEN-LAST:event_lvl1BtnActionPerformed

    private void lvl2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl2BtnActionPerformed
        // TODO add your handling code here:
        
        System.out.println("Redirecting to Level 2...");
        goTo.accept("Level 2");
    }//GEN-LAST:event_lvl2BtnActionPerformed

    private void lvl3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl3BtnActionPerformed
        // TODO add your handling code here:
        
        System.out.println("Redirecting to Level 3...");
        goTo.accept("Level 3");
        
    }//GEN-LAST:event_lvl3BtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton lvl1Btn;
    private javax.swing.JButton lvl2Btn;
    private javax.swing.JButton lvl3Btn;
    // End of variables declaration//GEN-END:variables
}
