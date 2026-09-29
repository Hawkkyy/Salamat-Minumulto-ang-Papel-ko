
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TestPaperUI extends JPanel implements SwitchToolListener{

    
    private Image testPaperImg;
    private JScrollPane scroll;
    private JPanel questArea;
    int level = 1,set = 1;
    
    public TestPaperUI() {
        
        initComponents();
        
        try {
            testPaperImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaper/TestPaperImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
           setOpaque(false);
    questHolder.setOpaque(false);
    questHolder.setPreferredSize(new java.awt.Dimension(600, 700));
    
    questArea = new JPanel();
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

    List<QuestionData> list = TestPaperData.generateQuestions(level, set);
           for (int i = 0; i < list.size(); i++) {
            questArea.add(new QuestionUI(i + 1, list.get(i)));
           }
           
    addComponentListener(new java.awt.event.ComponentAdapter() {
    @Override
    public void componentResized(java.awt.event.ComponentEvent e) {
        questHolder.setPreferredSize(new java.awt.Dimension(
                (int) (getWidth() * 0.87),
                (int) (getHeight() * 0.72)));
        revalidate();
    }
});  
            
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLbl = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        questHolder = new javax.swing.JPanel();

        headerLbl.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        jToggleButton1.setText("Finished Checking!");

        javax.swing.GroupLayout questHolderLayout = new javax.swing.GroupLayout(questHolder);
        questHolder.setLayout(questHolderLayout);
        questHolderLayout.setHorizontalGroup(
            questHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
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
                .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jToggleButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(questHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLbl;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel questHolder;
    // End of variables declaration//GEN-END:variables
}
