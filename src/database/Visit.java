package database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Visit {
	
	private int Id;
	private int ProviderId;
	private int MemberId;
	private int ServiceId;
	private String CurrentDate;
	private String Date;
	private String Comment;
	
	public Visit(int id, int providerId, int memberId, int serviceId, String date, String comment) {
		this.Id = id;
		this.ProviderId = providerId;
		this.MemberId = memberId;
		this.ServiceId = serviceId;
		this.CurrentDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()).toString();
		this.Date = date;
		this.Comment = comment;
	}
	
	public int getId() { return this.Id; }
	public void setId(int i) { this.Id = i; }
	
	public int getProviderId() { return this.ProviderId; }
	public void setProviderId(int i) { this.ProviderId = i; }
	
	public int getMemberId() { return this.MemberId; }
	public void setMemberId(int i) { this.MemberId = i; }
	
	public int getServiceId() { return this.ServiceId; }
	public void setServiceId(int i) { this.ServiceId = i; }
	
	public String getComment() { return this.Comment; }
	public void setComment(String s) { this.Comment = s; }
	
	public String getCurrentDate(){
		return this.CurrentDate;
	}
	
	public String getDate() { return this.Date; }
	public void setDate(String s){
		this.Date = s;
	}
}
