package cs200spring2017team6;
import database.*;
import java.io.*;
/**
 * Make a Summary Report
 * @author Carson Dobiash
 *
 */

public class SummaryReport {
	
	private VisitRepository visits;
	private ProviderRepository providers;
	private ServiceRepository services;
	
	/**
	 * Constructor
	 * @throws FileNotFoundException
	 */
	public SummaryReport() throws FileNotFoundException{
		visits = new VisitRepository();
		providers = new ProviderRepository();
		services = new ServiceRepository();
	}
	
	
	/**
	 * Creates the summary report
	 * @throws IOException
	 */
	void summaryReport() throws IOException{
		//Provider, num consultations, total fee
		//total consultations, overall fee
		int overallFee = 0, overallConsultations = 0;
		int consultations = 0, feeCount = 0;
		String path = "src/files/";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + "SummaryReport.txt"));

		Provider[] p = providers.getAll();
		for(int i = 0; i < p.length; ++i){
			writer.write("Provider Name: " + p[i].getName());
			
			Visit[] allVisits = visits.getAll();
			for(int j = 0; j < allVisits.length; ++j){
				if(allVisits[j].getProviderId() == p[i].getId()){
					feeCount += services.findById(allVisits[j].getServiceId()).getCost();
					consultations++;
				}
			}
			overallFee += feeCount;
			overallConsultations += consultations;
			writer.write("\nNumber of Consultations:" + consultations);
			writer.write("\nFee: " + feeCount + "\n\n");
			consultations = 0;
			feeCount = 0;
		}
		
		writer.write("\nTotal consultations for all Providers: " + overallConsultations);
		writer.write("\nTotal to be paid for all Providers: " + overallFee);
		writer.close();
	}
}
