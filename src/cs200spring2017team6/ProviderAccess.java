package cs200spring2017team6;

import database.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;

/**
 * This is the ProviderAccess Class. It is accessed from the ChocAn.java.
 * It simulates the Provider Terminal, the Provider is asked question
 * and depending on their answer it continues or returns to ChocAn.java
 * 
 * @author Landon Newberry
*
 */
public class ProviderAccess extends AccessLayerClass {
	
	// constructor
	public ProviderAccess() throws FileNotFoundException {}
	
	
    /**
     * Decides what function to go to based on the Providers choice
     * @throws IOException for errors
     */
    public static void providerTerminal() throws IOException {
        frame = null;
        
        Object [] options  = {"Verify Member", "Bill Member", "Request Provider Directory", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Pick an Option", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Verify Member for Visit"
        if (selection == 0)
            verifyMember();
        // "Bill Member for visit"
        else if (selection == 1)
            billVisit(0);
        // "Request Provider Directory"
        else if (selection == 2)
            requestDirectory();
        // "Return to main Menu, to return to ChocAn.java
        else if (selection == 3)
            return;
        //if the exit button is pressed
        else if (selection == -1)
            System.exit(0);
        else 
            System.out.println("providerTerminal()");
    }
    
    /**
     * If the Provider is wanting to verify the member before health care services are provided
     * @throws IOException 
     */
    private static void verifyMember() throws IOException {
        JFrame frame = null;
        
        int memberId = getMemberId();
        
        
        String status;
        if (memberExists(memberId)) {
        	status = "Verified";
        } else {
        	status = "Not verified";
        }

        JOptionPane.showMessageDialog(frame, status, "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        
        providerTerminal();
    }//close startVisit function
    
    /**
     * Function to handle the provider inputing information about the visit
     * @param providerId the Provider ID
     * @throws IOException 
     */
    private static void billVisit(int providerId) throws IOException {
        frame = null;

        services = new ServiceRepository();
        visits = new VisitRepository();
        
        int memberId = 0, serviceId = 0;
        
        // memberId
        // serviceId
        // date
        // providerId
        
        ServiceRepository sR = new ServiceRepository();
        
        //if you didn't come from the startVisit function get providerId
        if (providerId == 0) {
            providerId = getValidProviderId();
        }
        
        //get memberId
        memberId = getValidMemberId();
        
        while (!memberExists(memberId)) {
        	memberId = Integer.parseInt(JOptionPane.showInputDialog(frame,"Not a verified Member ID. Please enter vaild Member number.", "ChocAn", JOptionPane.ERROR_MESSAGE));
        }
        //makes sure the status is validated and nothing else
        while(!memberExists(memberId)) {
            memberId = Integer.parseInt(JOptionPane.showInputDialog(frame,"Not a verified Member ID. Please enter vaild Member number.", "ChocAn", JOptionPane.ERROR_MESSAGE));
        }
        if (memberExists(memberId))
            JOptionPane.showMessageDialog(frame, "Verified", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        
        
        //String currentDateAndTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        String date = JOptionPane.showInputDialog(frame,"Enter date the service was provided. MM-DD-YYYY.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        
        //exits if "cancel" or "exit" is pressed
        if (date == null) 
            System.exit(0);
        
        // confirm date is correct
        while (date.length() != 10 || (!Character.isDigit(date.charAt(0)) || !Character.isDigit(date.charAt(1)))
        		|| (date.charAt(2) != '-') || (!Character.isDigit(date.charAt(3))) || (!Character.isDigit(date.charAt(4)))
        		|| (date.charAt(5) != '-') || (!Character.isDigit(date.charAt(6))) || (!Character.isDigit(date.charAt(7)))
        		|| (!Character.isDigit(date.charAt(8))) || (!Character.isDigit(date.charAt(9)))) {
        	date = JOptionPane.showInputDialog(frame,"Error: Format must be MM-DD-YYYY","ChocAn",JOptionPane.ERROR_MESSAGE);
        }
        
        int selection = JOptionPane.showConfirmDialog(frame, "Do you want the Provider Directory sent to you?", "ChocAn", JOptionPane.YES_NO_OPTION);
        //Send Provider Directory if "Yes" is entered
        if (selection == 0) {
            sR.providerDirectory();
            JOptionPane.showMessageDialog(frame, "It has been sent!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        }
        //if the exit button is pressed quit the program
        else if (selection == -1)
            System.exit(0);
        
        //get service code
        serviceId = getValidServiceId();
        
        
        
        //ask if Provider wants to enter comments
        selection = JOptionPane.showConfirmDialog(frame, "Do you wish to enter comments?", "ChocAn", JOptionPane.YES_NO_OPTION);
        String comment = "";

        if (selection == 0) {
            comment = JOptionPane.showInputDialog(frame,"Enter comment","ChocAn", JOptionPane.QUESTION_MESSAGE);
            if (comment == null)
                System.exit(0);
            while (comment.length() > 25) {
                comment = JOptionPane.showInputDialog(frame,"Comment must be less than 25 characters.", "ChocAn", JOptionPane.ERROR_MESSAGE);

                if (comment == null)
                    System.exit(0);
            }
        } 

        else if (selection == 1)
            comment = "";
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("billVisit()");
        
        
        // find a unique visit Id
        int randomNum = ThreadLocalRandom.current().nextInt(1, 999999 + 1);
        
        System.out.println("New Id: " + randomNum);
        System.out.println("Provider ID: " + providerId);
        System.out.println("Member Id: " + memberId);
        System.out.println("Service Id: " + serviceId);
        System.out.println("date: " + date);
        System.out.println("comment: " + comment);
        
        Visit theVisit = new Visit(randomNum, providerId, memberId, serviceId, date, comment);
        
        // saved the new visit
        visits.save(theVisit);

        
        Object [] options  = {"Yes", "No, but stay in Provider access", "No, return to Main Menu"};
        selection = JOptionPane.showOptionDialog(frame, "Do you want to bill another visit?", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selection == 0)
            billVisit(providerId);
        // "No but stay Provider"
        else if (selection == 1)
            providerTerminal();
        // "No, return to Main Menu"
        else if (selection == 2) 
            return;
        //if the exit button is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("billVisit()");
    }
    
    /**
     * Function to send the Provider Directory if requested
     * @throws IOException 
     */
    private static void requestDirectory() throws IOException {
        frame = null;
        ServiceRepository sR = new ServiceRepository();
        sR.providerDirectory();
        
        JOptionPane.showMessageDialog(frame, "It has been sent!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);

        providerTerminal();
    }
    
}