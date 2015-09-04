package com.navestockcc.players;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.ServiceException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.nacestockcc.dbcom.NavestockDbConnection;
import com.navestockcc.matchreport.Constants;
import com.navestockcc.players.player;


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
public class playerservices {
	
	public ArrayList<player> PlayerList = new ArrayList<player>();
	
	@ApiMethod(name = "player.playerlist", httpMethod = ApiMethod.HttpMethod.GET)	
	public ArrayList<player> getPlayers()throws OAuthRequestException, 
    IOException, ServiceException, SQLException{
		ArrayList<player> PlayerList = new ArrayList<player>();
		String SQL = "Select Players.idPlayer, Players.Firstname, Players.Lastname, Players.Nickname, Players.emailaddress from Players order by Players.Lastname";	
			NavestockDbConnection connObj = new NavestockDbConnection();
			Connection conn = connObj.getNavestockDbConnection();
			ResultSet rs;
			
			try {
				rs = conn.createStatement().executeQuery(SQL);
					while (rs.next()) {
						player pl = new player(rs.getInt("idPlayer"), rs.getString("Firstname"), rs.getString("Lastname"), rs.getString("Nickname"), rs.getString("emailaddress"));
						PlayerList.add(pl);
					}			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connObj.closeNavestockDbConnection(conn);
		
		return PlayerList;	
	}
	@ApiMethod(name = "player.addPlayer", httpMethod = ApiMethod.HttpMethod.POST)
	public void addPlayer(player p)throws OAuthRequestException, 
    IOException, ServiceException, SQLException{
		String updateSql = null;
		String SqlCol = null;
		String SqlVal = null;
		
		if(p !=null){
			//Build the SQL Update command
			if(p.getFirstname() != null){ SqlCol="Firstname"; SqlVal="'" + p.getFirstname() + "'";};
			if(p.getLastName() != null){ if(SqlCol != null) {SqlCol= SqlCol + ", Lastname"; SqlVal= SqlVal + ", '" + p.getLastName() + "'";} else {SqlCol= "Lastname"; SqlVal= "'" + p.getLastName() + "'";}};			
			if(p.getNickName() != null){ if(SqlCol != null) {SqlCol= SqlCol + ", Nickname"; SqlVal= SqlVal + ", '" + p.getNickName() + "'";} else {SqlCol= "Nickname"; SqlVal= "'" + p.getNickName() + "'";}};
			if(p.getEmailaddress() != null){ if(SqlCol != null) {SqlCol= SqlCol + ", emailaddress"; SqlVal= SqlVal + ", '" + p.getEmailaddress() + "'";} else {SqlCol= "emailaddress"; SqlVal=  "'" + p.getEmailaddress() + "'";}};
			updateSql = "insert into Players (" + SqlCol + ") values (" + SqlVal + ")";
					
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
	}
	
	
	@ApiMethod(name = "player.removePlayer", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removePlayer(@Named("idPlyer") int idPlyer)throws OAuthRequestException, 
    IOException, ServiceException, SQLException{
		// TODO Add db connection to remove player
		//delete from Players where idPlayer = 31
		String updateSql = null;
		
		if(idPlyer > 0){
			//Build the SQL Update command
			updateSql = "delete from Players where idPlayer =" + idPlyer;
					
			//Create DB connection
			NavestockDbConnection connObj = new NavestockDbConnection();
			Connection conn = connObj.getNavestockDbConnection();

			//Execute query
			try {
				conn.createStatement().executeUpdate(updateSql);
				connObj.closeNavestockDbConnection(conn);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	@ApiMethod(name = "player.updatePlayer", httpMethod = ApiMethod.HttpMethod.PUT)
	public void editPlayer(@Named("idPlyer") int idPlyer, @Named("Frstname") String Frstname, @Named("LstName")String LstName,@Named("emailaddr") String emailaddr){
		// TODO Add db connection to edit player
	}
	

}
