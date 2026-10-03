package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Models.GameResult;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class Score extends JPanel {

    // must match the names GameFrame registers the screens under
    private static final String WON_SCREEN  = "Game Won";
    private static final String OVER_SCREEN = "Game Over";
    private static final double PASS_RATIO  = 0.6;

    private final JLabel titleLbl = new JLabel("", SwingConstants.CENTER);
    private final JLabel scoreLbl = new JLabel("", SwingConstants.CENTER);
    private final JLabel percentLbl = new JLabel("", SwingConstants.CENTER);
    private final JButton continueBtn = new JButton("Continue");
    private boolean passed;

    public Score(Consumer<String> goTo) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));

        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(new Color(30, 35, 40));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 2, true),
                BorderFactory.createEmptyBorder(30, 70, 30, 70)
        ));

        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 60));
        scoreLbl.setFont(new Font("SansSerif", Font.BOLD, 30));
        percentLbl.setFont(new Font("SansSerif", Font.BOLD, 50));

        titleLbl.setForeground(new Color(240, 240, 240));
        scoreLbl.setForeground(new Color(180, 185, 190));

        continueBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setBackground(new Color(60, 130, 250));
        continueBtn.setFocusPainted(false);
        continueBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 100, 240), 2, true),
                BorderFactory.createEmptyBorder(10, 35, 10, 35)
        ));

        continueBtn.addActionListener(e -> goTo.accept(passed ? WON_SCREEN : OVER_SCREEN));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 0, 10, 0);
        cardPanel.add(titleLbl, gbc);

        gbc.insets = new Insets(10, 0, 5, 0);
        cardPanel.add(scoreLbl, gbc);

        gbc.insets = new Insets(0, 0, 25, 0);
        cardPanel.add(percentLbl, gbc);

        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        cardPanel.add(continueBtn, gbc);

        add(cardPanel);

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                int pct = GameResult.maxScore == 0 ? 0
                        : (int) Math.round(100.0 * GameResult.score / GameResult.maxScore);
                passed = !GameResult.timedOut && GameResult.maxScore > 0
                        && GameResult.score >= GameResult.maxScore * PASS_RATIO;

                titleLbl.setText(GameResult.timedOut ? "Time's Up!" : "Level " + GameResult.level + " Complete");
                scoreLbl.setText("Score: " + GameResult.score + " / " + GameResult.maxScore);
                percentLbl.setText(pct + "%");

                if (passed) {
                    percentLbl.setForeground(new Color(75, 225, 130));
                } else {
                    percentLbl.setForeground(new Color(250, 115, 110));
                }
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
