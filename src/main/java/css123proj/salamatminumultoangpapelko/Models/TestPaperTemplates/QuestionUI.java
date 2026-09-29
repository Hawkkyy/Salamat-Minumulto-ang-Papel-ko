
package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.TestPaperData.QuestionData;


public class QuestionUI extends javax.swing.JPanel {

    public QuestionUI() {
        initComponents();
    }
    
    
    
public QuestionUI(int num, QuestionData q) {
    initComponents();
    questLbl.setText("<html><body style='width:420px'>" + num + ". " + q.question + "</body></html>");
             

    questLbl.setText("<html><body style='width:420px'>" + q.question + "</body></html>");

    answerLbl.setLayout(new javax.swing.BoxLayout(answerLbl, javax.swing.BoxLayout.Y_AXIS));
    answerLbl.add(new javax.swing.JLabel("A. " + q.a));
    answerLbl.add(new javax.swing.JLabel("B. " + q.b));
    if (q.c != null) answerLbl.add(new javax.swing.JLabel("C. " + q.c));
    if (q.d != null) answerLbl.add(new javax.swing.JLabel("D. " + q.d));

    setOpaque(false);
    answerLbl.setOpaque(false);
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
