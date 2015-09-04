package com.navestockcc.matchreport;

public class MatchReport {
	private Integer ReportId;
	private String Report;
	private Integer MatchId;
	private String Createdby;
	
	
	public MatchReport(){
		
	}
	
public MatchReport(Integer RprtId, String Reprt, Integer MtchId, String CrteId){
			this.ReportId = RprtId;
			this.Report = Reprt;
			this.MatchId = MtchId;
			this.Createdby = CrteId;
			
			
	}
	
	public int getReportId() {
		return ReportId;
	}
	public void setReportId(Integer reportId) {
		ReportId = reportId;
	}
	public String getReport() {
		return Report;
	}
	public void setReport(String report) {
		Report = report;
	}
	public int getMatchId() {
		return MatchId;
	}
	public void setMatchId(Integer matchId) {
		MatchId = matchId;
	}
	public String getCreatedby() {
		return Createdby;
	}
	public void setCreatedby(String createdby) {
		Createdby = createdby;
	}
	
	
	
	
}
