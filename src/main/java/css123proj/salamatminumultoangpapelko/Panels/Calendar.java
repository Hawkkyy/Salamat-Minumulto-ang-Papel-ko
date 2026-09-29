/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels;

import java.util.function.Consumer;


public class Calendar extends javax.swing.JPanel {

    
    private Consumer<String> goTo;
    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;     
        
    public Calendar(Consumer<String> goTo) {
        
        this.goTo = goTo;
        initComponents();
        
        
        
        repositionElements();
        
        
        
    }

    private void repositionElements() {
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;

        double sx = w / BASE_WIDTH;
        double sy = h / BASE_HEIGHT;

        bgPane.setBounds((int)(0 * sx), (int)(100 * sy), (int)(550 * sx), (int)(1000 * sy));


        revalidate();
        repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgPane = new javax.swing.JLayeredPane();
        lvl1Btn = new javax.swing.JButton();
        lvl2Btn = new javax.swing.JButton();
        lvl3Btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        lvl1Btn.setText("    ");
        lvl1Btn.setOpaque(true);
        lvl1Btn.addActionListener(this::lvl1BtnActionPerformed);
        bgPane.add(lvl1Btn);
        lvl1Btn.setBounds(250, 970, 150, 140);
        /*
        lvl1Btn.setOpaque(false);
        lvl1Btn.setContentAreaFilled(false);
        lvl1Btn.setBorderPainted(false);
        */

        lvl2Btn.setText("    ");
        lvl2Btn.setOpaque(true);
        lvl2Btn.addActionListener(this::lvl2BtnActionPerformed);
        bgPane.add(lvl2Btn);
        lvl2Btn.setBounds(570, 970, 140, 140);
        /*
        lvl2Btn.setOpaque(false);
        lvl2Btn.setContentAreaFilled(false);
        lvl2Btn.setBorderPainted(false);
        */

        lvl3Btn.setText("     ");
        lvl3Btn.setOpaque(true);
        lvl3Btn.addActionListener(this::lvl3BtnActionPerformed);
        bgPane.add(lvl3Btn);
        lvl3Btn.setBounds(870, 980, 130, 120);
        /*
        lvl3Btn.setOpaque(false);
        lvl3Btn.setContentAreaFilled(false);
        lvl3Btn.setBorderPainted(false);
        */

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Art/Backgrounds/CalendarImg.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        bgPane.add(jLabel1);
        jLabel1.setBounds(40, -100, 1930, 2100);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bgPane)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bgPane)
                .addContainerGap())
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
    private javax.swing.JLayeredPane bgPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton lvl1Btn;
    private javax.swing.JButton lvl2Btn;
    private javax.swing.JButton lvl3Btn;
    // End of variables declaration//GEN-END:variables
}
