package com.navestockcc.stats;

public class ScoreCard {
	
	private int idClub;
	private String clubName;
	private int teamId;
	private String teamName;
	private int idPlayer;
	private String firstname;
	private String lastname;
	private int batingOrder;
	private int runsScored;
	private int idHowOut;
	private String howOutDescription;
	private int oversBowled;
	private int wickets;
	private int runsConseded;
	private int idMatch;
	private boolean objServiceSaved;
	private boolean objServiceDB;
	private int objServiceDBAction;
	
	
	
	public ScoreCard() {
		
	}
	
	public ScoreCard(int idClb, String clbNme, int tmId, String teamNme, int idPlyer, String firstnme, String lastnme, int batingOrdr, int runsScred, int idHwOut, String howOutDescrptn, int oversBwld, int wckts, int runsCnseded, int idMtch){
		this.idClub = idClb;
		this.clubName = clbNme;
		this.teamId = tmId;
		this.teamName = teamNme;
		this.idPlayer = idPlyer;
		this.firstname =  firstnme;
		this.lastname = lastnme;
		this.batingOrder = batingOrdr;
		this.runsScored = runsScred;
		this.idHowOut = idHwOut;
		this.howOutDescription = howOutDescrptn;
		this.oversBowled = oversBwld;
		this.wickets = wckts;
		this.runsConseded = runsCnseded;
		this.idMatch = idMtch;
		this.objServiceSaved = true;
		this.objServiceDB = true;
	}

	public int getIdClub() {
		return this.idClub;
	}

	public String getClubName() {
		return this.clubName;
	}

	public int getTeamId() {
		return this.teamId;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public int getIdPlayer() {
		return this.idPlayer;
	}
	
	public String getPlayer(){
		return this.firstname + " " + this.lastname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public int getBatingOrder() {
		return this.batingOrder;
	}

	public int getRunsScored() {
		return this.runsScored;
	}

	public int getIdHowOut() {
		return this.idHowOut;
	}

	public String getHowOutDescription() {
		return this.howOutDescription;
	}

	public int getOversBowled() {
		return this.oversBowled;
	}

	public int getWickets() {
		return this.wickets;
	}

	public int getRunsConseded() {
		return this.runsConseded;
	}

	public int getIdMatch() {
		return idMatch;
	}

	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBatingOrder(int batingOrder) {
		this.batingOrder = batingOrder;
	}

	public void setRunsScored(int runsScored) {
		this.runsScored = runsScored;
	}

	public void setIdHowOut(int idHowOut) {
		this.idHowOut = idHowOut;
	}

	public void setHowOutDescription(String howOutDescription) {
		this.howOutDescription = howOutDescription;
	}

	public void setOversBowled(int oversBowled) {
		this.oversBowled = oversBowled;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public void setRunsConseded(int runsConseded) {
		this.runsConseded = runsConseded;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public boolean getObjServiceSaved() {
		return objServiceSaved;
	}

	public boolean getObjServiceDB() {
		return objServiceDB;
	}

	public int getObjServiceDBAction() {
		return objServiceDBAction;
	}

	public void setObjServiceSaved(boolean objServiceSaved) {
		this.objServiceSaved = objServiceSaved;
	}

	public void setObjServiceDB(boolean objServiceDB) {
		this.objServiceDB = objServiceDB;
	}

	public void setObjServiceDBAction(int objServiceDBAction) {
		this.objServiceDBAction = objServiceDBAction;
	}

	
	
}
