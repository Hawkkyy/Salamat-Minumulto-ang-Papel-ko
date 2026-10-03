package css123proj.salamatminumultoangpapelko.Models;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AnswerSheet extends JPanel {

    private Image ansKeyImg;
    private Image paperImg;
    private boolean expanded = false;
    private final JLabel titleLbl = new JLabel("ANSWER KEY", SwingConstants.CENTER);
    private final JPanel list = new JPanel();

    public AnswerSheet() {
        try {
            ansKeyImg = ImageIO.read(getClass().getResource("/Art/Models/AnswerKey/AnswerSheetImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(60, 40, 40, 40));   // keeps text inside the paper art

        titleLbl.setFont(new Font("Serif", Font.BOLD, 22));

        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        add(titleLbl, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        
        titleLbl.setVisible(false);
list.setVisible(false);
        
        try {
    paperImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaper/TestPaperImg.png"));
} catch (IOException | IllegalArgumentException e) {
    e.printStackTrace();
}
        
    }
    
    private Font lineFont() {
    return new Font("Serif", Font.PLAIN, expanded ? 30 : 20);
}

    public void setExpanded(boolean expanded) {
    this.expanded = expanded;
    titleLbl.setVisible(expanded);     // text only shows when the sheet is open
    list.setVisible(expanded);
    titleLbl.setFont(new Font("Serif", Font.BOLD, expanded ? 34 : 22));
    for (Component c : list.getComponents()) c.setFont(lineFont());
    setBorder(BorderFactory.createEmptyBorder(expanded ? 100 : 60, 40, 40, 40));
    revalidate();
    repaint();
}

    // call this whenever a new paper is loaded
    public void setKey(int set, List<String> answers) {
        titleLbl.setText("ANSWER KEY - SET " + (char) ('A' + set - 1));
        list.removeAll();

        Font f = lineFont();
        for (String line : answers) {
            JLabel l = new JLabel(line);
            l.setFont(f);
            l.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
            list.add(l);
        }
        revalidate();
        repaint();
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image bg = (expanded && paperImg != null) ? paperImg : ansKeyImg;
    if (bg != null) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
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
