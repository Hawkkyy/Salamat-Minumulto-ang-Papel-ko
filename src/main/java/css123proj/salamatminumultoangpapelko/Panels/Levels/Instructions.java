/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels;

import javax.swing.JOptionPane;





public class Instructions {
    public static void showInstructions(){
        
        Object[] instDialog = {"Previous", "Next"};
        
        int result = JOptionPane.showOptionDialog(
                null,
                "Instructions",
                "Instruction 1", // message to be displayed
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // icon
                instDialog,
                instDialog[1]);
        
        switch (result){
            case 0: 
                
                
        }
    }
}
