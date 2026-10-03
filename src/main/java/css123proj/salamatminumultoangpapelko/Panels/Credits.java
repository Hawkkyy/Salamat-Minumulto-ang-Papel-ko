
package css123proj.salamatminumultoangpapelko.Panels;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public class Credits extends javax.swing.JPanel {

    private Consumer<String> goTo;
    
    public Credits(Consumer<String> goTo) {
        this.goTo = goTo;
        
        initComponents();
        
        setBackground(new Color(25, 25, 50));
        setLayout(new GridBagLayout());
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        JLabel titleLbl = new JLabel("Credits");
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 60));
        titleLbl.setForeground(Color.WHITE);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel teamHeader = new JLabel("PROJECT TEAM");
        teamHeader.setFont(new Font("SansSerif", Font.BOLD, 35));
        teamHeader.setForeground(new Color(160, 195, 255));
        teamHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel member1 = new JLabel("Bea Athena Salazar");
        JLabel role1 = new JLabel("Project Manager & Lead Artist");
        JLabel member2 = new JLabel("Vincent Roui Teves");
        JLabel role2 = new JLabel("Game Mechanic & Lead Developer");
        JLabel member3 = new JLabel("Mariele C. Veterbo");
        JLabel role3 = new JLabel("Concept Artist & Developer");
        
        Font nameFont = new Font("SansSerif", Font.BOLD, 22);
        Font roleFont = new Font("SansSerif", Font.PLAIN, 18);
        
        member1.setFont(nameFont); member1.setForeground(Color.WHITE); member1.setAlignmentX(Component.CENTER_ALIGNMENT);
        role1.setFont(roleFont); role1.setForeground(new Color(190, 195, 210)); role1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        member2.setFont(nameFont); member2.setForeground(Color.WHITE); member2.setAlignmentX(Component.CENTER_ALIGNMENT);
        role2.setFont(roleFont); role2.setForeground(new Color(190, 195, 210)); role2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        member3.setFont(nameFont); member3.setForeground(Color.WHITE); member3.setAlignmentX(Component.CENTER_ALIGNMENT);
        role3.setFont(roleFont); role3.setForeground(new Color(190, 195, 210)); role3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel ackHeader = new JLabel("SPECIAL THANKS");
        ackHeader.setFont(new Font("SansSerif", Font.BOLD, 35));
        ackHeader.setForeground(new Color(160, 195, 255));
        ackHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel ackText1 = new JLabel("Dedicated to all hard-working professors and educators.");
        JLabel ackText2 = new JLabel("Thank you to our families and friends for their continuous support!");
        
        ackText1.setFont(new Font("SansSerif", Font.ITALIC, 15));
        ackText1.setForeground(new Color(210, 215, 230));
        ackText1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ackText2.setFont(new Font("SansSerif", Font.ITALIC, 15));
        ackText2.setForeground(new Color(210, 215, 230));
        ackText2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backBtn = new JButton("Return to Title");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        backBtn.setPreferredSize(new Dimension(220, 45));
        backBtn.setMaximumSize(new Dimension(220, 45));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> goTo.accept("Title Screen"));
        
        centerPanel.add(titleLbl);
        centerPanel.add(Box.createVerticalStrut(35));
        
        centerPanel.add(teamHeader);
        centerPanel.add(Box.createVerticalStrut(12));
        centerPanel.add(member1);
        centerPanel.add(role1);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(member2);
        centerPanel.add(role2);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(member3);
        centerPanel.add(role3);
        
        centerPanel.add(Box.createVerticalStrut(35));
        centerPanel.add(ackHeader);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(ackText1);
        centerPanel.add(Box.createVerticalStrut(4));
        centerPanel.add(ackText2);
        
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(backBtn);
        
        add(centerPanel);
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
