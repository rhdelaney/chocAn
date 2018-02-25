package database;
import java.io.*;


public class ProviderRepository {

	
	public ProviderRepository() throws FileNotFoundException {
		String curDir = System.getProperty("user.dir");
		path = curDir + "/bin/database/providers.txt";
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
	
	/**
	 * 
	 * [1] = id
	 * [2] = name
	 * [3] = address
	 * [4] = city
	 * [5] = state
	 * [6] = zip
	 */
	public Provider getProviderFromString(String str) {
		String[] data = readDataString(str);
		return new Provider(Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], Integer.parseInt(data[6]));
	}
	
	public String providerToString(Provider m) {
		return "Provider: " + m.getId() + " \"" + m.getName() + "\" \"" + m.getAddress() + 
				"\" \"" + m.getCity() + "\" \"" + m.getState() + "\" " + m.getZip() + "\n";
	}
	
	/********** core functions ********************/
	public int save(Provider m) throws IOException {
		String data = providerToString(m);
		append(data);
		return m.getId();
	}
	
	public Provider findById(int id) throws FileNotFoundException, IOException {
		return this.getProviderFromString(this.getDataStringById(id));
	}
	
	public void deleteById(int id) throws FileNotFoundException, IOException {
		replaceDataById(id, "");
	}
	
	public Provider update(Provider m) throws FileNotFoundException, IOException {
		String data = providerToString(m);
		this.deleteById(m.getId());
		append(data);
		return this.findById(m.getId());
	}
	
	public Provider[] getAll() throws FileNotFoundException {
		String[] data = readAllData();
		Provider[] providers = new Provider[data.length];
		for (int i = 0; i < data.length; i++) {
			if ((data[i] != null && data[i] != "") && !data[i].isEmpty()) {
				providers[i] = getProviderFromString(data[i]);
			}
		}
		return providers;
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
	
	
}
