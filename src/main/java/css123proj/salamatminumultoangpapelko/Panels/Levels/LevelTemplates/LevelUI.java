/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates;

import css123proj.salamatminumultoangpapelko.Models.*;
import css123proj.salamatminumultoangpapelko.Models.Menu.MenuButton;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.*;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author hawk
 */
public class LevelUI extends JPanel implements SwitchToolListener{

    private Image levelImg;
    Icon inst1;
    TestPaperUI testPaper;
    AnswerSheet ansKey;
    Clock clock;
    CorrectionTape corTape;
    GreenBallpen ballpen;
    TestPaperStack papStack;
    MenuButton menu;
    
    JPopupMenu popMenu;
    JMenuItem resume,inst,cal,title,restart,quit;

    JSeparator sep1,sep2,sep3,sep4,sep5;
    
    public LevelUI() {
        
        setLayout(null);
        
        try {
            levelImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/LevelImg.jpg"));
            inst1 = new ImageIcon(levelImg);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        
        
        testPaper = new TestPaperUI();
        ansKey = new AnswerSheet();
        clock = new Clock();
        corTape = new CorrectionTape();
        ballpen = new GreenBallpen();
        papStack = new TestPaperStack();
        menu = new MenuButton();
        
        popMenu = new JPopupMenu("In-Game Menu");
        
        sep1 = new JSeparator();
        sep2 = new JSeparator();
        sep3 = new JSeparator();
        sep4 = new JSeparator();
        sep5 = new JSeparator();
        
        resume = new JMenuItem("Resume");
        inst = new JMenuItem("Instructions");
        cal = new JMenuItem("Calendar");
        title = new JMenuItem("Return to Title Screen");
        restart = new JMenuItem("Restart");
        quit = new JMenuItem("Quit");
        
        popMenu.setPopupSize(800, 800);
        
        ////
        popMenu.add(resume);
        popMenu.add(sep1);
        popMenu.add(inst);
        popMenu.add(sep2);
        popMenu.add(cal);
        popMenu.add(sep3);
        popMenu.add(title);
        popMenu.add(sep4);
        popMenu.add(restart);
        popMenu.add(sep5);
        popMenu.add(quit);
        
        inst.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                /*
        JOptionPane.showMessageDialog(null,
                        "dies", 
                        "Instructions",
                        JOptionPane.PLAIN_MESSAGE,
                        inst1);
                */
            }
            
        });
        
        
        
        menu.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                popMenu.show(menu, -1100, menu.getHeight());
                
            }
            
        });
        
        
        JButton aButton;
        
        
        
        add(aButton = new JButton("Plain Message Box"));
        aButton.addActionListener (new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,
                    "yes", // message to be displayed
                    "Read This", // title
                    JOptionPane.PLAIN_MESSAGE); // type of message
                }});
        
        /*
        int result = JOptionPane.showConfirmDialog(null,
“Do you want me to erase your hard drive ?", "Answer this
Question", JOptionPane YES_NO_OPTION);
if (result == 0)
System.out.printin("OK, I'm erasing it now
else
System.out.printin("Fine then, you clean it up!")
        
        
        add(aButton = new JButton ("Confirmation Dialog Box with Cancel"));
aButton.addActionListener (new ActionListener() {
public void actionPerformed(ActionEvent e) {
int result = JoptionPane.showConfirmDialog(null,
"Do you want to overwrite the file 2",
"answer this Question",
JoptionPane.YES_NO_CANCEL OPTION);
switch (result) {
case 0: System.out.printin("OK, but don't come crying to me once its
gone"); break:
System.out.printin("Well you should pick a new name then");
break;
System.out.println("0K, I'll ask you again later"); break:
}

        add(aBucton = new JButton("Multiple option Dialog Box")):
aButton.addactionListener(new ActionListener () {
public void actionPerformed(ActionEvent e) {
Object{] options = ("outstanding", “Excellent, "Good",
#Be08#}s
int result = JoptionPane.showoptionDialog (null,
"How would you rate your vehicle's
performance 2",
"Pick an Option",
JoptionPane. DEFAULT_OPTION,
JoptionPane .QUESTION_MESSAGE,
null,
options,
‘options (0])
system.out.print ("You have rated your vehicle's performance
as " + options[result])?
Af (result < 3)
System.out.printin(*wWe are glad you are pleased."
Systen.out.printin ("Please explain why.
ye
        
        add(aButton = new JButton("Input Dialog Box"));
aButton.addActionListener (new ActionListener() {
public void actionPerformed(ActionBvent e) {
String inputvalue = JoptionPane.showInputDialog("Please input
your name!
System.out.printIn("Your name is " + inputValue);
We
        
        JFilechooser chooser = new JFileChooser();
int returnVal = chooser. showOpenDialog (this) ;
Af (returnval == JFileChooser.APPROVE_OPTION) {
System.out.println("You chose to open this file: " +
chooser.getSelectedFile() .getName())i
        
        Color newColor = JColorChooser.showDialog(
this, // The parent window
"Choose a Color", // Title on Dialog Box
Color.RED); // \nitial color selected
        
        
        
        
*/
        
        
        
        //
        
        ballpen.addSwitchToolListener(this);
        corTape.addSwitchToolListener(this);
        
        // x y w h
        
        testPaper.setBounds(550, 100, 650, 900);
        ansKey.setBounds(0, 100, 550, 1000);
        clock.setBounds(1530, 30, 350, 160);
        
        ballpen.setBounds(1300, 700, 100, 200);
        corTape.setBounds(1400, 700, 100, 200);
        papStack.setBounds(1600, 500, 600, 900);
        
        menu.setBounds(1625, 30, 200, 50);
        
        
        add(testPaper);
        add(ansKey);
        add(corTape);
        add(ballpen);
        add(papStack);
        add(menu);
        add(clock);
    }
    
    
    @Override
    public void onToolSelected(SwitchTool evt){
        System.out.println("Switched to "+evt.getSelectedTool());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (levelImg != null) {
            g.drawImage(levelImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
