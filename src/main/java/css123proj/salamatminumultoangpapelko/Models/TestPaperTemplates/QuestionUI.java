
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;
import css123proj.salamatminumultoangpapelko.Panels.AudioSettings;
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
    
    private String studentChoice;      // "A.", "B.", "C." or "D."
    private boolean studentCorrect;    // was the student actually right?
    
    public static Tool currentTool = Tool.NONE;

    private Mark mark = Mark.NONE;
    private Runnable onChange;
    public void setOnChange(Runnable r) { onChange = r; }
    private final JLabel markLbl = new JLabel("", SwingConstants.CENTER);
    private QuestionData data;
    private JTextArea questText;
    private JPanel choices;
    private int lastWidth = 0;
    
    private static final Font HAND_FONT = new Font("Serif", Font.ITALIC, 26);
    
    private static final int PREMARK_CORRECT = 40;   // % of items already graded correctly
    private static final int PREMARK_WRONG   = 30;   // % of items already graded wrongly (rest are blank)
    
    private final JLabel studentLbl = new JLabel("", SwingConstants.CENTER);        

    
    public QuestionUI(int num, QuestionData q) {
    
        this.data = q;
        
        java.util.List<String> letters = new java.util.ArrayList<>(java.util.Arrays.asList("A.", "B."));
            if (q.c != null) letters.add("C.");
            if (q.d != null) letters.add("D.");

        String correct = q.answer.endsWith(".") ? q.answer : q.answer + ".";   // fixes the "C" typo in the JSON
        java.util.Random rnd = new java.util.Random();

        if (rnd.nextInt(100) < 70) {
            studentChoice = correct;                       // 70%: student is right
        } else {
            letters.remove(correct);
            studentChoice = letters.get(rnd.nextInt(letters.size()));   // 30%: student picks a wrong one
        }
        studentCorrect = studentChoice.equals(correct);

        Font qFont = new Font("Serif", Font.PLAIN, 16);
        Font cFont = new Font("Serif", Font.BOLD, 15);

        setLayout(new BorderLayout(10, 0));
        setOpaque(false);

        questText = new JTextArea(num + ". " + q.question);
        questText.setLineWrap(true);
        questText.setWrapStyleWord(true);
        questText.setEditable(false);
        questText.setFocusable(false);
        questText.setOpaque(false);
        questText.setBorder(null);
        questText.setFont(qFont);

        choices = new JPanel();
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

        markLbl.setPreferredSize(new Dimension(40, 40));
        markLbl.setFont(new Font("Dialog", Font.BOLD, 28));

        add(center, BorderLayout.CENTER);
        add(markLbl, BorderLayout.EAST);
        
        JPanel choiceRow = new JPanel(new BorderLayout());
        choiceRow.setOpaque(false);
        choiceRow.add(choices, BorderLayout.WEST);

        studentLbl.setHorizontalAlignment(SwingConstants.LEFT);
        studentLbl.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));   // distance from the choices
        choiceRow.add(studentLbl, BorderLayout.CENTER);

        center.add(choiceRow, BorderLayout.CENTER);
        
        studentLbl.setText(studentChoice.replace(".", ""));   // "B." becomes "B"
        studentLbl.setFont(HAND_FONT);
        studentLbl.setForeground(new Color(20, 40, 140));     // pen-ink blue
        studentLbl.setVerticalAlignment(SwingConstants.TOP);

        
        
        setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        // child components swallow clicks, so the listener goes on every part
        addClickListener(this, new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        onClick(e);
    }
});
        
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                if (getWidth() != lastWidth) {   // only re-measure when the width changes
                lastWidth = getWidth();
                revalidate();
                }
            }
        });
        
        int roll = rnd.nextInt(100);
if (roll < PREMARK_CORRECT) {
    setMark(studentCorrect ? Mark.CHECK : Mark.X);          // the "teacher" graded it right
} else if (roll < PREMARK_CORRECT + PREMARK_WRONG) {
    setMark(studentCorrect ? Mark.X : Mark.CHECK);          // the "teacher" made a mistake
}                                                           // otherwise left blank
        
    }

    private void onClick(MouseEvent e) {
    boolean left  = SwingUtilities.isLeftMouseButton(e);
    boolean right = SwingUtilities.isRightMouseButton(e);

    switch (currentTool) {
        case PEN:
            // the pen can only write on a blank or taped spot
            if (mark == Mark.NONE || mark == Mark.TAPE) {
                if (left)       { setMark(Mark.CHECK); AudioSettings.playSfx("/SFX/pen.wav"); }
                else if (right) { setMark(Mark.X);     AudioSettings.playSfx("/SFX/pen.wav"); }   // right click = wrong
            }
            break;
        case TAPE:
            // the tape only covers an existing mark, left click only
            if (left && (mark == Mark.CHECK || mark == Mark.X)) {
                setMark(Mark.TAPE);
                AudioSettings.playSfx("/SFX/tape.wav");    // right click = wrong
            
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
    
    private String mark(String letter) {
    return letter.equals(studentChoice) ? "\u25CF " : "   ";   // ● next to the student's pick
    }

    public Mark getMark()         { return mark; }
    public boolean isChecked() { return mark == Mark.CHECK || mark == Mark.X; }
    public QuestionData getData() { return data; }

    private JLabel choice(String text, Font f) {
        JLabel l = new JLabel(text);
        l.setFont(f);
        return l;
    }
    
    public boolean isStudentCorrect() { return studentCorrect; }

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
    
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        if (getWidth() > 0 && questText != null) {
           int textWidth = getWidth() - 40 - 10 - 12; 
            questText.setSize(textWidth, Short.MAX_VALUE);
            int textHeight = questText.getPreferredSize().height;
            d.height = textHeight + Math.max(choices.getPreferredSize().height, 40) + 12;
        }
        return d;
    }
    
    public String getCorrectLetter() {
        String a = data.answer;
        return (a.endsWith(".") ? a : a + ".").replace(".", "");   // "C" typo in the JSON is handled
    }
    
   // private static final Font HAND_FONT = loadHandFont();

private static Font loadHandFont() {
    try (java.io.InputStream in = QuestionUI.class.getResourceAsStream("/YourFont.ttf")) {
        return Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(26f);
    } catch (Exception e) {
        return new Font("Serif", Font.ITALIC, 26);   // fallback if the file is missing
    }
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
