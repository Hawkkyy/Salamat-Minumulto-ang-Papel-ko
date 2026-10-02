
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class QuestionUI extends javax.swing.JPanel {

    
    public enum Mark { NONE, CHECK, X, TAPE }
    public enum Tool { NONE, PEN, TAPE }

    public static Tool currentTool = Tool.NONE;

    private Mark mark = Mark.NONE;
    private Runnable onChange;
    public void setOnChange(Runnable r) { onChange = r; }
    private final JLabel markLbl = new JLabel("", SwingConstants.CENTER);
    private QuestionData data;
    
    
public QuestionUI(int num, QuestionData q) {
    
        this.data = q;

        Font qFont = new Font("Serif", Font.PLAIN, 16);
        Font cFont = new Font("Serif", Font.BOLD, 15);

        setLayout(new BorderLayout(10, 0));
        setOpaque(false);

        JTextArea questText = new JTextArea(num + ". " + q.question);
        questText.setLineWrap(true);
        questText.setWrapStyleWord(true);
        questText.setEditable(false);
        questText.setFocusable(false);
        questText.setOpaque(false);
        questText.setBorder(null);
        questText.setFont(qFont);

        JPanel choices = new JPanel();
        choices.setOpaque(false);
        choices.setLayout(new BoxLayout(choices, BoxLayout.Y_AXIS));
        choices.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        choices.add(choice("A. " + q.a, cFont));
        choices.add(choice("B. " + q.b, cFont));
        if (q.c != null) choices.add(choice("C. " + q.c, cFont));
        if (q.d != null) choices.add(choice("D. " + q.d, cFont));

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(questText, BorderLayout.NORTH);
        center.add(choices, BorderLayout.CENTER);

        markLbl.setPreferredSize(new Dimension(40, 40));
        markLbl.setFont(new Font("Dialog", Font.BOLD, 28));

        add(center, BorderLayout.CENTER);
        add(markLbl, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        // child components swallow clicks, so the listener goes on every part
        addClickListener(this, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
            }
        });
    }

    private void onClick() {
    switch (currentTool) {
        case PEN:
            // pen writes over anything, including tape
            setMark(mark == Mark.CHECK ? Mark.X : Mark.CHECK);
            break;
        case TAPE:
            // tape only covers an existing mark
            if (mark == Mark.CHECK || mark == Mark.X) {
                setMark(Mark.TAPE);
            }
            break;
        default:
            break;
    }
}

    private void setMark(Mark m) {
    mark = m;
    markLbl.setOpaque(false);
    markLbl.setBackground(null);

    switch (m) {
        case CHECK:
            markLbl.setForeground(new Color(0, 130, 0));
            markLbl.setText("\u2714");
            break;
        case X:
            markLbl.setForeground(new Color(200, 0, 0));
            markLbl.setText("\u2718");
            break;
        case TAPE:
            markLbl.setText("");
            markLbl.setOpaque(true);
            markLbl.setBackground(new Color(245, 245, 235));   // pale tape patch
            break;
        default:
            markLbl.setText("");
    }
    markLbl.repaint();

    if (onChange != null) onChange.run();
}

    public Mark getMark()         { return mark; }
    public boolean isChecked() { return mark == Mark.CHECK || mark == Mark.X; }
    public QuestionData getData() { return data; }

    private JLabel choice(String text, Font f) {
        JLabel l = new JLabel(text);
        l.setFont(f);
        return l;
    }

    private void addClickListener(Component c, MouseAdapter l) {
        c.addMouseListener(l);
        if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                addClickListener(child, l);
            }
        }
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questLbl = new javax.swing.JLabel();
        checkPanel = new javax.swing.JPanel();
        answerLbl = new javax.swing.JLabel();

        javax.swing.GroupLayout checkPanelLayout = new javax.swing.GroupLayout(checkPanel);
        checkPanel.setLayout(checkPanelLayout);
        checkPanelLayout.setHorizontalGroup(
            checkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        checkPanelLayout.setVerticalGroup(
            checkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(answerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(424, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(questLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(answerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel answerLbl;
    private javax.swing.JPanel checkPanel;
    private javax.swing.JLabel questLbl;
    // End of variables declaration//GEN-END:variables
}
