/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels;

import java.util.function.Consumer;

public class Level1 extends javax.swing.JPanel {

    
    private Consumer<String> goTo;
    public Level1(Consumer<String> goTo) {
        
        this.goTo = goTo;
        initComponents();
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelUI1 = new css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI();
        levelUI2 = new css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelUI2, javax.swing.GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelUI2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI levelUI1;
    private css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI levelUI2;
    // End of variables declaration//GEN-END:variables
}
