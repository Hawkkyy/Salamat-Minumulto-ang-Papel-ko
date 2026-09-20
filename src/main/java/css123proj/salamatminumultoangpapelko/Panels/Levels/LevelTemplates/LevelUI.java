/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates;

import css123proj.salamatminumultoangpapelko.Models.*;
import css123proj.salamatminumultoangpapelko.Models.Menu.MenuButton;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.*;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author hawk
 */
public class LevelUI extends JPanel implements SwitchToolListener{

    private Image levelImg;
    TestPaperUI testPaper;
    AnswerSheet ansKey;
    Clock clock;
    CorrectionTape corTape;
    GreenBallpen ballpen;
    TestPaperStack papStack;
    MenuButton menu;
    
    public LevelUI() {
        
        setLayout(null);
        
        try {
            levelImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/LevelImg.jpg"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        testPaper = new TestPaperUI();
        ansKey = new AnswerSheet();
        clock = new Clock();
        corTape = new CorrectionTape();
        ballpen = new GreenBallpen();
        papStack = new TestPaperStack();
        menu = new MenuButton();
        
        ////
        
        ballpen.addSwitchToolListener(this);
        corTape.addSwitchToolListener(this);
        
        // x y w h
        
        testPaper.setBounds(550, 100, 650, 900);
        ansKey.setBounds(0, 100, 550, 1000);
        clock.setBounds(1530, 30, 350, 160);
        
        ballpen.setBounds(1300, 700, 100, 200);
        corTape.setBounds(1400, 700, 100, 200);
        papStack.setBounds(1600, 500, 600, 900);
        
        menu.setBounds(1625, 30, 200, 50);
        
        
        add(testPaper);
        add(ansKey);
        add(corTape);
        add(ballpen);
        add(papStack);
        add(menu);
        add(clock);
    }
    
    
    
    @Override
    public void onToolSelected(SwitchTool evt){
        System.out.println("Switched to "+evt.getSelectedTool());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (levelImg != null) {
            g.drawImage(levelImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
