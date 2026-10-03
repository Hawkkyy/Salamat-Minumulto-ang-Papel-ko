package css123proj.salamatminumultoangpapelko.Panels;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Calendar extends JPanel {

    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;

    // set to true to see the button areas while positioning them
    private static final boolean DEBUG = true;

    private final Consumer<String> goTo;
    private Image bg;

    // button areas in the 1920 x 1080 design space: x, y, w, h
    private final Rectangle r1 = new Rectangle(250, 970, 150, 140);
    private final Rectangle r2 = new Rectangle(570, 970, 140, 140);
    private final Rectangle r3 = new Rectangle(870, 980, 130, 120);

    public Calendar(Consumer<String> goTo) {
        this.goTo = goTo;
        setLayout(null);
        
        lvl1Btn = new JButton();
        lvl2Btn = new JButton();
        lvl3Btn = new JButton();


        try {
            bg = ImageIO.read(getClass().getResource("/Art/Backgrounds/CalendarImg.jpg"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        setupButton(lvl1Btn, "Level 1");
        setupButton(lvl2Btn, "Level 2");
        setupButton(lvl3Btn, "Level 3");

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionElements();
            }
        });
    }

    private void setupButton(JButton b, String screen) {
        if (!DEBUG) {
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        }
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(e -> {
            System.out.println("Redirecting to " + screen + "...");
            goTo.accept(screen);
        });
        add(b);
    }

    private void repositionElements() {
        double sx = getWidth() / BASE_WIDTH;
        double sy = getHeight() / BASE_HEIGHT;
        if (sx == 0 || sy == 0) return;

        place(lvl1Btn, r1, sx, sy);
        place(lvl2Btn, r2, sx, sy);
        place(lvl3Btn, r3, sx, sy);
        repaint();
    }

    private void place(JButton b, Rectangle r, double sx, double sy) {
        b.setBounds((int) (r.x * sx), (int) (r.y * sy),
                    (int) (r.width * sx), (int) (r.height * sy));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg == null) return;

        double sx = getWidth() / BASE_WIDTH;
        double sy = getHeight() / BASE_HEIGHT;

        // same placement your old label had (40, -100, 1930 x 2100), scaled to the screen
        g.drawImage(bg,
                (int) (40 * sx), (int) (-100 * sy),
                (int) (1930 * sx), (int) (2100 * sy), this);
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
        AudioSettings.playSfx("Confirm.wav");
        System.out.println("Redirecting to Level 1...");
        goTo.accept("Level 1");
        
    }//GEN-LAST:event_lvl1BtnActionPerformed

    private void lvl2BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl2BtnActionPerformed
        // TODO add your handling code here:
        AudioSettings.playSfx("Confirm.wav");
        System.out.println("Redirecting to Level 2...");
        goTo.accept("Level 2");
    }//GEN-LAST:event_lvl2BtnActionPerformed

    private void lvl3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvl3BtnActionPerformed
        // TODO add your handling code here:
        AudioSettings.playSfx("Confirm.wav");
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
