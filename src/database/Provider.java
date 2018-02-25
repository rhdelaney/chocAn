package database;

public class Provider {
	
	private int Id;
	private String Name;
	private String Address;
	private String City;
	private String State;
	private int Zip;
	
	public Provider(int id, String name, String address, String city, String state, int zip) {
		this.Id = id;
		this.Name = name;
		this.Address = address;
		this.City = city;
		this.State = state;
		this.Zip = zip;
	}
	
	public int getId() { return this.Id; }
	public void setId(int i) { this.Id = i; }
	
	public String getName() { return this.Name; }
	public void setName(String i) { this.Name = i; }
	
	public String getAddress() { return this.Address; }
	public void setAddress(String i) { this.Address = i; }
	
	public String getCity() { return this.City; }
	public void setCity(String i) { this.City = i; }
	
	public String getState() { return this.State; }
	public void setState(String s) { this.State = s; }
	
	public int getZip() { return this.Zip; }
	public void setZip(int i) { this.Zip = i; }
}
