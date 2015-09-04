package com.navestockcc.matchreport;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiNamespace;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nacestockcc.dbcom.NavestockDbConnection;
import com.navestockcc.matchreport.MatchReport;
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


public class MatchReportService {
	
	//public static List<MatchReport> quotes = new ArrayList<MatchReport>();
	public static MatchReport ReportService = new MatchReport();

	
	@ApiMethod(name="matchReport.get", httpMethod = ApiMethod.HttpMethod.GET)
	public MatchReport getMatchReport(@Named("matchid") Integer matchid){

		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();
		
		String SQL = "select * from MatchReport where idMatch = " + matchid;
			
			try {
				
				ResultSet rs = conn.createStatement().executeQuery(SQL);
				if(rs !=null){
					rs.first();
					 Clob clob = rs.getClob("MatchReport");
					 if (clob != null) {  
					    String clobStr = clob.getSubString(1, (int) clob.length());
					    ReportService.setReport(clobStr);
					 }
					 else{
						 ReportService.setReport("Report not yet filed"); 
					 }
					
					ReportService.setReportId(rs.getInt("idMatchReport"));
					ReportService.setMatchId(rs.getInt("idMatch"));
					ReportService.setCreatedby("Test");
				
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		connObj.closeNavestockDbConnection(conn);
	
		return ReportService;
		
	}
	
	@ApiMethod(name="matchReport.add" , httpMethod = ApiMethod.HttpMethod.POST)
	public void addMatchReport(@Named("matchReport") String matchReport, @Named("idMatch") int idMatch){
		NavestockDbConnection connObj = new NavestockDbConnection();
		Connection conn = connObj.getNavestockDbConnection();
		
		try {
			CallableStatement callableStatement =conn.prepareCall("{call addMatchReport(?,?)}");
			callableStatement.setString("mtchReport", matchReport);
			callableStatement.setInt("idMtch", idMatch);
			callableStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		connObj.closeNavestockDbConnection(conn);
	}
}
