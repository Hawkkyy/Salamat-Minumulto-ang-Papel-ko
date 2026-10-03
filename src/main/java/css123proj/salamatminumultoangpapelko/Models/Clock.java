/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Models;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;




public class Clock extends javax.swing.JPanel {

    private Image clockImg;
    private javax.swing.Timer timer;
private int totalSeconds = 300;
private int secondsLeft = 300;
private boolean running;
private Runnable onTimeUp;

public void setOnTimeUp(Runnable r) { onTimeUp = r; }
public int getSecondsLeft() { return secondsLeft; }

/** Sets the clock to full time but does not start counting. */
public void prepare(int seconds) {
    stop();
    timer = null;
    totalSeconds = seconds;
    secondsLeft = seconds;
    repaint();
}

/** Starts counting down from whatever prepare() set. */
public void begin() {
    if (timer != null) timer.stop();
    timer = new javax.swing.Timer(1000, e -> {
        secondsLeft--;
        repaint();
        if (secondsLeft <= 0) {
            stop();
            if (onTimeUp != null) onTimeUp.run();
        }
    });
    running = true;
    timer.start();
}

public void start(int seconds) { prepare(seconds); begin(); }

public void stop() {
    running = false;
    if (timer != null) timer.stop();
}

public void pause()  { if (timer != null) timer.stop(); }
public void resume() { if (timer != null && running) timer.start(); }

/** 11:00 PM at the start, 4:00 AM at the end (5 in-game hours over the whole level). */
private String gameTime() {
    int elapsedSec = totalSeconds - Math.max(0, secondsLeft);
    int minutes = 23 * 60 + (int) Math.round(elapsedSec * 300.0 / totalSeconds);
    int h24 = (minutes / 60) % 24;
    int m = minutes % 60;
    int h12 = (h24 % 12 == 0) ? 12 : h24 % 12;
    return String.format("%d:%02d %s", h12, m, h24 < 12 ? "AM" : "PM");
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (clockImg != null) {
        g.drawImage(clockImg, 0, 0, getWidth(), getHeight(), this);
    }
    String t = gameTime();
    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, Math.max(14, getHeight() / 4)));
    g2.setColor(secondsLeft <= 30 ? java.awt.Color.RED : java.awt.Color.BLACK);
    java.awt.FontMetrics fm = g2.getFontMetrics();
    g2.drawString(t, (getWidth() - fm.stringWidth(t)) / 2,
            (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
    g2.dispose();
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
