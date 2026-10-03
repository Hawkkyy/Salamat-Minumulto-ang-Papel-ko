package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Models.GameResult;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class GameOver extends JPanel {

    private final JLabel msgLbl = new JLabel("", SwingConstants.CENTER);
    private final JButton retryBtn = new JButton("Retry Level");
    private final JButton calBtn = new JButton("Back to Calendar");
    private final JButton titleBtn = new JButton("Return to Title Screen");

    public GameOver(Consumer<String> goTo) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));
        msgLbl.setFont(new Font("SansSerif", Font.BOLD, 48));
        msgLbl.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 12, 12, 12);
        add(msgLbl, gbc);
        add(retryBtn, gbc);
        add(calBtn, gbc);
        add(titleBtn, gbc);

        retryBtn.addActionListener(e -> goTo.accept("Level " + GameResult.level));
        calBtn.addActionListener(e -> goTo.accept("Calendar"));
        titleBtn.addActionListener(e -> goTo.accept("Title Screen"));

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                msgLbl.setText(GameResult.timedOut
                        ? "The sun came up before you finished!"
                        : "Too many mistakes. Game Over.");
            }
        });
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
