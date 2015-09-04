package com.nacestockcc.clubsteams;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.navestockcc.matchreport.Constants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nacestockcc.dbcom.NavestockDbConnection;


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


public class ClubServices {
	
// Get Club or List of clubs
	@ApiMethod(name = "club.all", httpMethod = ApiMethod.HttpMethod.GET)
	public ArrayList<club> ClubAll() {
		ArrayList<club> ClubList = new ArrayList<club>();
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();

		ResultSet rs;
		try {
			rs = conn
					.createStatement()
					.executeQuery("select idClub, ClubName, website from Club order by ClubName");
			rs.first();
			if (rs != null) {
				do {
					club ClubDetails = new club(rs.getInt("idClub"),
												rs.getString("ClubName"), 
												rs.getString("website")
												);
					ClubList.add(ClubDetails);
					}while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connObj.closeNavestockDbConnection(conn);
		return ClubList;
	}
	
	@ApiMethod(name = "club.getclub", httpMethod = ApiMethod.HttpMethod.GET)
	public club getClub(@Named("clubId") Integer clubId) {
		club ClubDetails = null;
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();

		ResultSet rs;
		try {
			rs = conn
					.createStatement()
					.executeQuery("select idClub, ClubName, website from Club where idClub=" + clubId);
			rs.first();
			if (rs != null) {
				ClubDetails = new club(rs.getInt("idClub"),
											rs.getString("ClubName"), 
											rs.getString("website")
											);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connObj.closeNavestockDbConnection(conn);
		return ClubDetails;
	}

//Update Club	
	// TODO Add club update and add service
	
//Get Team and Team List
	@ApiMethod(name = "club.allTeams", httpMethod = ApiMethod.HttpMethod.GET)
	public ArrayList<team> TeamAll(){
		ArrayList<team> TeamList = new ArrayList<team>();
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();

		ResultSet rs;
		try {
			rs = conn
					.createStatement()
					.executeQuery("select teams.TeamId, teams.TeamName, Club.idClub, Club.ClubName, Club.website from teams left join Club on teams.idClub = Club.idClub where teams.TeamId > 0 order by ClubName, TeamName");
			rs.first();
			if (rs != null) {
				do {
					team TeamDetails = new team(rs.getInt("TeamId"),
												rs.getString("TeamName"),
												rs.getInt("idClub"),
												rs.getString("ClubName"),
												rs.getString("website")
												);
					TeamList.add(TeamDetails);
					}while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connObj.closeNavestockDbConnection(conn);
		return TeamList;
	}
	
	@ApiMethod(name = "club.teamsPerClub", httpMethod = ApiMethod.HttpMethod.GET)
	public ArrayList<team> TeamPerClub(@Named("clubId") Integer clubId){
		ArrayList<team> TeamList = new ArrayList<team>();
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();

		ResultSet rs;
		try {
			rs = conn
					.createStatement()
					.executeQuery("select teams.TeamId, teams.TeamName, Club.idClub, Club.ClubName, Club.website from teams left join Club on teams.idClub = Club.idClub where (teams.TeamId > 0 and Club.idClub = " + clubId + ") order by ClubName, TeamName");
			rs.first();
			if (rs != null) {
				do {
					team TeamDetails = new team(rs.getInt("TeamId"),
												rs.getString("TeamName"),
												rs.getInt("idClub"),
												rs.getString("ClubName"),
												rs.getString("website")
												);
					TeamList.add(TeamDetails);
					}while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connObj.closeNavestockDbConnection(conn);
		return TeamList;
	}	
	
}
