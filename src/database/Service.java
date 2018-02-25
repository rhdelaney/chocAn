package database;

public class Service {
	
	private int Id;
	private String Name;
	private int Cost;
	
	public Service(int id, String name, int cost) {
		this.Id = id;
		this.Name = name;
		this.Cost = cost;
	}
	
	public int getId() {
		return this.Id;
	}
	public void setId(int id){
		this.Id = id;
	}
	public String getName() {
		return this.Name;
	}
	public void setName(String s) {
		this.Name = s;
	}
	public int getCost() { return this.Cost; }
	public void setCost(int c) { this.Cost = c; }
}
