/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels;


    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.net.URL;
    import java.util.function.Consumer;

public class OpeningCutscene extends javax.swing.JPanel {

        private Consumer<String> goTo;
        private int i = 1;
    
    public OpeningCutscene(Consumer<String> goTo) {
        
        this.goTo = goTo;
        
        
        setLayout(new BorderLayout());

        JButton next = new JButton(">");
        JButton prev = new JButton("<");
        JButton confirm = new JButton("Confirm");
        
        CardLayout showScene = new CardLayout();
        JPanel sceneBox = new JPanel(showScene);
        
        JPanel scene1 = new JPanel();
        JPanel scene2 = new JPanel();
        
        scene2.add(confirm);
        
        JPanel controlBox = new JPanel(new FlowLayout());
        
        controlBox.add(prev);
        controlBox.add(next);
        
        sceneBox.add(scene1, "SCENE_1");
        sceneBox.add(scene2, "SCENE_2");
        
        URL url1 = getClass().getResource("/Art/Cutscenes/Opening/Cutscene1.jpg");

        URL url2 = getClass().getResource("/Art/Cutscenes/Opening/Cutscene2.png");
        
        if (url1 != null && url2 != null) {
            Image openComic1 = new ImageIcon(url1).getImage().getScaledInstance(1200, 1000, Image.SCALE_SMOOTH);
            Image openComic2 = new ImageIcon(url2).getImage().getScaledInstance(1200, 1000, Image.SCALE_SMOOTH);

            JLabel img1 = new JLabel(new ImageIcon(openComic1));
            JLabel img2 = new JLabel(new ImageIcon(openComic2));

            scene1.add(img1);
            scene2.add(img2);   
        } else {
        System.err.println("Could not load image resources! Check paths.");
        }
        
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                goTo.accept("Title Screen");
            }
        });
        
        
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
                
                if(i<2){
                    
                    i++;
                    showScene.show(sceneBox, "SCENE_"+i);
                    
                }   }
        });
        
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("bye");
                
                if(i>1){
                    
                    i--;
                    showScene.show(sceneBox, "SCENE_"+i);
                    
                }   }
        
    });
        
        
        add(sceneBox, BorderLayout.CENTER);
        add(controlBox, BorderLayout.SOUTH);
        setVisible(true);
        
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
