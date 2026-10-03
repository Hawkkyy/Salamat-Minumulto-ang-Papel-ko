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

        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 56));
        scoreLbl.setFont(new Font("SansSerif", Font.PLAIN, 40));
        percentLbl.setFont(new Font("SansSerif", Font.PLAIN, 28));
        for (JLabel l : new JLabel[]{titleLbl, scoreLbl, percentLbl}) l.setForeground(Color.WHITE);

        continueBtn.addActionListener(e -> goTo.accept(passed ? WON_SCREEN : OVER_SCREEN));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 12, 12, 12);
        add(titleLbl, gbc);
        add(scoreLbl, gbc);
        add(percentLbl, gbc);
        add(continueBtn, gbc);

        // GameFrame builds this at startup, so compute when it is shown, not in the constructor
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                int pct = GameResult.maxScore == 0 ? 0
                        : (int) Math.round(100.0 * GameResult.score / GameResult.maxScore);
                passed = !GameResult.timedOut && GameResult.maxScore > 0
                        && GameResult.score >= GameResult.maxScore * PASS_RATIO;

                titleLbl.setText(GameResult.timedOut ? "Time's Up!" : "Level " + GameResult.level + " Complete");
                scoreLbl.setText("Score: " + GameResult.score + " / " + GameResult.maxScore);
                percentLbl.setText(pct + "%");
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
