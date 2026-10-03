
package css123proj.salamatminumultoangpapelko.Panels;

import java.util.function.Consumer;

public class EndingCutscene extends javax.swing.JPanel {

    private Consumer<String> goTo;
    
    public EndingCutscene(Consumer<String> goTo) {
        
        this.goTo = goTo;
        initComponents();
        setLayout(new java.awt.FlowLayout());
        
        javax.swing.JButton back = new javax.swing.JButton("Back to Title Screen");
        back.addActionListener(e -> goTo.accept("Title Screen"));
        add(back);
        
    }

    @SuppressWarnings("unchecked")
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
