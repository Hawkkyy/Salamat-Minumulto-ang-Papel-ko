package css123proj.salamatminumultoangpapelko.Panels.Levels;

import css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates.LevelUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.function.Consumer;

public class Level1 extends javax.swing.JPanel {

    private final Consumer<String> goTo;

    public Level1(Consumer<String> goTo) {
        this.goTo = goTo;

        setPreferredSize(new Dimension(1920, 1080));
        setLayout(new BorderLayout());
        add(new LevelUI(1), BorderLayout.CENTER);
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
