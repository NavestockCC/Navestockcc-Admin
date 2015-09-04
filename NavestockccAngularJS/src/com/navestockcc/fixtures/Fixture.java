package com.navestockcc.fixtures;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.nacestockcc.dbcom.NavestockDbConnection;

public class Fixture {


	private int matchId;
	private int navestockTeamId;
	private String navestockTeamName; 
	private int oppositionTeamId;
	private String oppositionTeamName;
	private String matchType;
	private String homeOrAway;
	private Date matchDate;
	private String matchStartTime;
	private int idGround;
	private String groundName;
	private String groundAddress1;
	private String groundAddress2;
	private String groundAddressTown;
	private String groundAddressCounty;
	private String groundPostCode;
	private int idWinningTeam;
	private String winningTeamName;
	private String resultDescription;
	private int navestockRuns;
	private int navestockWickets;
	private int oppositionRuns;
	private int oppositionWickets;
	
	
	public Fixture() {

	}
		
		
	//FixtureList constructor
	public Fixture(int vmatchId, int vnavestockTeamId, String vnavestockTeamName, int voppositionTeamId, String voppositionTeamName, 
				String vmatchType, String vhomeOrAway ,Date vmatchDate, String vmatchStartTime, int vgroundId, String vgroundName, 
				String vgroundAddress1, String vgroundAddress2, String vgroundAddressTown, String vgroundAddressCounty, String vgroundPostCode, 
				int vnavestockRuns, int vnavestockWickets, int voppositionRuns, int voppositionWickets, int vidWinningTeam, String vresultDescription, String vwinningTeamName ){
			this.setMatchId(vmatchId);
			this.setNavestockTeamId(vnavestockTeamId);
			this.setNavestockTeamName(vnavestockTeamName);
			this.setOppositionTeamId(voppositionTeamId);
			this.setOppositionTeamName(voppositionTeamName);
			this.setMatchType(vmatchType);
			this.setHomeOrAway(vhomeOrAway);
			this.setMatchDate(vmatchDate);
			this.setMatchStartTime(vmatchStartTime);
			this.setIdGround(vgroundId);
			this.setGroundName(vgroundName);
			this.setGroundAddress1(vgroundAddress1);
			this.setGroundAddress2(vgroundAddress2);
			this.setGroundAddressTown(vgroundAddressTown);
			this.setGroundAddressCounty(vgroundAddressCounty);
			this.setGroundPostCode(vgroundPostCode);
			this.setNavestockRuns(vnavestockRuns);
			this.setNavestockWickets(vnavestockWickets);
			this.setOppositionRuns(voppositionRuns);
			this.setOppositionWickets(voppositionWickets);
			this.setWinningTeamName(vwinningTeamName);
			this.setIdWinningTeam(vidWinningTeam);
			this.setResultDescription(vresultDescription);
		}

	
public int getNavestockRuns() {
		return navestockRuns;
	}


	public int getNavestockWickets() {
		return navestockWickets;
	}


	public int getOppositionRuns() {
		return oppositionRuns;
	}


	public int getOppositionWickets() {
		return oppositionWickets;
	}


	public void setNavestockRuns(int navestockRuns) {
		this.navestockRuns = navestockRuns;
	}


	public void setNavestockWickets(int navestockWickets) {
		this.navestockWickets = navestockWickets;
	}


	public void setOppositionRuns(int oppositionRuns) {
		this.oppositionRuns = oppositionRuns;
	}


	public void setOppositionWickets(int oppositionWickets) {
		this.oppositionWickets = oppositionWickets;
	}


private String SelectFictreSQL(int matchId){
	String Select = null;
	String From = null;
	String Where = null;
	String OrderBy = null;

	
	Select = "Select idMatch, "; 
	Select = Select + "NavestockTeamId, "; 
	Select = Select + "(Select concat(Club.ClubName, ' : ', teams.TeamName) from teams left join Club on teams.idClub=Club.idClub where teams.TeamId = Matches.NavestockTeamId) as NavestockTeamName, "; 
	Select = Select + "OppositionTeamId, ";	
	Select = Select + "(Select concat(Club.ClubName, ' : ', teams.TeamName) from teams left join Club on teams.idClub=Club.idClub where teams.TeamId = Matches.OppositionTeamId) as OppositionTeamName, "; 
	Select = Select + "MatchType, ";
	Select = Select + "HomeOrAway, ";
	Select = Select + "year(MatchDate) as MatchYear, ";
	Select = Select + "MatchDate, "; 
	Select = Select + "MatchStartTime, "; 
	Select = Select + "Matches.idGround, ";
	Select = Select + "Matches.idTeamWinning, ";
	Select = Select + "Matches.NavestockRuns, ";
	Select = Select + "Matches.NavestockWickets, ";
	Select = Select + "Matches.OppositionRuns, ";
	Select = Select + "Matches.OppositionWickets, ";	
	Select = Select + "Ground.GroundName, ";
	Select = Select + "Ground.Address1, ";
	Select = Select + "Ground.Address2, ";
	Select = Select + "Ground.AddressTown, ";
	Select = Select + "Ground.AddressCounty, "; 
	Select = Select + "Ground.PostCode, ";
	Select = Select + "(Select concat(Club.ClubName, ' ', teams.TeamName) from teams left join Club on teams.idClub=Club.idClub where teams.TeamId = Matches.idTeamWinning) as WinningTeamName, ";
	Select = Select + "Matches.ResultDescription";
	From = "From Matches	left join (Ground) on (Matches.idGround = Ground.idGround)";
	OrderBy = "ORDER BY NavestockTeamName asc, MatchYear desc, MatchDate asc";
	Where = "Where idMatch = " +  matchId;
		
	return "(" + Select + " " + From + " " + Where + " " + OrderBy + ")";
}

	
	
	
// Getter methods
	
	public void getFixtureDB(int MatchId){
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();
		
		String SQL = SelectFictreSQL(MatchId);
		try {
			ResultSet rs = conn.createStatement().executeQuery(SQL);
			if (rs != null){
				rs.first();
				this.setMatchId(rs.getInt("idMatch"));
				this.setNavestockTeamId(rs.getInt("NavestockTeamId"));
				this.setNavestockTeamName(rs.getString("NavestockTeamName"));
				this.setOppositionTeamId(rs.getInt("OppositionTeamId"));
				this.setOppositionTeamName(rs.getString("OppositionTeamName"));
				this.setMatchType(rs.getString("MatchType"));
				this.setHomeOrAway(rs.getString("HomeOrAway"));
				this.setMatchDate(rs.getDate("MatchDate"));
				this.setMatchStartTime(rs.getString("MatchStartTime"));
				this.setIdGround(rs.getInt("idGround"));
				this.setGroundName(rs.getString("GroundName"));
				this.setGroundAddress1(rs.getString("Address1"));
				this.setGroundAddress2(rs.getString("Address2"));
				this.setGroundAddressTown(rs.getString("AddressTown"));
				this.setGroundAddressCounty(rs.getString("AddressCounty"));
				this.setGroundPostCode(rs.getString("PostCode"));
				this.setIdWinningTeam(rs.getInt("idTeamWinning"));
				this.setWinningTeamName(rs.getString("WinningTeamName"));
				this.setResultDescription(rs.getString("ResultDescription"));
				this.setNavestockRuns(rs.getInt("NavestockRuns"));
				this.setNavestockWickets(rs.getInt("NavestockWickets"));
				this.setOppositionRuns(rs.getInt("OppositionRuns"));
				this.setOppositionWickets(rs.getInt("OppositionWickets"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connObj.closeNavestockDbConnection(conn);
	}
	
		
		public int getMatchId() {
			return matchId;
		}


		public int getNavestockTeamId() {
			return navestockTeamId;
		}


		public String getNavestockTeamName() {
			return navestockTeamName;
		}


		public int getOppositionTeamId() {
			return oppositionTeamId;
		}


		public String getOppositionTeamName() {
			return oppositionTeamName;
		}


		public String getMatchType() {
			return matchType;
		}


		public String getHomeOrAway() {
			return homeOrAway;
		}


		public Date getMatchDate() {
			return matchDate;
		}


		public String getMatchStartTime() {
			return matchStartTime;
		}


		public int getIdGround() {
			return idGround;
		}


		public String getArrderssLong(char DisplayVerticalorHorisontal){
			
			String Disp = "";
			String VH = "";
			
			if(DisplayVerticalorHorisontal == 'V'){
				VH = "<br>";
			}else{
				VH = ", ";
			}
			
			
			Disp = this.groundName;
			
			if(this.groundAddress1!=null && !this.groundAddress1.isEmpty()){
				Disp = Disp + VH +this.groundAddress1;
			}	
			if(this.groundAddress2!=null && !this.groundAddress2.isEmpty()){
				Disp = Disp + VH + this.groundAddress2;
			}
			if(this.groundAddressTown!=null && !this.groundAddressTown.isEmpty()){
				Disp = Disp + VH + this.groundAddressTown;
			}
			if(this.groundAddressCounty!=null && !this.groundAddressCounty.isEmpty()){
				Disp = Disp + VH + this.groundAddressCounty;
			}
			if(this.groundPostCode!=null && !this.groundPostCode.isEmpty()){
				Disp = Disp + VH + this.groundPostCode;
			}			
			
			return Disp;
		}
		
		
		public String getGroundName() {
			return groundName;
		}


		public String getGroundAddress1() {
			return groundAddress1;
		}


		public String getGroundAddress2() {
			return groundAddress2;
		}


		public String getGroundAddressTown() {
			return groundAddressTown;
		}


		public String getGroundAddressCounty() {
			return groundAddressCounty;
		}


		public String getGroundPostCode() {
			return groundPostCode;
		}

		
// Setter Methods		
		public void setMatchId(int matchId) {
			this.matchId = matchId;
		}


		public void setNavestockTeamId(int navestockTeamId) {
			this.navestockTeamId = navestockTeamId;
		}


		public void setNavestockTeamName(String navestockTeamName) {
			this.navestockTeamName = navestockTeamName;
		}


		public void setOppositionTeamId(int oppositionTeamId) {
			this.oppositionTeamId = oppositionTeamId;
		}


		public void setOppositionTeamName(String oppositionTeamName) {
			this.oppositionTeamName = oppositionTeamName;
		}


		public void setMatchType(String matchType) {
			if(matchType != null && !matchType.isEmpty()){
				this.matchType = matchType;
			} else{
				this.matchType = "Match Format, to be advised";
			}
		}


		public void setHomeOrAway(String homeOrAway) {
			if(homeOrAway != null && !homeOrAway.isEmpty()){
				this.homeOrAway = homeOrAway;
			} else{
				this.homeOrAway = "Home or Away, to be advised";
			}
			
			
		}


		public void setMatchDate(Date matchDate) {
				this.matchDate = matchDate;
		}


		public void setMatchStartTime(String matchStartTime) {
			if(matchStartTime != null && !matchStartTime.isEmpty()){
				this.matchStartTime = matchStartTime;
			}else{
				this.matchStartTime = "Start Time, to be advised";
			}
		}


		public void setIdGround(int idGround) {
			this.idGround = idGround;
		}


		public void setGroundName(String groundName) {
			if(groundName != null && !groundName.isEmpty()){
				this.groundName = groundName;
			}else{
				this.groundName = "Ground to be advised";
			}
		}


		public void setGroundAddress1(String groundAddress1) {
			this.groundAddress1 = groundAddress1;
		}


		public void setGroundAddress2(String groundAddress2) {
			this.groundAddress2 = groundAddress2;
		}


		public void setGroundAddressTown(String groundAddressTown) {
			this.groundAddressTown = groundAddressTown;
		}


		public void setGroundAddressCounty(String groundAddressCounty) {
			this.groundAddressCounty = groundAddressCounty;
		}


		public void setGroundPostCode(String groundPostCode) {
			this.groundPostCode = groundPostCode;
		}


		public int getIdWinningTeam() {
			return idWinningTeam;
		}


		public void setIdWinningTeam(int idWinningTeam) {
			this.idWinningTeam = idWinningTeam;
		}


		public String getWinningTeamName() {
			return winningTeamName;
		}


		public void setWinningTeamName(String winningTeamName) {
			this.winningTeamName = winningTeamName;
		}


		public String getResultDescription() {
			return resultDescription;
		}


		public void setResultDescription(String resultDescription) {
			this.resultDescription = resultDescription;
		}

		public String getFixtureStatus(){
			if(this.idWinningTeam > 0){
				return this.winningTeamName + " won by " + this.resultDescription + "<br>" + this.navestockTeamName + " " + this.navestockRuns + "/" + this.navestockWickets + " v " + this.oppositionTeamName + " " + this.oppositionRuns + "/" + this.oppositionWickets;
			}else if(this.idWinningTeam == -3){ 
				return "Fixture drawn. " + "<br>" + this.navestockTeamName + " " + this.navestockRuns + "/" + this.navestockWickets + " v " + this.oppositionTeamName + " " + this.oppositionRuns + "/" + this.oppositionWickets;
			} else{
				return this.winningTeamName + " " + this.resultDescription;
			}
		}



}

