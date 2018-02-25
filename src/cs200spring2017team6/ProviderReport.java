package cs200spring2017team6;

import database.*; 
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Create Single or All Provider Reports
 * @author Carson Dobiash
 */
public class ProviderReport {
	private ProviderRepository providers;
	private VisitRepository visits;
	private ServiceRepository services;
	public int feeCount = 0;
	private int consultations = 0;
	
	/**
	 * Constructor
	 * @throws FileNotFoundException
	 */
	public ProviderReport() throws FileNotFoundException {
		providers = new ProviderRepository();
		visits = new VisitRepository();
		services = new ServiceRepository();
	}
	
	/**
	 * Used to list all visits serviced by the provider
	 * @param writer - ensures data is written to same report
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void listService(BufferedWriter writer) throws FileNotFoundException, IOException{
		Visit[] allVisits = visits.getAll();
		MemberRepository mData = new MemberRepository();
		for(int i = 0; i < allVisits.length; ++i){
			writer.write("\nVisit " + (i + 1) + "\nDate: "+ allVisits[i].getDate() + "\nDate data was received: " + allVisits[i].getCurrentDate() + "\nMember name: " +
					mData.findById(allVisits[i].getMemberId()).getName() + "\nMember Id: " + allVisits[i].getMemberId()
					+ "\nService Id: " + allVisits[i].getServiceId() + "\nService Cost: $"
					+ services.findById(allVisits[i].getServiceId()).getCost() + "\n");
			feeCount += services.findById(allVisits[i].getServiceId()).getCost();
			consultations++;
		}
	}
	
	/**
	 * Send All Provider Reports
	 * @throws IOException throws error
	 */
	void providerReport() throws IOException{
		Provider[] allProviders = providers.getAll();
		
		for(int i = 0; i < allProviders.length; ++i) {
			providerReport(allProviders[i].getId());
		}
	}
	/**
	 * Compile and Save a Single Provider Report
	 * @param numberID the id number
	 * @throws IOException throws error
	 */
	public void providerReport(int numberID) throws IOException{
		Provider p = providers.findById(numberID);
		String path = "src/files/";
		String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + p.getName() + "_" + date + ".txt"));

		writer.write("Provider Name: " + p.getName() + "\nProvider Id: " + p.getId() + "\nProvider Address: " + p.getAddress() + "\nProvider City:" + p.getCity() + "\nProvider State: ");
		writer.write(p.getState() + "\nProvider Zipcode: " + p.getZip() + "\n");
		
		listService(writer);
		writer.write("\n\nTotal Consultations: " + consultations);
		writer.write("\nTotal Fee: $" + feeCount + "\n\n");
		
		writer.close();
	}
}

