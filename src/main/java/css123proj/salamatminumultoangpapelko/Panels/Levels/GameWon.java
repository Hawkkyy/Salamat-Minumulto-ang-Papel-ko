package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Models.GameResult;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class GameWon extends JPanel {

    private final JLabel msgLbl = new JLabel("", SwingConstants.CENTER);
    private final JButton nextBtn = new JButton();
    private final JButton titleBtn = new JButton("Return to Title Screen");

    public GameWon(Consumer<String> goTo) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));
        msgLbl.setFont(new Font("SansSerif", Font.BOLD, 48));
        msgLbl.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 12, 12, 12);
        add(msgLbl, gbc);
        add(nextBtn, gbc);
        add(titleBtn, gbc);

        nextBtn.addActionListener(e ->
                goTo.accept(GameResult.level >= 3 ? "Ending Cutscene" : "Calendar"));
        titleBtn.addActionListener(e -> goTo.accept("Title Screen"));

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                boolean last = GameResult.level >= 3;
                msgLbl.setText(last ? "You graded every paper before sunrise!"
                                    : "Level " + GameResult.level + " passed!");
                nextBtn.setText(last ? "Continue" : "Back to Calendar");
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
