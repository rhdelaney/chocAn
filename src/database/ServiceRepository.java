package database;
import java.io.*;


public class ServiceRepository {

	
	public ServiceRepository() throws FileNotFoundException {
		String curDir = System.getProperty("user.dir");
		path = curDir + "/bin/database/services.txt";
	}
	
	
	
	
	
	
	
	
	
	
	public static String path;
	
	public static String findDataStringById(int id) throws FileNotFoundException, IOException {
		
		FileInputStream fstream = new FileInputStream(path);
		//fstream.close();
		//fstream = new FileInputStream(path);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));
		
		
		String line = null;
		String idString = "";
		
		try {
			line = reader.readLine();
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		if (line != null) {
			if (line != "") {
				for (int i = 0; i < line.length(); i++) {
					if (Character.isDigit(line.charAt(i))) {
						idString += line.charAt(i);
					} else if (Character.isSpaceChar(line.charAt(i)) && idString != "") {
						break;
					}
				}
			}
		}
		
		if (idString != "") {
			if (Integer.parseInt(idString) == id) {
				try {
					reader.close();
				} catch(Exception ex) {
					
				}
				return line;
			}
		}
		
		while (line != null) {
			idString = "";
			
			if (line != "") {
				for (int i = 0; i < line.length(); i++) {
					if (Character.isDigit(line.charAt(i))) {
						idString += line.charAt(i);
					} else if (Character.isSpaceChar(line.charAt(i)) && idString != "") {
						break;
					}
				}
				
				if (idString != "") {
					if (Integer.parseInt(idString) == id) {
						try {
							reader.close();
						} catch(Exception ex) {
							
						}
						
						return line;
					}
				}
			}
			try {
				line = reader.readLine();
			} catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
		
		try {
			reader.close();
		} catch(Exception ex) {
			
		}
		
		return null;
	}
	
	public static String[] readDataString(String str) {
		String[] returnThis = new String[100];
		
		for (int i = 0; i < returnThis.length; i++) {
			returnThis[i] = "";
		}
		
		int index = 0;
		int lastWasSpace = 0;
		int awaitingApos = 0;
		for (int i = 0; i < str.length(); i++) {
			if (awaitingApos == 1 || !Character.isSpaceChar(str.charAt(i))) {
				if (str.charAt(i) == '"') {
					if (awaitingApos == 1) {
						awaitingApos = 0;
						lastWasSpace = 0;
					} else {
						awaitingApos = 1;
					}
				}
				else {
					returnThis[index] += str.charAt(i);
					lastWasSpace = 0;
				}
			} else {
				if (lastWasSpace == 0) {
					index++;
					lastWasSpace = 1;
				}
			}
		}
		return returnThis;
	}
	
	
	
	public void replaceText(String oldText, String newText) {
		
		try {
			
			File file = new File(path);
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String line = "", oldtext = "";
	        while((line = reader.readLine()) != null) {
	            oldtext += line + "\r\n";
	        }
	        reader.close();
	        
	        
	        String newtext = oldtext.replaceAll(oldText, newText);
	       
	        FileWriter writer = new FileWriter(path);
	        writer.write(newtext);
	        writer.close();
	        
		} catch(Exception ex) {
			
			System.out.println(ex);
			
		}
		
	}
	
	public void append(String str) throws IOException {
		FileWriter fw = new FileWriter(path, true);
		fw.write(str);
		fw.close();
	}
	
	
	public static String[] readAllData() throws FileNotFoundException {
		

		int size = 0;

		
		
		
		
		
		
		FileInputStream fstream = new FileInputStream(path);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));
		
		
		String line = null;
		
		try {
			line = reader.readLine();
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		if (line != null) {
			if (line != "" && !line.isEmpty()) {
				size++;
			}
		}
		
		while (line != null) {
			if (line != "" && !line.isEmpty()) {
				size++;
			}
			try {
				line = reader.readLine();
			} catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
		
		try {
			reader.close();
		} catch(Exception ex) {
			
		}
		
		
		
		String[] data = new String[size];
		int index = 0;
		
		
		fstream = new FileInputStream(path);
		
		reader = new BufferedReader(new InputStreamReader(fstream));
		
		
		line = null;
		
		try {
			line = reader.readLine();
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		if (line != null) {
			if (line != "" && !line.isEmpty()) {
				data[index] = line;
				index++;
			}
		}
		
		while (line != null) {
			if (line != "" && !line.isEmpty()) {
				data[index] = line;
				index++;
			}
			try {
				line = reader.readLine();
			} catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
		
		try {
			reader.close();
		} catch(Exception ex) {
			
		}
		
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	public void write(String str) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream(path);
		PrintStream printStream = new PrintStream(out);
		printStream.print(str + "\n");
		printStream.close();
	}
	
	/*
	 * 
	 * [1] = id
	 * [2] = name
	 * [3] = cost
	 */
	public Service getServiceFromString(String str) {
		String[] data = readDataString(str);
		return new Service(Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3]));
	}
	
	public String serviceToString(Service m) {
		return "Service: " + m.getId() + " \"" + m.getName() + "\" " + m.getCost() + "\n";
	}
	
	/********** core functions ********************/
	public int save(Service m) throws IOException {
		String data = serviceToString(m);
		append(data);
		return m.getId();
	}
	
	public Service findById(int id) throws FileNotFoundException, IOException {
		return this.getServiceFromString(this.getDataStringById(id));
	}
	
	public void deleteById(int id) throws FileNotFoundException, IOException {
		replaceDataById(id, "");
	}
	
	public Service update(Service m) throws FileNotFoundException, IOException {
		String data = serviceToString(m);
		this.deleteById(m.getId());
		append(data);
		return this.findById(m.getId());
	}
	
	public Service[] getAll() throws FileNotFoundException {
		String[] data = readAllData();
		Service[] services = new Service[data.length];
		for (int i = 0; i < data.length; i++) {
			if ((data[i] != null && data[i] != "") && !data[i].isEmpty()) {
				services[i] = getServiceFromString(data[i]);
			}
		}
		return services;
	}
	/************************************************/
	
	public String getDataStringById(int id) throws FileNotFoundException, IOException {
		return findDataStringById(id);
	}
	
	public void replaceDataString(String oldString, String newString) {
		replaceText(oldString, newString);
	}
	
	public void replaceDataById(int id, String newText) throws FileNotFoundException, IOException {
		replaceDataString(getDataStringById(id), newText);
	}
	
	
	//PROVIDER DIRECTORY
	public void providerDirectory() throws IOException{
		ServiceRepository services = new ServiceRepository();
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/files/ProviderDirectory.txt"));
		Service[] allServices = services.getAll();
		
		for(int i = 0; i < allServices.length - 1; ++i){
			if(allServices[i].getName().charAt(0) > allServices[i + 1].getName().charAt(0)){
				Service temp = allServices[i];
				allServices[i] = allServices[i + 1];
				allServices[i + 1] = temp;
			}
		}
		
		for(int i = 0; i < allServices.length; ++i){
			writer.write("Service name: " + allServices[i].getName() + "\nService Id: " + allServices[i].getId() + "\nService Cost: $"
					+ allServices[i].getCost() + "\n\n");
		}
		writer.close();
	}
	
	
	
	
}
