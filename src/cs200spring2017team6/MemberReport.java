package cs200spring2017team6;
import database.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Creates Member reports (one or all)
 * @author Carson Dobiash
 */
public class MemberReport {
	
	private MemberRepository members;
	private ProviderRepository providers;
	private ServiceRepository services;
	private VisitRepository visits;
	
	/**
	 * Constructor for Member Report
	 * @throws FileNotFoundException
	 */
	public MemberReport() throws FileNotFoundException {
			members = new MemberRepository();
			providers = new ProviderRepository();
			services = new ServiceRepository();
			visits = new VisitRepository();
	}
	/**
	 * Send all member Files (calls create single file for all the members)
	 * @throws IOException
	 */
	void memberReport() throws IOException{
		
		Member[] allMembers = members.getAll();
		
		for (int i = 0; i < allMembers.length; ++i) {
			memberReport(allMembers[i].getId());
		}
	}
	//TODO why print first one twice? check if it even actually is
	/**
	 * Lists all services that the member was billed and their info
	 * @param writer
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void listService(BufferedWriter writer) throws FileNotFoundException, IOException{
		Visit[] allVisits = visits.getAll();
		for(int i = 0; i < allVisits.length; ++i){
			writer.write("\nVisit " + (i + 1) + "\nDate: " + allVisits[i].getDate() + "\nProvider Name: " 
				+ providers.findById(allVisits[i].getProviderId()).getName() + "\nService Name: "
				+ services.findById(allVisits[i].getServiceId()).getName() + "\n");
		}
	}
	
	
	/**
	 * Creates a single Member Report, saves to src/files/
	 * @param id the id number
	 * @throws IOException throws error
	 */
	public void memberReport(int id) throws IOException{
		Member m = members.findById(id);
		String path = "src/files/";
		String date= new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
		BufferedWriter writer = new BufferedWriter(new FileWriter(path + m.getName() + "_" + date + ".txt"));
		
		//write member info to the report file
		writer.write("Member Name: " + m.getName() + "\nID:" + m.getId() + "\nAddress: " + m.getAddress() + "\nCity: " + m.getCity() + "\nState: ");
		writer.write(m.getState() + "\nZipcode: " + m.getZip() + "\n");
		listService(writer);
		writer.close();
	}
}
