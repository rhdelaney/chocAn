/**
 * 
 */
package database;

import java.io.*;

/**
 * @author Landon Newberry
 *
 */
public class Program {
	
	public static void write(String s) {
		System.out.println(s);
	}

	private static MemberRepository members;
	
	public Program() {
		try {
			members = new MemberRepository();
		} catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//Program p = new Program();

		int LandonId = members.save(new Member(1, "Landon Newberry", "1249 Clairmont Pl", "Macon", "GA", 31204));
		//int JacobId = members.save(new Member(21, "Jacob Cowles", "Behind Waffle House", "Tuscaloosa", "AL", 35404, "false"));
		
		Member Landon = members.findById(LandonId);
		
		write("Landon's Address: " + Landon.getAddress());
		
		Landon.setAddress("885 W Peachtree St NW");
		Landon = members.update(Landon);
		
		write("Landon's Updated Address: " + Landon.getAddress());
		
		Member Jacob = members.findById(21);
		Jacob.setAddress("Tuscaloosa, AL");
		members.update(Jacob);
		
		write("Jacob's Updated Address: " + Jacob.getAddress());
		
		Member[] allMembers = members.getAll();
		write("Length of all members is " + allMembers.length);
		
		for (int i = 0; i < allMembers.length; i++) {
			write("Name: " + allMembers[i].getName());
		}
	}
	
	

}
