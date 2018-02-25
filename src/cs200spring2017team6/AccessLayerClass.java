package cs200spring2017team6;
import database.*;
import java.io.*;
import javax.swing.*;

public class AccessLayerClass {

	public static JFrame frame;
	public static MemberRepository members;
	public static ProviderRepository providers;
	public static ServiceRepository services;
	public static VisitRepository visits;
	
	public AccessLayerClass() throws FileNotFoundException {
		members = new MemberRepository();
		providers = new ProviderRepository();
		services = new ServiceRepository();
		visits = new VisitRepository();
		frame = null;
	}
	
	
	public static boolean memberExists(int id) throws FileNotFoundException {
		members = new MemberRepository();
		Member[] all = members.getAll();
		for (int i = 0; i < all.length; i++) {
			if (all[i].getId() == id) return true;
		}
		return false;
	}
	
	
	public static boolean serviceExists(int id) throws FileNotFoundException {
		services = new ServiceRepository();
		Service[] all = services.getAll();
		for (int i = 0; i < all.length; i++) {
			if (all[i].getId() == id) return true;
		}
		return false;
	}
	
	public static boolean providerExists(int id) throws FileNotFoundException {
		providers = new ProviderRepository();
		Provider[] all = providers.getAll();
		for (int i = 0; i < all.length; i++) {
			if (all[i].getId() == id) return true;
		}
		return false;
	}
	
	
	public static int getValidProviderId() throws FileNotFoundException {
		frame = null;
        int providerId = 0;
        
        String idString = JOptionPane.showInputDialog(frame, "Please enter a Provider ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

        if (idString == null) 
            System.exit(0);

        try { providerId = Integer.parseInt(idString);
        } catch (Exception e){}
        
        while((providerId <= 99999999) || (providerId >= 1000000000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Provider ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
          
            if (idString == null) 
                System.exit(0);
            
            try { providerId = Integer.parseInt(idString);
            } catch (Exception e){}
        }
        
        while (!providerExists(providerId)) {
        	idString = JOptionPane.showInputDialog(frame, "No provider exists with that ID. Please enter a valid Provider ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

            if (idString == null) 
                System.exit(0);

            try { providerId = Integer.parseInt(idString);
            } catch (Exception e){}
            
            while((providerId <= 99999999) || (providerId >= 1000000000)) { 
                idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Provider ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
              
                if (idString == null) 
                    System.exit(0);
                
                try { providerId = Integer.parseInt(idString);
                } catch (Exception e){}
            }
        }
        
        return providerId;
	}
	
	
	
	
	
	
	public static int getProviderId() throws FileNotFoundException {
		frame = null;
        int providerId = 0;
        
        String idString = JOptionPane.showInputDialog(frame, "Please enter a Provider ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

        if (idString == null) 
            System.exit(0);

        try { providerId = Integer.parseInt(idString);
        } catch (Exception e){}
        
        while((providerId <= 99999999) || (providerId >= 1000000000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Provider ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
          
            if (idString == null) 
                System.exit(0);
            
            try { providerId = Integer.parseInt(idString);
            } catch (Exception e){}
        }
        
        return providerId;
	}
	
	
	
	
	
	
	
	
	
	
	public static int getValidMemberId() throws FileNotFoundException {
		frame = null;
        int memberId = 0;
        
        String idString = JOptionPane.showInputDialog(frame, "Please enter a Member ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

        if (idString == null) 
            System.exit(0);

        try { memberId = Integer.parseInt(idString);
        } catch (Exception e){}
        
        while((memberId <= 99999999) || (memberId >= 1000000000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Member ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
          
            if (idString == null) 
                System.exit(0);
            
            try { memberId = Integer.parseInt(idString);
            } catch (Exception e){}
        }
        
        
        while (!memberExists(memberId)) {
        	idString = JOptionPane.showInputDialog(frame, "No member exists with that ID. Please enter a valid Member ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

            if (idString == null) 
                System.exit(0);

            try { memberId = Integer.parseInt(idString);
            } catch (Exception e){}
            
            while((memberId <= 99999999) || (memberId >= 1000000000)) { 
                idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Member ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
              
                if (idString == null) 
                    System.exit(0);
                
                try { memberId = Integer.parseInt(idString);
                } catch (Exception e){}
            }
        }
        
        return memberId;
	}
	
	
	
	public static int getMemberId() throws FileNotFoundException {
		frame = null;
        int memberId = 0;
        
        String idString = JOptionPane.showInputDialog(frame, "Please enter a Member ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);

        if (idString == null) 
            System.exit(0);

        try { memberId = Integer.parseInt(idString);
        } catch (Exception e){}
        
        while((memberId <= 99999999) || (memberId >= 1000000000)) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 9 digit number. Please enter valid Member ID.", "ChocAn", JOptionPane.ERROR_MESSAGE);
          
            if (idString == null) 
                System.exit(0);
            
            try { memberId = Integer.parseInt(idString);
            } catch (Exception e){}
        }
        
        return memberId;
	}
	
	
	
	
	
	
	public static int getValidServiceId() throws FileNotFoundException, IOException {
		services = new ServiceRepository();
		frame = null;
		int serviceId = 0;
		
		String idString = JOptionPane.showInputDialog(frame,"Enter the Service ID:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        
        if (idString == null)
            System.exit(0);
        
        try { serviceId = Integer.parseInt(idString);
        } catch (Exception e) {}
        
        while (!serviceExists(serviceId)) {
    		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
            
            if (idString == null)
                System.exit(0);
            
            try { serviceId = Integer.parseInt(idString);
            } catch (Exception e) {}
    	}
        
        
        if (serviceId > 99999 && serviceId < 1000000) {
        	
        	
        	Service theService = services.findById(serviceId);
            String serviceName = theService.getName();
            
            int selection = JOptionPane.showConfirmDialog(frame, "Is " + serviceName + " the correct service?", "ChocAn", JOptionPane.YES_NO_OPTION);

            if (selection == -1) {
            	System.exit(0);
            }

            while (selection == 1) {
                idString = JOptionPane.showInputDialog(frame,"Enter the Service ID: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                
                if (idString == null)
                    System.exit(0);
                
                try { serviceId = Integer.parseInt(idString);
                } catch (Exception e) {}
                
                while (!serviceExists(serviceId)) {
            		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    
                    if (idString == null)
                        System.exit(0);
                    
                    try { serviceId = Integer.parseInt(idString);
                    } catch (Exception e) {}
            	}
                
                
                theService = services.findById(serviceId);
                serviceName = theService.getName();
                
                
                
                while (!serviceExists(serviceId)) {
            		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                    
                    if (idString == null)
                        System.exit(0);
                    
                    try { serviceId = Integer.parseInt(idString);
                    } catch (Exception e) {}
            	}
                
                if (serviceId > 99999 && serviceId < 1000000) {

                    selection = JOptionPane.showConfirmDialog(frame, "Is " + serviceName + " the correct service?", "ChocAn", JOptionPane.YES_NO_OPTION);
                    if (selection == 1) {
                        return getServiceId();
                    }
                }
                else 
                    selection = 0;
            }
        }
            
        
        
        while (!serviceExists(serviceId)) {
    		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
            
            if (idString == null)
                System.exit(0);
            
            try { serviceId = Integer.parseInt(idString);
            } catch (Exception e) {}
    	}
        
        while(serviceId <= 99999 || serviceId >= 1000000) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 6 digit number. Enter valid service code", "ChocAn", JOptionPane.ERROR_MESSAGE);

            if (idString == null)
                System.exit(0);

            try { serviceId = Integer.parseInt(idString);
            } catch (Exception e) {}

            while (!serviceExists(serviceId)) {
        		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                
                if (idString == null)
                    System.exit(0);
                
                try { serviceId = Integer.parseInt(idString);
                } catch (Exception e) {}
        	}
            
            
            if (serviceId > 99999 && serviceId < 1000000) {
                Service theService = services.findById(serviceId);
                String serviceName = theService.getName();
                
                int selection = JOptionPane.showConfirmDialog(frame, "Is " + serviceName + " the correct service?", "ChocAn", JOptionPane.YES_NO_OPTION);

                if (selection == -1) {
                    System.exit(0);
                }

                while (selection == 1) {
                    idString = JOptionPane.showInputDialog(frame,"Enter the service code: ", "ChocAn", JOptionPane.QUESTION_MESSAGE);

                    if (idString == null)
                        System.exit(0);

                    try { serviceId = Integer.parseInt(idString);
                    } catch (Exception e) {}

                    theService = services.findById(serviceId);
                    serviceName = theService.getName();
                    
                    
                    while (!serviceExists(serviceId)) {
                		idString = JOptionPane.showInputDialog(frame,"No Service exists with that ID. Please enter a valid Service ID.", "ChocAn", JOptionPane.QUESTION_MESSAGE);
                        
                        if (idString == null)
                            System.exit(0);
                        
                        try { serviceId = Integer.parseInt(idString);
                        } catch (Exception e) {}
                	}

                    if (serviceId > 99999 && serviceId < 1000000) {
                        selection = JOptionPane.showConfirmDialog(frame, "Is " + serviceName + " the correct service?", "ChocAn", JOptionPane.YES_NO_OPTION);

                        if (selection == -1) {
                            return serviceId;
                        }
                    }
                    else 
                        selection = 0;
                }
            }
        }
        
        
        return serviceId;
	}
	
	
	
	
	
	
	
	
	
	
	public static int getServiceId() throws FileNotFoundException, IOException {
		services = new ServiceRepository();
		frame = null;
		int serviceId = 0;
		
		String idString = JOptionPane.showInputDialog(frame,"Enter the Service ID:", "ChocAn", JOptionPane.QUESTION_MESSAGE);
        
        if (idString == null)
            System.exit(0);
        
        try { serviceId = Integer.parseInt(idString);
        } catch (Exception e) {}
        
        
        if (serviceId > 99999 && serviceId < 1000000) {
        	
        	return serviceId;
        	
        }
            
        while(serviceId <= 99999 || serviceId >= 1000000) { 
            idString = JOptionPane.showInputDialog(frame,"Must be a 6 digit number. Enter valid service code", "ChocAn", JOptionPane.ERROR_MESSAGE);

            if (idString == null)
                System.exit(0);

            try { serviceId = Integer.parseInt(idString);
            } catch (Exception e) {}
            
            
            if (serviceId > 99999 && serviceId < 1000000) {
                return serviceId;
            }
            
            return getServiceId();
        }
        
        
        return serviceId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
