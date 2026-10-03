package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Models.GameResult;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class GameWon extends JPanel {
    
    private final JPanel reactionPanel = new JPanel(new GridBagLayout());
    private final JLabel reactionImageLbl = new JLabel("[ Reaction Image Placeholder ]", SwingConstants.CENTER);
    
    private final JPanel summaryPanel = new JPanel(new GridBagLayout());
    private final JLabel statusLbl = new JLabel("", SwingConstants.CENTER);
    private final JLabel commentLbl = new JLabel("", SwingConstants.CENTER);
    private final JButton nextBtn = new JButton();
    private final JButton titleBtn = new JButton("Return to Title Screen");
    
    public GameWon(Consumer<String> goTo) {
        setLayout(new GridBagLayout());
        setBackground(new Color(25, 25, 50));
        
        reactionPanel.setBackground(new Color(40, 40, 50));
        reactionPanel.setPreferredSize(new Dimension(500, 220));
        reactionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(75, 225, 130), 2, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        
        reactionImageLbl.setFont(new Font("SansSerif", Font.ITALIC, 16));
        reactionImageLbl.setForeground(new Color(150, 160, 175));
        
        GridBagConstraints gbcReaction = new GridBagConstraints();
        gbcReaction.gridx = 0;
        gbcReaction.gridy = 0;
        gbcReaction.anchor = GridBagConstraints.CENTER;
        reactionPanel.add(reactionImageLbl, gbcReaction);
        
        summaryPanel.setBackground(new Color(40, 40, 50));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                BorderFactory.createEmptyBorder(25, 40, 25, 40)
        ));
        
        statusLbl.setFont(new Font("SansSerif", Font.BOLD, 36));
        statusLbl.setForeground(new Color(75, 225, 130));
        
        commentLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        commentLbl.setForeground(new Color(200, 205, 210));
        
        styleButton(nextBtn, new Color(60, 130, 250), new Color(40, 100, 240));
        styleButton(titleBtn, new Color(80, 85, 95), new Color(60, 65, 75));
        
        GridBagConstraints gbcSummary = new GridBagConstraints();
        gbcSummary.gridx = 0;
        gbcSummary.fill = GridBagConstraints.HORIZONTAL;
        gbcSummary.anchor = GridBagConstraints.CENTER;
        
        gbcSummary.insets = new Insets(5, 0, 10, 0);
        summaryPanel.add(statusLbl, gbcSummary);
        
        gbcSummary.insets = new Insets(0, 0, 20, 0);
        summaryPanel.add(commentLbl, gbcSummary);
        
        gbcSummary.insets = new Insets(8, 0, 8, 0);
        gbcSummary.fill = GridBagConstraints.NONE;
        summaryPanel.add(nextBtn, gbcSummary);
        summaryPanel.add(titleBtn, gbcSummary);
        
        // Main Layout Structure
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.insets = new Insets(15, 20, 15, 20);
        
        add(reactionPanel, mainGbc);
        add(summaryPanel, mainGbc);
        
        nextBtn.addActionListener(e ->
                goTo.accept(GameResult.level >= 3 ? "Ending Cutscene" : "Calendar"));
        titleBtn.addActionListener(e -> goTo.accept("Title Screen"));
        
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                boolean last = GameResult.level >= 3;
                
                statusLbl.setText(last ? "You graded every paper before sunrise!" : "Level " + GameResult.level + " Passed!");
                commentLbl.setText(last ? "Outstanding work! The professor woke up refreshed!" : "Great job! Keep up the good work!");
                nextBtn.setText(last ? "Continue" : "Back to Calendar");
            }
        });
    }

    private void styleButton(JButton btn, Color bg, Color border) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(border, 2, true),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
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
