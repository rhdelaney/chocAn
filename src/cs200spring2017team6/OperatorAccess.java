package cs200spring2017team6;
import database.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This OperatorAccess class is called from ChocAn.java when someone wants
 * to be an Operator. As an Operator you can manage Members, Providers,
 * and the Provider Directory. when you manage them you have the option
 * to add, delete, and update them.
 * 
 *@author Landon Newberry
*
 */
public class OperatorAccess extends AccessLayerClass {
    
	// constructor
	public OperatorAccess() throws FileNotFoundException {}
	
	
    /**
     * Function to come from ChocAn.java
     * @throws IOException for errors
     */
    public static void operatorTerminal() throws IOException {
        frame = null;
        
        Object [] options  = {"Manage Member", "Manage Provider", "Manage Provider Directory", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Pick an Option", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (selection == 0)
        	manageMember();
        // "Manage Provider"
        else if (selection == 1)
        	manageProvider("Provider");
        // "Manage Provider Directory"
        else if (selection == 2)
            manageDirectory();
        // "Return to Main Menu"
        else if (selection == 3)
            return;
        //if exit is pressed
        else if (selection == -1)
        	System.exit(0);
        else 
            System.out.println("operatorTerminal()");
    }

    /**
     * Function to see if add, remove, or update Member info
     * @param item will always be 'Member'
     * @throws IOException 
     */
    private static void manageMember() throws IOException {
        frame = null;
        
        Object [] options  = {"Add Member", "Remove Member", "Update Member's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Pick an Option", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addMember();
        // "Remove"
        else if (selection == 1)
            deleteMember();
        // "Update"
        else if (selection == 2) {
            int memberId = getMemberId();
            updateMember(memberId);
        }
        // "Return to Operator Menu"
        else if (selection == 3) 
            operatorTerminal();
        // "Return to Main Menu"
        else if (selection == 4)
            return;
        //if exit is pressed
        else if (selection == -1)
        	System.exit(0);
        else 
            System.out.println("manageMember()");
    }
    
    /**
     * Function to add a Member
     * @param item will always be 'Member'
     * @throws IOException 
     */
    private static void addMember() throws IOException {
        frame = null;
        
        //MemberRepository mData = new MemberRepository();
        int memberId = 0, zip = 0;
        
        //get name
        String name = JOptionPane.showInputDialog(frame, "Enter a name for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (name == null)
            System.exit(0);
        
        //get id
        String idString = JOptionPane.showInputDialog(frame, "Enter an ID for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (idString == null) 
            System.exit(0);
        
        try { memberId = Integer.parseInt(idString);
        } catch (Exception e){}
        
        // confirm format of the new ID
        while(((memberId <= 99999999) || (memberId >= 1000000000)) || memberExists(memberId)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be 9 digits and unique. Please enter vaild Member ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);

            if (idString == null) 
                System.exit(0);
            try { memberId = Integer.parseInt(idString);
            } catch (Exception e) {}
        }
        
        //get address
        String address = JOptionPane.showInputDialog(frame, "Enter an address for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (address == null)
            System.exit(0);
        
        //get city
        String city = JOptionPane.showInputDialog(frame, "Enter a city for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (city == null)
            System.exit(0);
        
        //get state
        String state = JOptionPane.showInputDialog(frame, "Enter a state for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (state == null)
            System.exit(0);
        
        // confirm the format of state string
        while (state.length() != 2 || (state.charAt(0) < 65 || state.charAt(0) > 90) || (state.charAt(1) < 65 || state.charAt(1) > 90)){
            state = JOptionPane.showInputDialog(frame,"Error: Must enter a valid State acronym.","ChocAn",JOptionPane.ERROR_MESSAGE);
            if (state == null)
                System.exit(0);
        }
        
        //get zip
        idString = JOptionPane.showInputDialog(frame, "Enter a ZIP code for the new Member: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (idString == null) 
            System.exit(0);

        try { zip = Integer.parseInt(idString);
        } catch (Exception e){}
        
        // confirm the format of the zip
        while((zip <= 9999) || (zip >= 100000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be 5 digit number. Please enter vaild zip code.", "ChocAn", JOptionPane.ERROR_MESSAGE);
            if (idString == null) 
                System.exit(0);

            try { zip = Integer.parseInt(idString);
            } catch (Exception e) {}
        }  
        
        //add entry
        Member m = new Member(memberId, name, address, city, state, zip);
        members.save(m);
        
        JOptionPane.showMessageDialog(frame, "Member added!\nName: " + name + "\nNumber: " + memberId + "\nAddress: " + address + "\nCity: " + city + "\nState: " + state + "\nZip Code: " + zip, "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        

        Object [] options = {"Add another Member", "Remove a Member", "Update a Member's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Pick an Option", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add another Member"
        if (selection == 0)
            addMember();
        // "Remove Member"
        else if (selection == 1)
            deleteMember();
        // "Update Member"
        else if (selection == 2)
            updateMember(0);
        // "Return to Operator Menu"
        else if (selection == 3)
            operatorTerminal();
        // "Return to Main Menu"
        else if (selection == 4) 
            return;
        // if exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("addMember()");
    }
    
    
    /**
     * Function to delete Members
     * @param item will always be 'Member"
     * @throws IOException 
     */
    private static void deleteMember() throws IOException {
        frame = null;
        
        int memberId = getMemberId();
        
        //delete member
        members.deleteById(memberId);
        
        
        JOptionPane.showMessageDialog(frame, "Member " + memberId + " deleted!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);

        Object [] options = {"Add a Member", "Remove another Member", "Update a Member's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addMember();
        // "Remove"
        else if (selection == 1)
            deleteMember();
        // "Update"
        else if (selection == 2)
            updateMember(0);
        // "Back"
        else if (selection == 3)
            operatorTerminal();
        // "Remove"
        else if (selection == 4) 
            return;
        //if exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("deleteMember()");
    }
    
    /**
     * Function to update Members information
     * @param item will always be 'Member'
     * @param id the Members ID that you are updating
     * @throws IOException 
     */
    private static void updateMember(int id) throws IOException {
        frame = null;
        members = new MemberRepository();
        
        String idString, newName = "", newAddress = "", newCity = "", newState = "";
        int newZip = 0;
        
        //if you don't come from manageMember function the id will be 0
        if (id == 0) {
            id = getMemberId();
        }
        
        //set newId equal to id so that it will be the same unless changed
        int newId = id;
        Member m = members.findById(newId);
        
        //look up info using id
        String name = m.getName();
        String address = m.getAddress();
        String city = m.getCity();
        String state = m.getState();
        int zip = m.getZip();
        
        Object [] options1 = {"Name: " + name, "ID: " + id, "Address: " + address, "City: " + city, "State: " + state, "Zip: " + zip, "Return to Operator Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
        while (selection != 6) {
        	switch (selection) {
			    
        		// "Name"
        	    case 0:
			        //get new name
			        newName = JOptionPane.showInputDialog(frame, "Enter a new name: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
			        if (newName == null)
			            System.exit(0);
			        m.setName(newName);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member name has been updated from " + name + " to " + newName + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;
			    
			    // "ID"
        	    case 1:
			        //get new ID
			        newId = getMemberId();
			        m.setId(newId);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member ID updated from " + id + " to " + newId + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;
			    
			    // "Address"
        	    case 2:
			        //get new address
			        newAddress = JOptionPane.showInputDialog(frame, "Enter the new address: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
			        if (newAddress == null)
			            System.exit(0);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member address updated from " + address + " to " + newAddress + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;

			    // "City"
        	    case 3:
			        //get new city
			        newCity = JOptionPane.showInputDialog(frame, "Enter the new city: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
			        if (newCity == null)
			            System.exit(0);
			        m.setCity(newCity);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member city updated from " + city + " to " + newCity + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;
			    
			    // "State"
        	    case 4:
			        //get new state
			        newState = JOptionPane.showInputDialog(frame, "Enter the new state: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
			        if (newState == null)
                        System.exit(0);
			        while (newState.length() != 2 || (newState.charAt(0) < 65 || newState.charAt(0) > 90) || (newState.charAt(1) < 65 || newState.charAt(1) > 90)){
			            newState = JOptionPane.showInputDialog(frame,"Error: Must enter valid State acronym","ChocAn",JOptionPane.ERROR_MESSAGE);
			            if (newState == null)
	                        System.exit(0);
			        }
			        m.setState(newState);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member state updated from " + state + " to " + newState + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;
			    
			    // "Zip"
        	    case 5:
			      //get new zip
			       idString = JOptionPane.showInputDialog(frame, "Enter the new zip code: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
			        if (idString == null)
			            System.exit(0);
			        try { newZip = Integer.parseInt(idString);
			        } catch (Exception e){}
			        while((newZip <= 9999) || (newZip >= 100000)) { 
			            idString = JOptionPane.showInputDialog(frame,"Must be 5 digit number. Please enter vaild zip code.", "ChocAn", JOptionPane.ERROR_MESSAGE);
			            if (idString == null) 
			                System.exit(0);
			            
			            try { newZip = Integer.parseInt(idString);
			            } catch (Exception e) {}
			        }

			        m.setZip(newZip);
			        members.update(m);
			        JOptionPane.showMessageDialog(frame, "Member zip updated from " + zip + " to " + newZip + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
			        break;

        	    default: 
			        System.exit(0);
			        
        	}
        	
        	//get the new values with newId;
            newName = m.getName();
            newAddress = m.getAddress();
            newCity = m.getCity();
            newState = m.getState();
            newZip = m.getZip();
        	Object [] options2 = {"Name: " + newName, "ID: " + newId, "Address: " + newAddress, "City: " + newCity, "State: " + newState, "Zip: " + newZip,"Done updating Member"};
        	selection = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        }
        
        operatorTerminal();
    }
    
    /**
     * Function to see if add, remove, or update Providers information
     * @param item will always be 'Provider'
     * @throws IOException 
     */
    private static void manageProvider(String item) throws IOException {
        JFrame frame = null;
        //determine where to go
        Object [] options  = {"Add a " + item, "Remove a " + item, "Update a " + item + " info", "Back to Operator Menu", "Return to Main Menu"};
        int ans = JOptionPane.showOptionDialog(frame, "What do you want to do?", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        //if "Add..." is chosen
        if (ans == 0)
            addProvider();
        //if "Remove..." is chosen
        else if (ans == 1)
            deleteProvider();
        //if "Update..." is chosen
        else if (ans == 2) {
            int id = 0;
            String temp = JOptionPane.showInputDialog(frame, "Please enter " + item + " number.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
            //exits if "cancel" or "exit" is pressed
            if (temp == null) 
                System.exit(0);
            //try to parse the string
            try {id = Integer.parseInt(temp);
            } catch (Exception e){}
            //makes sure the number is 9 digits
            while((id <= 99999999) || (id >= 1000000000)) { 
                temp = JOptionPane.showInputDialog(frame,"Must be 9 digit number. Please enter vaild " + item + " number.", "ChocAn", JOptionPane.ERROR_MESSAGE);
                //exits if "cancel" or "exit" is pressed
                if (temp == null) 
                    System.exit(0);
                //try to parse the string
                try { id = Integer.parseInt(temp);
                } catch (Exception e) {}
            }//close while
            updateProvider(id);
        }
        //if "Back.." is chosen
        else if (ans == 3) 
            operatorTerminal();
        //if "Return..." is chosen
        else if (ans == 4)
            return;
        //if exit is pressed
        else if (ans == -1)
            System.exit(0);
        else 
            System.out.println("manageProvider()");
    }//close manageProvider function
    
    /**
     * Function to add a Provider
     * @param item will always be 'Provider'
     * @throws IOException 
     */
    private static void addProvider() throws IOException {
        frame = null;
        
        providers = new ProviderRepository();
        
        int id = 0, zip = 0;
        
        //get name
        String name = JOptionPane.showInputDialog(frame, "Enter a name for the new Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (name == null) 
            System.exit(0);

        // get id
        id = getProviderId();
        
        //get address
        String address = JOptionPane.showInputDialog(frame, "Enter an address for the new Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (address == null)
            System.exit(0);
        
        //get city
        String city = JOptionPane.showInputDialog(frame, "Enter a city for the new Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (city == null)
            System.exit(0);
        
        //get state
        String state = JOptionPane.showInputDialog(frame, "Enter a state for the new Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (state == null)
            System.exit(0);
        
        //get zip
        String idString = JOptionPane.showInputDialog(frame, "Enter a ZIP code for the new Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (idString == null) 
            System.exit(0);
        try { zip = Integer.parseInt(idString);
        } catch (Exception e){}
        // confirm format
        while((zip <= 9999) || (zip >= 100000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 5 digit number. Please enter vaild zip code.", "ChocAn", JOptionPane.ERROR_MESSAGE);
            if (idString == null) 
                System.exit(0);
            try { zip = Integer.parseInt(idString);
            } catch (Exception e) {}
        }
        
        Provider p = new Provider(id, name, address, city, state, zip);
        providers.save(p);
        
        JOptionPane.showMessageDialog(frame, "Provider added!\nName: " + name + "\nNumber: " + id + "\nAddress: " + address + "\nCity: " + city + "\nState: " + state + "\nZip Code: " + zip, "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        //determine where to go
        Object [] options = {"Add another Provider", "Remove a Provider", "Update a Provider's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        // "Add"
        if (selection == 0)
            addProvider();
        // "Remove"
        else if (selection == 1)
            deleteProvider();
        // "Update"
        else if (selection == 2)
            updateProvider(0);
        // "Return to Operator Menu"
        else if (selection == 3)
            operatorTerminal();
        // "Return to Main Menu"
        else if (selection == 4) 
            return;
        // exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("addProvider()");
    }
    
    /**
     * Function to delete Providers
     * @param item will always be 'Provider'
     * @throws IOException 
     */
    private static void deleteProvider() throws IOException {
        frame = null;
                
        int providerId = getProviderId();
        
        providers.deleteById(providerId);

        JOptionPane.showMessageDialog(frame, "Provider " + providerId + " deleted!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        //determine where to go
        Object [] options = {"Add a Provider", "Remove another Provider", "Update a Provider's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addProvider();
        // "Remove"
        else if (selection == 1)
            deleteProvider();
        // "Update"
        else if (selection == 2) 
            updateProvider(0);
        // "Return to Operator Menu"
        else if (selection == 3)
            operatorTerminal();
        // "Return to Main Menu"
        else if (selection == 4) 
            return;
        //if exit is pressed
        else if (selection == -1) 
            System.exit(0);    
        else 
            System.out.println("deleteProvider()");
    }
    
    /**
     * Function to update Providers information
     * @param item will always be 'Provider'
     * @param id the Providers ID you are updating
     * @throws IOException 
     */
    private static void updateProvider(int id) throws IOException {
        JFrame frame = null;
        providers = new ProviderRepository();
        
        String idString, newName = "", newAddress = "", newCity = "", newState = "";
        int newZip = 0;
        
        //if you don't come from manageMember function the id will be 0
        if (id == 0) {
        	id = getProviderId();
        }
        
        //set newId equal to id so that it will be the same unless changed
        int newId = id;
        
        
        Provider p = providers.findById(newId);
        
        
        //look up info using id
        String name = p.getName();
        String address = p.getAddress();
        String city = p.getCity();
        String state = p.getState();
        int zip = p.getZip();
        
        
        Object [] options1 = {"Name: " + name, "ID: " + id, "Address: " + address, "City: " + city, "State: " + state, "Zip: " + zip};
        int updateChoice = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
        
        while (updateChoice != 6) {
            switch (updateChoice) {
                
            	// "Name"
                case 0:
                    //get new name
                    newName = JOptionPane.showInputDialog(frame, "Enter a new name for the Provider: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (newName == null)
                        System.exit(0);
                    p.setName(newName);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider name updated from " + name + " to " + newName + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "ID"
                case 1:
                	//get new ID:
                    newId = getProviderId();
                    p.setId(newId);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider ID updated from " + id + " to " + newId + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "Address"
                case 2:
                    //get new address
                    newAddress = JOptionPane.showInputDialog(frame, "Enter the new address: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (newAddress == null)
                        System.exit(0);
                    p.setAddress(newAddress);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider address updated from " + address + " to " + newAddress + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "City"
                case 3:
                    //get new city
                    newCity = JOptionPane.showInputDialog(frame, "Enter the new city: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (newCity == null)
                        System.exit(0);
                    p.setCity(newCity);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider city updated from " + city + " to " + newCity + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "State"
                case 4:
                    //get new state
                    newState = JOptionPane.showInputDialog(frame, "Enter the new state: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (newState == null)
                        System.exit(0);
                    p.setState(newState);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider state updated from " + state + " to " + newState + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "Zip"
                case 5:
                    //get new zip
                    idString = JOptionPane.showInputDialog(frame, "Enter the new zip code: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (idString == null)
                         System.exit(0);
                     try { newZip = Integer.parseInt(idString);
                     } catch (Exception e){}
                     // confirm format
                     while((newZip <= 9999) || (newZip >= 100000)) { 
                         idString = JOptionPane.showInputDialog(frame,"Must be a 5 digit number. Please enter vaild zip code.", "ChocAn", JOptionPane.ERROR_MESSAGE);
                         if (idString == null) 
                             System.exit(0);
                         try { newZip = Integer.parseInt(idString);
                         } catch (Exception e) {}
                     }

                    p.setZip(newZip);
                    providers.update(p);
                    JOptionPane.showMessageDialog(frame, "Provider zip code updated from " + zip + " to " + newZip + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // exit button is pressed
                default: 
                    System.exit(0);
            }
            
            
            newName = p.getName();
            newAddress = p.getAddress();
            newCity = p.getCity();
            newState = p.getState();
            newZip = p.getZip();
            
            Object [] options2 = {"Name: " + newName, "ID: " + newId, "Address: " + newAddress, "City: " + newCity, "State: " + newState, "Zip: " + newZip,"Done updating Provider"};
            updateChoice = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        }     
        
        
        Object [] options = {"Add a Provider", "Remove a Provider", "Update another Provider's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addProvider();
        // "Remove"
        else if (selection == 1)
            deleteProvider();
        // "Update"
        else if (selection == 2)
            updateProvider(id);
        // "Back"
        else if (selection == 3)
            operatorTerminal();
        // "Return"
        else if (selection == 4) 
            return;
        // exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("updateProvider()");
        
    }
    
    
    
    /**
     * Function to see if you want to add, remove, or update a Service's info
     * @param item will always be 'Service'
     * @throws IOException 
     */
    private static void manageDirectory() throws IOException {
        
    	frame = null;
        
    	
        Object [] options  = {"Add a Service", "Remove a Service", "Update a Service's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addService();
        // "Remove"
        else if (selection == 1)
            deleteService();
        //if "Update..." is chosen
        else if (selection == 2) {
            int serviceId = getServiceId();
            updateService(serviceId);
        }
        // "Back"
        else if (selection == 3) 
            operatorTerminal();
        // "Return to Operator Menu"
        else if (selection == 4)
            return;
        // exit is pressed
        else if (selection == -1)
            System.exit(0);
        else 
            System.out.println("manageDirectory()");
    }
    
    /**
     * Function to add a service 
     * @param item will always be 'Service'
     * @throws IOException 
     */
    private static void addService() throws IOException {
        frame = null;
        
        int serviceId = 0;
        int fee = 0;
        
        //get name
        String name = JOptionPane.showInputDialog(frame, "Enter a name for the new Service:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (name == null)
            System.exit(0);
        
        //get id
        String idString = JOptionPane.showInputDialog(frame, "Enter an ID for the new Service:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        if (idString == null) 
            System.exit(0);
        try { serviceId = Integer.parseInt(idString);
        } catch (Exception e){}
        while((serviceId <= 99999) || (serviceId >= 1000000) || serviceExists(serviceId)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 6 digit number and not already belong to a Service. Please enter vaild Service ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);

            if (idString == null) 
                System.exit(0);

            try { serviceId = Integer.parseInt(idString);
            } catch (Exception e) {}
        }
        
        //get fee
        idString = JOptionPane.showInputDialog(frame, "Enter a fee for the new Service:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        
        if (idString == null)
            System.exit(0);

        try { fee = Integer.parseInt(idString);
        } catch (Exception e){}       

        Service s = new Service(serviceId, name, fee);
        services.save(s);
        
        JOptionPane.showMessageDialog(frame, "Service added!\nName: " + name + "\nNumber: " + serviceId + "\nFee: " + fee, "ChocAn", JOptionPane.INFORMATION_MESSAGE);

        Object [] options = {"Add another Service", "Remove a Service ", "Update a Service's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addService();
        // "Remove"
        else if (selection == 1)
            deleteService();
        // "Update"
        else if (selection == 2)
            updateService(0);
        // "Back"
        else if (selection == 3)
            operatorTerminal();
        // "Return"
        else if (selection == 4) 
            return;
        // exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("addService()");
    }
    
    /**
     * Function to delete a Service
     * @param item will always be 'Service'
     * @throws IOException 
     */
    private static void deleteService() throws IOException {
        frame = null;
        
        int serviceId = getServiceId();
        
        services.deleteById(serviceId);
        
        JOptionPane.showMessageDialog(frame, "Service " + serviceId + " deleted!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
        Object [] options = {"Add a Service", "Remove another Service", "Update a Service's info", "Return to Operator Menu", "Return to Main Menu"};
        int selection = JOptionPane.showOptionDialog(frame, "What do you want to do?", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addService();
        // "Remove"
        else if (selection == 1)
            deleteService();
        // "Update"
        else if (selection == 2)
            updateService(0);
        // "Return to Operator Menu"
        else if (selection == 3)
            operatorTerminal();
        // "Return to Main Menu"
        else if (selection == 4) 
            return;
        // exit is pressed
        else if (selection == -1) 
            System.exit(0);    
        else 
            System.out.println("deleteService()");
    }
    
    /**
     * Function to update a Service's info
     * @param item will always be 'Service'
     * @param id the Service ID
     * @throws IOException 
     */
    private static void updateService(int id) throws IOException {
        frame = null;
        services = new ServiceRepository();
        
        String idString, newName = "";
        int newFee = 0;
        
        //if you don't come from manageMember function the id will be 0
    	if (id == 0) {
            id = getServiceId();
        }
    	
    	int newId = id;
    	
    	Service theService = services.findById(id);
    	
    	//get the info
        String name = theService.getName();
        int fee = theService.getCost();
        
        
        Object [] options1 = {"Name: " + name, "ID: " + id, "Fee: " + fee};
        int selection = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
        
        while (selection != 3) {
            switch (selection) {
                
            	// "Name"
                case 0:
                    // get new name
                    newName = JOptionPane.showInputDialog(frame, "Enter new name for the Service:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (newName == null)
                        System.exit(0);
                    //makes sure the service name isn't already assigned
                    
                    /*
                     * possibly check to make sure no service with that name exists 
                     */
                    theService.setName(newName);
                    services.save(theService);
                    JOptionPane.showMessageDialog(frame, "Service name updated from " + name + " to " + newName + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "ID"
                case 1:
                    // get new ID
                    idString = JOptionPane.showInputDialog(frame, "Please enter Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (idString == null) 
                        System.exit(0);

                    try { newId = Integer.parseInt(idString);
                    } catch (Exception e){}
                    // confirm format
                    while((newId <= 99999) || (newId >= 1000000) || serviceExists(newId)) { 
                        idString = JOptionPane.showInputDialog(frame,"Must be a 6 digit number and not already belong to a Service. Please enter vaild Service ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
                        if (idString == null) 
                            System.exit(0);
                        try { newId = Integer.parseInt(idString);
                        } catch (Exception e) {}
                    }

                    theService.setId(newId);
                    services.save(theService);
                    
                    JOptionPane.showMessageDialog(frame, "Service ID updated from " + id + " to " + newId + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                // "fee"
                case 2:
                    //get new fee
                    idString = JOptionPane.showInputDialog(frame, "Enter a new fee for the Service:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    if (idString == null)
                        System.exit(0);
                    try { newFee = Integer.parseInt(idString);
                    } catch (Exception e){}

                    while(newFee > 999) {
                        idString = JOptionPane.showInputDialog(frame, "Price must be less than 999.99. Enter a new fee for the Service: ", "ChocAn", JOptionPane.ERROR_MESSAGE);
                        if (idString == null)
                            System.exit(0);
                        try { newFee = Integer.parseInt(idString);
                        } catch (Exception e){}
                    }
                    
                    theService.setCost(newFee);
                    services.save(theService);
                    
                    JOptionPane.showMessageDialog(frame, "Service fee updated from " + fee + " to " + newFee + "!", "ChocAn", JOptionPane.INFORMATION_MESSAGE);
                    break;
                // exit button is pressed
                default: 
                    System.exit(0);
            }
            //get the new values
            newName = theService.getName();
            newFee = theService.getCost();
            Object [] options2 = {"Name: " + newName, "ID: " + newId, "Fee: " + newFee, "Done updating Service"};
            selection = JOptionPane.showOptionDialog(frame, "Choose a field to update.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        }
        
        
        Object [] options = {"Add a Service", "Remove a Service", "Update another Service's info", "Return to Operator Menu", "Return to Main Menu"};
        selection = JOptionPane.showOptionDialog(frame, "Choose an operation.", "ChocAn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        // "Add"
        if (selection == 0)
            addService();
        // "Remove"
        else if (selection == 1)
            deleteService();
        // "Update"
        else if (selection == 2)
            updateService(id);
        // "Back"
        else if (selection == 3)
            operatorTerminal();
        // "Return to Operator Menu"
        else if (selection == 4) 
            return;
        // exit is pressed
        else if (selection == -1) 
            System.exit(0);
        else 
            System.out.println("updateService()");
    }
    
    
    
}