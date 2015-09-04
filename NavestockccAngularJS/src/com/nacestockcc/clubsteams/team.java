package com.nacestockcc.clubsteams;

public class team {

	private int TeamId;
	private String TeamName;
	private int idClub;
	private String ClubName;
	private String websiteaddress;
	
	public team(){
		//teams.TeamId, teams.TeamName, Club.idClub, Club.ClubName, Club.website
	}

	public team(int tmId, String tNme, int idClb, String ClbNme, String wbSite){
		this.TeamId = tmId;
		this.TeamName = tNme;
		this.idClub = idClb;
		this.ClubName = ClbNme;
		this.websiteaddress = wbSite;
	}
	
	
//Getter and Setters	
	public int getTeamId() {
		return TeamId;
	}

	public String getTeamName() {
		return TeamName;
	}

	public int getIdClub() {
		return idClub;
	}

	public String getClubName() {
		return ClubName;
	}

	public String getWebsiteaddress() {
		return websiteaddress;
	}

	public void setTeamId(int teamId) {
		TeamId = teamId;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
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
