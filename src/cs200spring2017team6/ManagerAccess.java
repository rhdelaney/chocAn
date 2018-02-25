package cs200spring2017team6;

import java.io.*;
import javax.swing.JOptionPane;

/**
* This ManagerAccess class is called from ChocAn.java when someone wants
* to be a manager. The manager can request reports. Depending on their
* answers they can get all of a certain type of a report or just one 
* member/provider using their ID number as signifier. 
*
* @author Raychel Delaney
* 
*/
public class ManagerAccess extends AccessLayerClass {
    
	public ManagerAccess() throws FileNotFoundException {}
	
    /**
     * Function to come from ChocAn.java
     * @throws IOException for errors
     */
    public static void managerTerminal() throws IOException {
    	getReport();
    }
    
    /**
     * Function to figure out what type of report the Manager wants
     * @throws IOException 
     */
    private static void getReport() throws IOException {
    	frame = null;
        String reportType;
        
        Object [] options  = {"Send Member reports", "Send Provider reports", "Send Summary report", "Send EFT report", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        //send member reports
        if (selection == 0) {
            reportType = "Member";
        	MemberReport m = new MemberReport();
        	
            //all reports?
            selection = JOptionPane.showConfirmDialog(frame, "Do you want to run all Member reports?", "ChocAn", JOptionPane.YES_NO_OPTION);

       		if (selection == 0) {
        		m.memberReport();
            	JOptionPane.showMessageDialog(frame, "Sending all Member reports.", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
            	
            	getReport();
       		}
       		
       		else if (selection == 1)
       		    getSingleReport(reportType);
       		else
       			System.exit(0);
        }
        
        //send provider reports
        else if (selection == 1) {
            reportType = "Provider";
            ProviderReport p = new ProviderReport();
            
            //all reports?
            selection = JOptionPane.showConfirmDialog(frame, "Do you want to run all Provider reports?", "ChocAn", JOptionPane.YES_NO_OPTION);
            
       		//get all reports
       		if (selection == 0) {
        		p.providerReport();
       		 JOptionPane.showMessageDialog(frame, "Sending all Provider reports.", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
       		 
       		 getReport();
       	}
       		
       		else if (selection == 1)
       			getSingleReport(reportType);
       		else
       			System.exit(0);
       	}
        
        //summary report
        else if (selection == 2) {
            SummaryReport s = new SummaryReport();
            s.summaryReport();
            JOptionPane.showMessageDialog(frame, "Sending Summary Report.", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
            getReport();
        }

        //EFT report
        else if (selection == 3) {
            EFTReport eft = new EFTReport();
            eft.eftReport();
            JOptionPane.showMessageDialog(frame, "Getting EFT Report.", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
            getReport();
        }
        else if (selection == 4)
            return;
        else
            System.exit(0);
    }
    
    /**
     * Function used to get a single member/provider report
     * @param report the type of report, either 'Member' or 'Provider'
     * @throws IOException 
     */
    private static void getSingleReport(String reportType) throws IOException {
    	frame = null;
    	int selection, reportId = 0;
        String idString;
        
        
        // the following is if they selected member reports
        if ("Member".equals(reportType)) {
            MemberReport mR = new MemberReport();
            
            idString = JOptionPane.showInputDialog(frame, "Please enter the " + reportType + " ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
            if (idString == null) 
                System.exit(0);
            try { reportId = Integer.parseInt(idString);
            } catch (Exception e){}
            while((reportId <= 99999999) || (reportId >= 1000000000)) { 
                idString = JOptionPane.showInputDialog(frame,"Must be 9 digit number. Please enter vaild " + reportType + " ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
                if (idString == null) 
                    System.exit(0);
                try { reportId = Integer.parseInt(idString);
                } catch (Exception e) {}
            }
            
            //get single report
            mR.memberReport(reportId);
            JOptionPane.showMessageDialog(frame, "Getting " + reportType + " report for " + reportType + " ID: " + reportId + ".", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
            
            Object [] options = {"Yes", "No, but stay in Manager access", "No, return to Main Menu"};
            selection = JOptionPane.showOptionDialog(frame, "Do you want more " + reportType + " reports?", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            // "Yes"
            if (selection == 0) {
                getSingleReport(reportType);
            }
            // "No, but stay in Manager access"
            else if (selection == 1)
                managerTerminal();
            // "No, return to Main Menu"
            else if (selection == 2)
                return;
            else
                System.exit(0);
        }
        
        // the following is if the selected provider reports
        else if ("Provider".equals(reportType)){
            ProviderReport pR = new ProviderReport();
            
            // get id
            idString = JOptionPane.showInputDialog(frame, "Please enter the " + reportType + " number.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
            if (idString == null) 
                System.exit(0);
            try { reportId = Integer.parseInt(idString);
            } catch (Exception e){}
            
            // confirm format
            while((reportId <= 99999999) || (reportId >= 1000000000)) { 
                idString = JOptionPane.showInputDialog(frame,"Must be 9 digit number. Please enter vaild " + reportType + " ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
                if (idString == null) 
                    System.exit(0);
                try { reportId = Integer.parseInt(idString);
                } catch (Exception e) {}
            }
            
            // send single report
            pR.providerReport(reportId);
            JOptionPane.showMessageDialog(frame, "Getting " + reportType + " report for " + reportType + " ID: " + reportId + ".", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
            
            
            Object [] options = {"Yes", "No, but stay in Manager access", "No, return to Main Menu"};
            selection = JOptionPane.showOptionDialog(frame, "Do you want more " + reportType + " reports?", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            // "Yes"
            if (selection == 0) {
                getSingleReport(reportType);
            }
            // "No, but stay in Manager access"
            else if (selection == 1)
                managerTerminal();
            // "No, return to Main Menu"
            else if (selection == 2)
                return;
            else
            	System.exit(0);
        }
        else 
            System.exit(0);
    }
}