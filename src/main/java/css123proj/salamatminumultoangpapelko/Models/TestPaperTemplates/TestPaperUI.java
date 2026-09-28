
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TestPaperUI extends JPanel implements SwitchToolListener{

    
    private Image testPaperImg;
    private JScrollPane scroll;
    private JPanel questArea;
    
    public TestPaperUI() {
        
        initComponents();
        
        try {
            testPaperImg = ImageIO.read(getClass().getResource("/Art/Models/TestPaper/TestPaperImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        setOpaque(false);
        scroll.setOpaque(false);
        questHolder.setOpaque(false);
        questArea.setOpaque(false);
        //////
        
        scroll = new JScrollPane();
        questHolder.setLayout(new BorderLayout());
        
        questArea = new JPanel();
        questArea.add(scroll);
        questArea.setLayout(new BoxLayout(questArea, BoxLayout.Y_AXIS));
        
        
        
        questHolder.add(questArea, BorderLayout.CENTER);
        
        
        
        
        
        
        
        
        
    }
//         testPaper.setBounds((int)(550 * sx), (int)(100 * sy), (int)(650 * sx), (int)(900 * sy));

    
    
    
    
    
    
    
    
    
    
    
    
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToggleButton1)
                        .addGap(11, 11, 11)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(questHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
