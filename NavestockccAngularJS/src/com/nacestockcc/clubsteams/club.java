package com.nacestockcc.clubsteams;

public class club {
	private int idClub;
	private String ClubName;
	private String websiteaddress;
	
	
//Object initialisation	
public club(){
	
}

public club(int idClb, String ClbName, String WebAddress){
	this.idClub = idClb;
	this.ClubName = ClbName;
	this.websiteaddress = WebAddress;	
}

//Getter and Setters
	public int getIdClub() {
		return idClub;
	}
	public String getClubName() {
		return ClubName;
	}
	public String getWebsiteaddress() {
		return websiteaddress;
	}
	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	public void setClubName(String clubName) {
		ClubName = clubName;
	}
	public void setWebsiteaddress(String websiteaddress) {
		this.websiteaddress = websiteaddress;
	}
		
}
