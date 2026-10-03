
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;

import java.awt.BorderLayout;
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
    loadPaper();
}

    public int getPapersPerLevel() { return PAPERS_PER_LEVEL; }

/** Papers still sitting in the stack (the one on the desk is not counted). */
public int getPapersLeftInStack() {
    int taken = papersDone + (paperActive ? 1 : 0);
    return PAPERS_PER_LEVEL - taken;
}

/** Takes the next paper from the stack. Returns false if one is already on the desk or the stack is empty. */
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
    questArea.removeAll();
    rows.clear();

    set = new java.util.Random().nextInt(level == 1 ? 1 : 2) + 1;   // 1 = A, 2 = B
    headerLbl.setText("SET " + (char) ('A' + set - 1));

    List<QuestionData> list = TestPaperData.generateQuestions(level, set);
    for (int i = 0; i < list.size(); i++) {
        QuestionUI row = new QuestionUI(i + 1, list.get(i));
        row.setOnChange(this::updateFinishButton);
        rows.add(row);
        questArea.add(row);
    }

    updateFinishButton();
    questArea.revalidate();
    questArea.repaint();
    top.revalidate();
    }
    
    public List<String> getAnswerKey() {
        List<String> key = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            key.add((i + 1) + ". " + rows.get(i).getCorrectLetter());
        }
        return key;
    }
    
    public int getSet() { return set; }

    
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
