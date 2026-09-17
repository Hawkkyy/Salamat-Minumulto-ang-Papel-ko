/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package css123proj.salamatminumultoangpapelko;


    import css123proj.salamatminumultoangpapelko.Panels.*;
    import css123proj.salamatminumultoangpapelko.Panels.Levels.*;
    import java.awt.*;
    import javax.swing.*;
    

public class GameFrame extends javax.swing.JFrame {
    
        CardLayout showPanel = new CardLayout();
        JPanel screenBox = new JPanel(showPanel);
                
        OpeningCutscene openCutPanel = new OpeningCutscene(this::showCard);
        EndingCutscene endCutPanel = new EndingCutscene(this::showCard);
        
        TitleScreen titlePanel = new TitleScreen(this::showCard);
        Settings setPanel = new Settings(this::showCard);
        Credits credPanel = new Credits(this::showCard);
        
        Calendar calPanel = new Calendar(this::showCard);
        Level1 lev1Panel = new Level1(this::showCard);
        Level2 lev2Panel = new Level2(this::showCard);
        Level3 lev3Panel = new Level3(this::showCard);
        
        Score scorePanel = new Score(this::showCard);
        GameWon gWonPanel = new GameWon(this::showCard);
        GameOver gOverPanel = new GameOver(this::showCard);
        
        LoadingPage loadPanel = new LoadingPage(this::showCard);

    public GameFrame() {
        
        screenBox.add(openCutPanel, "Opening Cutscene");
        screenBox.add(endCutPanel, "Ending Cutscene");
        screenBox.add(titlePanel, "Title Screen");
        screenBox.add(setPanel, "Settings");
        screenBox.add(credPanel, "Credits");
        screenBox.add(calPanel, "Calendar");
        screenBox.add(lev1Panel, "Level 1");
        screenBox.add(lev2Panel, "Level 2");
        screenBox.add(lev3Panel, "Level 3");
        screenBox.add(scorePanel, "Score");
        screenBox.add(gWonPanel, "Game Won");
        screenBox.add(gOverPanel, "Game Over");
        screenBox.add(loadPanel, "Loading");
        
        setContentPane(screenBox);
        
        showPanel.show(screenBox, "Opening Cutscene");
        
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }

   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showCard(String cardName) {
        
        System.out.println("Going to "+cardName);
        showPanel.show(screenBox, cardName);
        screenBox.revalidate();
        screenBox.repaint();
        
    }
    
    
    public static void main(String args[]) {
        
        new GameFrame();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}