/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents;

import java.util.EventObject;

/**
 *
 * @author hawk
 */
public class SwitchTool extends EventObject {
    
    private String toolSelected;
    
    public SwitchTool(Object source, String toolSelected) {
        
        super(source);
        this.toolSelected = toolSelected;
        
    }
    
    
    public String getSelectedTool(){
        
        return toolSelected;
        
    }
    
    
}
