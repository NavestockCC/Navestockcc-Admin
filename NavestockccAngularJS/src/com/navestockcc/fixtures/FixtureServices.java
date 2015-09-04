package com.navestockcc.fixtures;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nacestockcc.dbcom.NavestockDbConnection;
import com.navestockcc.fixtures.Fixture;
import com.navestockcc.matchreport.Constants;

@Api(name="navestockccapi", 
version="v1", 		
scopes = {Constants.EMAIL_SCOPE},
clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
audiences = {Constants.ANDROID_AUDIENCE},
namespace = @ApiNamespace(ownerDomain = "navestockcc-002.appspot.com",
ownerName = "navestockcc-002.appspot.com",
packagePath=""),
description="An API to manage Navestock Cricket Club"
)


public class FixtureServices {
	
	@ApiMethod(name = "match.all", httpMethod = ApiMethod.HttpMethod.GET)
	public ArrayList<Fixture> FixtureServicesAll(@Named("teamId") Integer teamId, @Named("matchYear") String matchYear) {
			ArrayList <Fixture> FixtureList = new  ArrayList<Fixture>();
			NavestockDbConnection connObj = new NavestockDbConnection();
			Connection conn = connObj.getNavestockDbConnection();
			
			ResultSet rs;
			try {
				rs = conn.createStatement().executeQuery(getSQL("allmatches", teamId, matchYear));
				rs.first();
				if (rs != null){
					do {
						Fixture fixturedetails = new Fixture(rs.getInt("idMatch"), rs.getInt("NavestockTeamId"), rs.getString("NavestockTeamName"), rs.getInt("OppositionTeamId"), 
								rs.getString("OppositionTeamName"), rs.getString("MatchType"), rs.getString("HomeOrAway") ,rs.getDate("MatchDate"), 
								rs.getString("MatchStartTime"), rs.getInt("idGround"), rs.getString("GroundName"), rs.getString("Address1"), rs.getString("Address2"), 
								rs.getString("AddressTown"), rs.getString("AddressCounty"), rs.getString("PostCode"), rs.getInt("NavestockRuns"), rs.getInt("NavestockWickets"), 
								rs.getInt("OppositionRuns"), rs.getInt("OppositionWickets"), rs.getInt("idTeamWinning") , rs.getString("ResultDescription"), rs.getString("WinningTeamName"));
						FixtureList.add(fixturedetails);
					} while (rs.next());				
				} 
				    connObj.closeNavestockDbConnection(conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return FixtureList;
		}		
	

	@ApiMethod(name = "match.get", httpMethod = ApiMethod.HttpMethod.GET)
	public Fixture FixtureServicesFixture(@Named("matchid") Integer matchid) {
			
		Fixture fixturedetails = null;
		
			NavestockDbConnection connObj = new NavestockDbConnection();
			Connection conn = connObj.getNavestockDbConnection();
			
			
			ResultSet rs;
			try {
				rs = conn.createStatement().executeQuery(getSQL("getmatch", matchid ));
				rs.first();
				if (rs != null){
						 fixturedetails = new Fixture(rs.getInt("idMatch"), rs.getInt("NavestockTeamId"), rs.getString("NavestockTeamName"), rs.getInt("OppositionTeamId"), 
								rs.getString("OppositionTeamName"), rs.getString("MatchType"), rs.getString("HomeOrAway") ,rs.getDate("MatchDate"), 
								rs.getString("MatchStartTime"), rs.getInt("idGround"), rs.getString("GroundName"), rs.getString("Address1"), rs.getString("Address2"), 
								rs.getString("AddressTown"), rs.getString("AddressCounty"), rs.getString("PostCode"), rs.getInt("NavestockRuns"), rs.getInt("NavestockWickets"), 
								rs.getInt("OppositionRuns"), rs.getInt("OppositionWickets"), rs.getInt("idTeamWinning") , rs.getString("ResultDescription"), rs.getString("WinningTeamName"));			
							} 
				    connObj.closeNavestockDbConnection(conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return fixturedetails;
		}	
	
	
	@ApiMethod(name = "match.updateresult", httpMethod = ApiMethod.HttpMethod.PUT)
	public Fixture updateResult(Fixture f){
		
		if(f !=null){
					String updateSql = null;

		
		//Build the SQL Update command			
					updateSql = "Update Matches Set idTeamWinning = " + f.getIdWinningTeam();
					
					if(f.getResultDescription() != null){updateSql = updateSql + ", ResultDescription ='" + f.getResultDescription() + "'";};
					if(f.getMatchType() != null){updateSql = updateSql + ", MatchType ='" + f.getMatchType() + "'";};
					if(f.getNavestockRuns() > -1){updateSql = updateSql + ", NavestockRuns =" + f.getNavestockRuns();};
					if(f.getNavestockWickets() > -1){updateSql = updateSql + ", NavestockWickets =" + f.getNavestockWickets();};
					if(f.getOppositionRuns() > -1){updateSql = updateSql + ", OppositionRuns =" + f.getOppositionRuns();};
					if(f.getOppositionWickets() > -1){updateSql = updateSql + ", OppositionWickets =" + f.getOppositionWickets();};
					
					updateSql = updateSql + " where idMatch = " + f.getMatchId();
		
		//Create DB connection
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();
		
		//Execute query
		try {
				conn.createStatement().executeUpdate(updateSql);
			    connObj.closeNavestockDbConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	};
	
	
	private String getSqlSelect(){
		String Select = null;
		
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
		Select = Select + "Ground.GroundName, ";
		Select = Select + "Ground.Address1, ";
		Select = Select + "Ground.Address2, ";
		Select = Select + "Ground.AddressTown, ";
		Select = Select + "Ground.AddressCounty, "; 
		Select = Select + "Ground.PostCode, ";
		Select = Select + "Matches.idTeamWinning, ";
		Select = Select + "Matches.NavestockRuns, ";
		Select = Select + "Matches.NavestockWickets, ";
		Select = Select + "Matches.OppositionRuns, ";
		Select = Select + "Matches.OppositionWickets, ";
		Select = Select + "(Select concat(Club.ClubName, ' ', teams.TeamName) from teams left join Club on teams.idClub=Club.idClub where teams.TeamId = Matches.idTeamWinning) as WinningTeamName, ";
		Select = Select + "Matches.ResultDescription";
		
		return Select;
	}
	
	
	private String getSQL(String SQLType, int Id){
		String From = null;
		String Where = null;
		String OrderBy = null;

		From = "From Matches	left join (Ground) on (Matches.idGround = Ground.idGround)";		
		OrderBy = "ORDER BY NavestockTeamName asc, MatchYear desc, MatchDate asc";		
		
		switch (SQLType) {
        case "allmatches":  Where = "Where NavestockTeamId = " + Id +  " and year(MatchDate) = year(now())"; 
                 break;
        case "getmatch":  Where = "Where idMatch = " + Id; 
        		 break;
		}
		return getSqlSelect() + " " + From + " " + Where + " " + OrderBy;
	}	
	
	private String getSQL(String SQLType, int Id, String SqlYear){
		String From = null;
		String Where = null;
		String OrderBy = null;

		From = "From Matches	left join (Ground) on (Matches.idGround = Ground.idGround)";		
		OrderBy = "ORDER BY NavestockTeamName asc, MatchYear desc, MatchDate asc";		
		
		switch (SQLType) {
        case "allmatches":  Where = "Where NavestockTeamId = " + Id +  " and year(MatchDate) = '" + SqlYear + "'"; 
                 break;
        case "getmatch":  Where = "Where idMatch = " + Id; 
        		 break;
		}
		return getSqlSelect() + " " + From + " " + Where + " " + OrderBy;
	}
}

