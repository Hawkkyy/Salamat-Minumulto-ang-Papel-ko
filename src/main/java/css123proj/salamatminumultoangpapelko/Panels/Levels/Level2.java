
package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.function.Consumer;

public class Level2 extends javax.swing.JPanel {

    public Level2(Consumer<String> goTo) {
        setPreferredSize(new Dimension(1920, 1080));
        setLayout(new BorderLayout());
        add(new LevelUI(2, goTo), BorderLayout.CENTER);  
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
