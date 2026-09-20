/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Models;

import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author hawk
 */
public class GreenBallpen extends javax.swing.JPanel {

    private Image ballImg;
    private List<SwitchToolListener> listeners = new ArrayList<>();
    private SwitchToolListener switchToolListen;
    
    
    public GreenBallpen() {
        try {
            ballImg = ImageIO.read(getClass().getResource("/Art/Models/GreenBallpen/BallpenImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        setOpaque(false);
        initComponents();
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if (listeners.isEmpty() == false ){
                    triggerSwitch("BALLPEN");
                }
                
            }
            
        });
        
    }
    
    
    //////////////////////////////////////////////////

    public void addSwitchToolListener(SwitchToolListener l){
        listeners.add(l);
    }
    
    public void removeSwitchToolListener(SwitchToolListener l){
        listeners.remove(l);
    }
    
    public void triggerSwitch(String toolSelected){
        
        System.out.println("BALLPEN SELECTED");
        
        SwitchTool evt = new SwitchTool(this, toolSelected);
        for (SwitchToolListener listener : listeners) {
            listener.onToolSelected(evt);
        }
        
    }
    
    public void setToolSelected(SwitchToolListener l){
        this.switchToolListen = l;
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (ballImg != null) {
            g.drawImage(ballImg, 0, 0, getWidth(), getHeight(), this);
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
