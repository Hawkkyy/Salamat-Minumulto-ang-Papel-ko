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
    private int remaining = 5;
    private int total = 5;
    
    public TestPaperStack() {
        
        try {
            paperStackImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaperStack/TestPaperStackImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        setOpaque(false);
        initComponents();
        
    }

    
   

public void setCount(int remaining, int total) {
    this.remaining = remaining;
    this.total = total;
    repaint();
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (paperStackImg != null && remaining > 0) {
        int h = getHeight();
        double frac = 0.25 + 0.75 * remaining / (double) total;   // never fully flat while papers remain
        int visH = (int) (h * frac);
        int imgH = paperStackImg.getHeight(null);
        int srcTop = imgH - (int) (imgH * frac);
        // draw only the bottom part of the image so the pile gets shorter
        g.drawImage(paperStackImg, 0, h - visH, getWidth(), h,
                0, srcTop, paperStackImg.getWidth(null), imgH, this);
    }
    g.setColor(java.awt.Color.WHITE);
    g.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
    g.drawString(remaining + " left", 10, 24);
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
