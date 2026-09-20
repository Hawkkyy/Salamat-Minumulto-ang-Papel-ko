/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Models;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author hawk
 */
public class TestPaperStack extends javax.swing.JPanel {

    
    private Image paperStackImg;
    
    public TestPaperStack() {
        try {
            paperStackImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaperStack/TestPaperStackImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        setOpaque(false);
        initComponents();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (paperStackImg != null) {
            g.drawImage(paperStackImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
