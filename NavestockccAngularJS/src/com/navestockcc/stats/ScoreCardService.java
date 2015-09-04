package com.navestockcc.stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.nacestockcc.dbcom.NavestockDbConnection;
import com.navestockcc.matchreport.Constants;
import com.navestockcc.stats.ScoreCard;


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
public class ScoreCardService {

	
	public ArrayList<ScoreCard> ScoreCards = new ArrayList<ScoreCard>();
	
	@ApiMethod(name = "match.scorecard", httpMethod = ApiMethod.HttpMethod.GET)
	public List<ScoreCard> getScoreCard(@Named("matchid") Integer matchid){
		List<ScoreCard> ScoreCards = new ArrayList<ScoreCard>();
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();
		ResultSet rs;	
		try {
			rs = conn.createStatement().executeQuery(getScorecardSQL(matchid));
				while (rs.next()) {
					ScoreCard sc = new ScoreCard(rs.getInt("idClub"), rs.getString("ClubName"), rs.getInt("TeamId"),rs.getString("TeamName"), rs.getInt("idPlayer"), rs.getString("Firstname"), rs.getString("Lastname"), rs.getInt("BatingOrder"),rs.getInt("RunsScored"), rs.getInt("idHowOut"), rs.getString("HowOutDescription"), rs.getInt("OversBowled"), rs.getInt("Wickets"), rs.getInt("RunsConseded"), rs.getInt("idMatch"));
					ScoreCards.add(sc);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connObj.closeNavestockDbConnection(conn);
		return ScoreCards;
	}
	
	@ApiMethod(name = "match.insertPlayerInScorecard", httpMethod = ApiMethod.HttpMethod.POST)
	public void addPlayerToScorecard(@Named("idMtch") int idMtch, @Named("idPlyr") int idPlyr, @Named("idTm") int idTm){
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();	
		try {
			conn.createStatement().executeUpdate(addPlayertoScorecardSql(idMtch, idPlyr, idTm));
		    connObj.closeNavestockDbConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ApiMethod(name = "match.removePlayerFromScorecard", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removePlayerFromScorecard(@Named("idMtch") int idMtch, @Named("idPlyr") int idPlyr){ 
		try {
			NavestockDbConnection connObj = new NavestockDbConnection();
			Connection conn = connObj.getNavestockDbConnection();
			conn.createStatement().executeUpdate(removePlayerfromScorecardSql(idMtch, idPlyr));
			connObj.closeNavestockDbConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@ApiMethod(name = "match.updatePlayerScoreCard", httpMethod = ApiMethod.HttpMethod.PUT)
	public void updatePlayerScoreCard(ScoreCard S){
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();	
		try {
			conn.createStatement().executeUpdate(updatePlayerScorecardSql(S.getIdMatch(), S.getIdPlayer(), S.getRunsScored(), S.getIdHowOut(), S.getBatingOrder(), S.getOversBowled(), S.getWickets(), S.getRunsConseded()));	
			connObj.closeNavestockDbConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String getScorecardSQL(int idMtch){
		String BuildSql = null;
		
		BuildSql = "SELECT Club.idClub,";
		BuildSql = BuildSql + " Club.ClubName,";
		BuildSql = BuildSql + " teams.TeamId,";
		BuildSql = BuildSql + " teams.TeamName,";
		BuildSql = BuildSql + " Players.idPlayer,";
		BuildSql = BuildSql + " Players.Firstname,";
		BuildSql = BuildSql + " Players.Lastname,";
		BuildSql = BuildSql + " Stats.BatingOrder,";
		BuildSql = BuildSql + " Stats.RunsScored,";
		BuildSql = BuildSql + " HowOut.idHowOut,";
		BuildSql = BuildSql + " HowOut.HowOutDescription,";
		BuildSql = BuildSql + " Stats.OversBowled,";
		BuildSql = BuildSql + " Stats.Wickets,";
		BuildSql = BuildSql + " Stats.RunsConseded,";
		BuildSql = BuildSql + " Stats.idMatch";
		BuildSql = BuildSql + " FROM (HowOut INNER JOIN (((Stats INNER JOIN Matches ON Stats.idMatch = Matches.idMatch)"; 
		BuildSql = BuildSql + " INNER JOIN Players ON Stats.idPlayer = Players.idPlayer)"; 
		BuildSql = BuildSql + " INNER JOIN teams ON Stats.idTeam = teams.TeamId) ON HowOut.idHowOut = Stats.idHowOut)"; 
		BuildSql = BuildSql + " INNER JOIN Club ON teams.idClub = Club.idClub";
		BuildSql = BuildSql + " Where Stats.idMatch = " + idMtch;
		
		return BuildSql;
	}
	
	private String addPlayertoScorecardSql(int idMtch, int idPlyr, int idTm){
		String BuildSql = null;
		BuildSql = "insert into Stats (idMatch, idPlayer, idTeam) values (" + idMtch + ", " + idPlyr + " , " + idTm + ")";
		return BuildSql;
	}

	private String removePlayerfromScorecardSql(int idMtch, int idPlyr){
		String BuildSql = null;
		BuildSql = "delete from Stats where (idMatch = " + idMtch + " and idPlayer = " + idPlyr +");";
		return BuildSql;
	}

	private String updatePlayerScorecardSql(int idMtch, int idPlyr, int RnsScored, int idHwOut, int BttingOrder, int Ovrsbowled, int Wckets, int RnsConceded){
		String BuildSql = null;
		BuildSql = "update Stats set RunsScored =" + RnsScored + ", idHowOut=" + idHwOut + ", BatingOrder=" + BttingOrder + ", OversBowled=" + Ovrsbowled + ", Wickets=" + Wckets + ", RunsConseded=" + RnsConceded + " where (idMatch = " + idMtch + " and idPlayer=" + idPlyr + ")";
		return BuildSql;
	}
}
