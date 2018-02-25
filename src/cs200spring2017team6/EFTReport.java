package cs200spring2017team6;
import database.*;
import java.io.*;
/**
 * 
 * @author Carson Dobiash
 * Produces an EFTReport and saves it
 */

public class EFTReport {

	private ProviderRepository providers;
	private ServiceRepository services;
	private VisitRepository visits;
	
	/**
	 * Constructor for EFTReport
	 * @throws FileNotFoundException
	 */
	EFTReport() throws FileNotFoundException{
		providers = new ProviderRepository();
		services = new ServiceRepository();
		visits = new VisitRepository();
	}
	
	/**
	 * Creates an EFTReport in the src/files folder
	 * @throws IOException
	 */
	void eftReport() throws IOException{
		Provider[] allProviders = providers.getAll(); 
		Visit[] allVisits = visits.getAll();
		String path = "src/files/";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + "EFTReport.txt"));
		int feeCount = 0;
		
		for(int i = 0; i < allVisits.length; ++i){
			feeCount += services.findById(allVisits[i].getServiceId()).getCost();
		}
		
		for(int i = 0; i < allProviders.length; ++i){
			writer.write("ProviderName: " + allProviders[i].getName() + "\nProviderId: " + allProviders[i].getId() + "\nTotal to be paid: $" + feeCount + "\n\n");
		}
		
		writer.close();
	}
}
