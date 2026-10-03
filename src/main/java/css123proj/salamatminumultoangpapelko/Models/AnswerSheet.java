package css123proj.salamatminumultoangpapelko.Models;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AnswerSheet extends JPanel {

    private final Image ansKeyImg;
    private final Image paperImg;
    private boolean expanded = false;
    private double scale = 1.0;

    private final JLabel titleLbl = new JLabel("ANSWER KEY", SwingConstants.CENTER);
    private final JButton prevBtn = new JButton("<");
    private final JButton nextBtn = new JButton(">");
    private final JPanel header = new JPanel(new BorderLayout());
    private final JPanel list = new JPanel();
    private final JScrollPane scroll = new JScrollPane(list,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private List<List<String>> keys = new ArrayList<>();
    private int shown = 0;

    public AnswerSheet() {
        ansKeyImg = load("/Art/Models/AnswerKey/AnswerSheetImg.png");
        paperImg = load("/Art/Models/TestPaper/TestPaperImg.png");

        setOpaque(false);
        setLayout(new BorderLayout());

        header.setOpaque(false);
        header.add(prevBtn, BorderLayout.WEST);
        header.add(titleLbl, BorderLayout.CENTER);
        header.add(nextBtn, BorderLayout.EAST);

        list.setOpaque(false);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        prevBtn.addActionListener(e -> showSet(shown - 1));
        nextBtn.addActionListener(e -> showSet(shown + 1));

        setExpanded(false);
    }

    private Image load(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int px(int base) { return Math.max(1, (int) Math.round(base * scale)); }

    private Font lineFont() { return new Font("Serif", Font.PLAIN, px(expanded ? 30 : 20)); }

    private void applyLook() {
        titleLbl.setFont(new Font("Serif", Font.BOLD, px(expanded ? 34 : 22)));
        Font bf = new Font("SansSerif", Font.BOLD, px(26));
        prevBtn.setFont(bf);
        nextBtn.setFont(bf);
        Font f = lineFont();
        for (Component c : list.getComponents()) c.setFont(f);
        // bottom border leaves room for the Confirm button that LevelUI draws over the sheet
        setBorder(BorderFactory.createEmptyBorder(px(expanded ? 100 : 60), px(60),
                expanded ? Math.max(80, px(100)) : px(40), px(60)));
        revalidate();
        repaint();
    }

    public void setScale(double s) {
        if (Math.abs(s - scale) < 0.001) return;
        scale = s;
        applyLook();
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        header.setVisible(expanded);      // text only shows when the sheet is open
        scroll.setVisible(expanded);
        applyLook();
    }

    /** Call whenever a new level or paper is loaded. currentSet is 1 or 2 (the set of the paper on the desk). */
    public void setKeys(List<List<String>> keys, int currentSet) {
        this.keys = keys;
        showSet(currentSet - 1);
    }

    private void showSet(int index) {
        if (keys.isEmpty()) return;
        shown = Math.max(0, Math.min(keys.size() - 1, index));
        titleLbl.setText("ANSWER KEY - SET " + (char) ('A' + shown));

        boolean several = keys.size() > 1;
        prevBtn.setVisible(several);
        nextBtn.setVisible(several);
        prevBtn.setEnabled(shown > 0);
        nextBtn.setEnabled(shown < keys.size() - 1);

        list.removeAll();
        Font f = lineFont();
        for (String line : keys.get(shown)) {
            JLabel l = new JLabel(line);
            l.setFont(f);
            l.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
            list.add(l);
        }
        list.revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> scroll.getVerticalScrollBar().setValue(0));
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
