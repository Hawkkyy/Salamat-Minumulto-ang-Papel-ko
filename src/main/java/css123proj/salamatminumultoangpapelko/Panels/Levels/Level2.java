
package css123proj.salamatminumultoangpapelko.Panels.Levels;

import java.util.function.Consumer;

public class Level2 extends javax.swing.JPanel {

    
    private Consumer<String> goTo;
    
    
    public Level2(Consumer<String> goTo) {
        
        this.goTo = goTo;
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelUI1 = new css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 1498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(levelUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI levelUI1;
    // End of variables declaration//GEN-END:variables
}
