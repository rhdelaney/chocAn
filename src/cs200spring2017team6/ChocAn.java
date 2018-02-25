package cs200spring2017team6;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane; 

/**
 * This is the beginning of the program. It ask the user what role they want to be in
 * depending on their answer it will go to ProviderAccess.java, ManagerAccess.java, or 
 * OperatorAccess.java. 
 * 
 * @author Raychel Delaney
 *
 */
public class ChocAn {
    /**
     * The start of the program
     * @param args argument from the command line
     * @throws IOException for errors
     */
    public static void main(String[] args) throws IOException {
        selectRole();
    }


    /**
     * Decides permissions based off of the user selection
     * @throws IOException for errors
     */
    public static void selectRole() throws IOException {
        JFrame frame = null;
        
        Object [] options = {"Provider", "Manager", "Operator"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose a Role", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // Provider
        if (selection == 0){
            ProviderAccess.providerTerminal();
            selectRole();
        }
        // Manager
        else if (selection == 1) {
            ManagerAccess.managerTerminal();
            selectRole();
        }
        // Operator
        else if (selection == 2) {
            OperatorAccess.operatorTerminal();
            selectRole();
        }
        //if the exit button is pressed the function stops
        else if (selection == -1)
            System.exit(0);
        else 
            System.out.println("selectRole()");
    }
    
}