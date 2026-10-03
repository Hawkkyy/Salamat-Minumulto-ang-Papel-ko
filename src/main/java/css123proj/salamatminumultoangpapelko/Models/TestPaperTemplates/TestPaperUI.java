
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class TestPaperUI extends JPanel implements SwitchToolListener{

    
    private Image testPaperImg;
    private JScrollPane scroll;
    private JPanel questArea, top;
    private final List<QuestionUI> rows = new ArrayList<>();
    int level = 1,set;
    private int totalScore = 0;
    private static final int PAPERS_PER_LEVEL = 5;
    private int papersDone = 0;
    private int maxScore = 0;
    private java.util.function.BiConsumer<Integer, Boolean> onFinished;   // (totalScore, levelOver)
    private final List<List<QuestionData>> sets = new ArrayList<>();   // fixed question sets for this level run
    private double scale = 1.0;
    
    private boolean paperActive = true;
    
    public void setOnFinished(java.util.function.BiConsumer<Integer, Boolean> c) { onFinished = c; }
    public int getMaxScore() { return maxScore; }
    public int getTotalScore() { return totalScore; }

public void reset() {
    totalScore = 0;
    maxScore = 0;
    papersDone = 0;
    paperActive = true;
    setVisible(true);
    buildSets();
    loadPaper();
}

    public int getPapersPerLevel() { return PAPERS_PER_LEVEL; }

public int getPapersLeftInStack() {
    int taken = papersDone + (paperActive ? 1 : 0);
    return PAPERS_PER_LEVEL - taken;
}

public boolean nextPaper() {
    if (paperActive || papersDone >= PAPERS_PER_LEVEL) return false;
    loadPaper();
    paperActive = true;
    setVisible(true);
    return true;
}
    
    public TestPaperUI(int level) {
        
        this.level = level;
        
        initComponents();
        
        try {
            testPaperImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaper/TestPaperImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        
        setLayout(new BorderLayout());
        setOpaque(false);
        
        top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(headerLbl, BorderLayout.CENTER);
        top.add(finishBtn, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(questHolder, BorderLayout.CENTER);
        
        questHolder.setOpaque(false);
    
        questArea = new ScrollPanel();
        questArea.setOpaque(false);
        questArea.setLayout(new BoxLayout(questArea, BoxLayout.Y_AXIS));
    
    
        scroll = new JScrollPane(questArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        questHolder.setLayout(new BorderLayout());
        questHolder.add(scroll, BorderLayout.CENTER);
        
        
        loadPaper();

        finishBtn.addActionListener(e -> {
        int score = 0;
    for (QuestionUI row : rows) {
        boolean playerSaysCorrect = row.getMark() == QuestionUI.Mark.CHECK;
        score += (playerSaysCorrect == row.isStudentCorrect()) ? 1 : -1;
    }
    totalScore += score;
    maxScore += rows.size();
    papersDone++;
    finishBtn.setSelected(false);

    boolean levelOver = papersDone >= PAPERS_PER_LEVEL;
    if (!levelOver) {
        paperActive = false;   // paper leaves the desk until the player clicks the stack
        setVisible(false);
    }
    if (onFinished != null) onFinished.accept(totalScore, levelOver);
});
            
        addComponentListener(new ComponentAdapter() {
    @Override
    public void componentResized(ComponentEvent e) {
        applyScale();
    }
});
        
        
        
}        
    


    private static class ScrollPanel extends JPanel implements Scrollable {
    public java.awt.Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
    public int getScrollableUnitIncrement(java.awt.Rectangle r, int o, int d) { return 16; }
    public int getScrollableBlockIncrement(java.awt.Rectangle r, int o, int d) { return r.height; }
    public boolean getScrollableTracksViewportWidth() { return true; }   // the important one
    public boolean getScrollableTracksViewportHeight() { return false; }
}
    
    
    
    
    @Override
    public void onToolSelected(SwitchTool evt){
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (testPaperImg != null) {
            g.drawImage(testPaperImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    private void updateFinishButton() {
    boolean allChecked = true;
    for (QuestionUI row : rows) {
        if (!row.isChecked()) {
            allChecked = false;
            break;
        }
    }
    finishBtn.setVisible(allChecked);
    top.revalidate();
    top.repaint();
    }
    
    public void loadPaper() {
    if (sets.isEmpty()) buildSets();
    questArea.removeAll();
    rows.clear();

    java.util.Random rnd = new java.util.Random();
    set = rnd.nextInt(sets.size()) + 1;                      // 1 = A, 2 = B
    headerLbl.setText("SET " + (char) ('A' + set - 1));

    // 30% of papers have nothing pre-marked; the rest have 15-35% of their items pre-marked
    int preMark = rnd.nextInt(100) < 30 ? 0 : 15 + rnd.nextInt(21);

    List<QuestionData> list = sets.get(set - 1);
    for (int i = 0; i < list.size(); i++) {
        QuestionUI row = new QuestionUI(i + 1, list.get(i), preMark);
        row.setScale(scale);
        row.setOnChange(this::updateFinishButton);
        rows.add(row);
        questArea.add(row);
    }

    updateFinishButton();
    questArea.revalidate();
    questArea.repaint();
    top.revalidate();
    SwingUtilities.invokeLater(() -> scroll.getVerticalScrollBar().setValue(0));   // start at the top
}
    
    
    
    public int getSet() { return set; }

    
    private void buildSets() {
    sets.clear();
    int count = (level == 1) ? 1 : 2;               // level 1 has one set, levels 2 and 3 have two
    for (int s = 1; s <= count; s++) {
        sets.add(TestPaperData.generateQuestions(level, s));
    }
}

/** Answer lines for every set, e.g. [["1. A", "2. B", ...], ["1. B", ...]]. */
public List<List<String>> getAllAnswerKeys() {
    List<List<String>> all = new ArrayList<>();
    for (List<QuestionData> qs : sets) {
        List<String> key = new ArrayList<>();
        for (int i = 0; i < qs.size(); i++) {
            key.add((i + 1) + ". " + qs.get(i).answer.replace(".", "").trim());
        }
        all.add(key);
    }
    return all;
}

private void applyScale() {
    double s = Math.min(getWidth() / 650.0, getHeight() / 900.0);
    if (s <= 0 || Math.abs(s - scale) < 0.01) return;
    scale = s;
    headerLbl.setFont(headerLbl.getFont().deriveFont(Font.BOLD, (float) (22 * s)));
    finishBtn.setFont(finishBtn.getFont().deriveFont(Font.BOLD, (float) (16 * s)));
    for (QuestionUI row : rows) row.setScale(s);
    questArea.revalidate();
    questArea.repaint();
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLbl = new javax.swing.JLabel();
        finishBtn = new javax.swing.JToggleButton();
        questHolder = new javax.swing.JPanel();

        headerLbl.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        finishBtn.setText("Finished Checking!");

        javax.swing.GroupLayout questHolderLayout = new javax.swing.GroupLayout(questHolder);
        questHolder.setLayout(questHolderLayout);
        questHolderLayout.setHorizontalGroup(
            questHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        questHolderLayout.setVerticalGroup(
            questHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(finishBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(188, 188, 188)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finishBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(questHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(74, 74, 74))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton finishBtn;
    private javax.swing.JLabel headerLbl;
    private javax.swing.JPanel questHolder;
    // End of variables declaration//GEN-END:variables
}
