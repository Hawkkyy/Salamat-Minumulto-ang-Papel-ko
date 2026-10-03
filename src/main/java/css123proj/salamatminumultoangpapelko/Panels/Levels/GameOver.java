package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Models.GameResult;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class GameOver extends JPanel {
    
    private final JPanel reactionPanel = new JPanel(new GridBagLayout());
    private final JLabel reactionImageLbl = new JLabel("[ Reaction Image Placeholder ]", SwingConstants.CENTER);
    
    private final JPanel summaryPanel = new JPanel(new GridBagLayout());
    private final JLabel statusLbl = new JLabel("", SwingConstants.CENTER);
    private final JLabel commentLbl = new JLabel("", SwingConstants.CENTER);
    private final JButton retryBtn = new JButton("Retry Level");
    private final JButton calBtn = new JButton("Back to Calendar");
    private final JButton titleBtn = new JButton("Return to Title Screen");

    public GameOver(Consumer<String> goTo) {
        setLayout(new GridBagLayout());
        setBackground(new Color(25, 25, 50));
        
        reactionPanel.setBackground(new Color(40, 40, 50));
        reactionPanel.setPreferredSize(new Dimension(500, 220));
        reactionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(250, 115, 110), 2, true),
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
        statusLbl.setForeground(new Color(250, 115, 110));
        
        commentLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        commentLbl.setForeground(new Color(200, 205, 210));
        
        styleButton(retryBtn, new Color(220, 75, 75), new Color(180, 50, 50));
        styleButton(calBtn, new Color(60, 130, 250), new Color(40, 100, 240));
        styleButton(titleBtn, new Color(80, 85, 95), new Color(60, 65, 75));
        
        GridBagConstraints gbcSummary = new GridBagConstraints();
        gbcSummary.gridx = 0;
        gbcSummary.fill = GridBagConstraints.HORIZONTAL;
        gbcSummary.anchor = GridBagConstraints.CENTER;
        
        gbcSummary.insets = new Insets(5, 0, 10, 0);
        summaryPanel.add(statusLbl, gbcSummary);
        
        gbcSummary.insets = new Insets(0, 0, 20, 0);
        summaryPanel.add(commentLbl, gbcSummary);
        
        gbcSummary.insets = new Insets(6, 0, 6, 0);
        gbcSummary.fill = GridBagConstraints.NONE;
        summaryPanel.add(retryBtn, gbcSummary);
        summaryPanel.add(calBtn, gbcSummary);
        summaryPanel.add(titleBtn, gbcSummary);
        
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.insets = new Insets(15, 20, 15, 20);
        
        add(reactionPanel, mainGbc);
        add(summaryPanel, mainGbc);
        
        retryBtn.addActionListener(e -> goTo.accept("Level " + GameResult.level));
        calBtn.addActionListener(e -> goTo.accept("Calendar"));
        titleBtn.addActionListener(e -> goTo.accept("Title Screen"));
        
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                if (GameResult.timedOut) {
                    statusLbl.setText("Time's Up!");
                    commentLbl.setText("The sun came up before you finished grading!");
                } else {
                    statusLbl.setText("Level Failed");
                    commentLbl.setText("Too many mistakes made. Be extra careful with the answer key!");
                }
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
