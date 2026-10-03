/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;



public class Instructions {
    public static void showInstructions(){
        String[] instructions = {
            "Instruction 1: Title<br><br>" +
            "The player is a ghost that wants to check tests for the professor! " +
            "She is currently sleeping and needs rest. To lighten her burden, check her test papers.",
            
            "Instruction 2: Gameplay<br><br>" +
            "The player will have 5 minutes to check the test papers. " +
            "Along the way, you will encounter several tools as you check the test items. " +
            "You may hold only one tool at a time. " +
            "Make sure to put the items back in their place after use. <br><br>" +
            "Tools/Items: Clock | Answer Key | Test Paper | Test Paper Stack | Green Ballpen | Correction Tape <br>" +
            "Test types: True or False | Multiple Choice | Combination<br><br>" +
            "Once the timer runs out, the game ends automatically even if the player is not done checking the papers.",
            
            "Instruction 3: Clock<br><br>" +
            "Check the Clock from time to time. You start at 11:00 PM and always end at 4:00 AM.<br><br>" +
            "Be mindful of the time and check as many tests as you can!",
            
            "Instruction 4: Answer Key<br><br>"+
            "When starting a level, you will first be shown the Answer Key. "+
            "Click “Confirm” when you’re done reading to check tests! The timer will start counting after that.<br><br>"+
            "If you cannot remember an answer, click on the Answer Key on the left.<br><br>"+
            "Checking tests is all about memorization and understanding. "+
            "Try to familiarize yourself with the answers to all the questions before checking the tests. Every second counts!",
            
            "Instruction 5: Test Paper<br><br>" +
            "Read the questions and answers carefully. " +
            "Some test papers will already be graded, some will have incorrect markings, and some might not even be checked at all. <br><br>"+
            "In addition, students may have messy handwriting, and it is your job to identify what answer they picked.", 
            
            "Instruction 6: Test Paper Stack<br><br>" +
            "Contains how many test papers are left.", 
            
            "Instruction 7: Green Ballpen<br><br>" +
            "Use this to check the Test Items on Test Papers. <br><br>" +
            "Use Left Click to check [/], and Right Click to mark wrong [X] items. <br><br>" +
            "Remember to put down the Green Ballpen at its rightful place beside the Correction Tape on the right side.", 
            
            "Instruction 8: Correction Tape<br><br>" +
            "Use this to correct your mistakes on Test Items on Test Papers.<br><br>" +
            "Use Left Click to erase traces of errors in your judgment, and use the Green Ballpen to check on top of the corrections.<br><br>" +
            "Remember to put down the Correction Tape at its rightful place beside the Green Ballpen on the right side.", 
            
            "Instruction 9: Multiple Choice Test Papers<br><br>" +
            "For Tests like these, remember the Letters for each item. Be careful of answers with messy handwriting!", 
            
            "Instruction 10: True or False Test Papers<br><br>" +
            "For Tests like these, remember which is True and False for each item. Be careful of answers with messy handwriting!", 
            
            "Instruction 11: Combination Test Papers<br><br>" +
            "For Tests like these, remember which is True and False and the Letters for each item. Be careful of answers with messy handwriting!", 
            
            "Instruction 12: Have Fun Checking!<br><br>" +
            "And that is all! Feel free to discover strategies of your own and have fun checking them out!" 
        };
        
        Object[] instDialog = {"Previous", "Next", "Close"}; 
        int currentIndex = 0;
        
        JLabel messageLabel = new JLabel();
        
        messageLabel.setPreferredSize(new Dimension(400, 210));
        
        messageLabel.setVerticalAlignment(SwingConstants.TOP);
        messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        while (currentIndex >= 0 && currentIndex < instructions.length) {
            messageLabel.setText("<html><body>" + instructions[currentIndex] + "</body></html>");
            
            int result = JOptionPane.showOptionDialog(
                null,
                messageLabel, 
                "Instructions (" + (currentIndex + 1) + "/" + instructions.length + ")",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, 
                instDialog, 
                instDialog[1] 
            );
            
            if (result == 0) { 
                if (currentIndex > 0) {
                    currentIndex = currentIndex - 1; 
                } else {
                    currentIndex = instructions.length - 1; 
                }
            } else if (result == 1) { 
                if (currentIndex < instructions.length - 1) {
                    currentIndex = currentIndex + 1; 
                } else {
                    currentIndex = 0; 
                }
            } else { 
                break; 
            }
        }
    }
}
