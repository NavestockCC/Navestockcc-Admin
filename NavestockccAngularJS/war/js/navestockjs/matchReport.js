

/**
'use strict';
 
 match report app 
 */
(function(){
var matchreportApp = angular.module('MatchreportApp', []);

matchreportApp.controller('MatchreportCtrl', function() {
		var match = this;
		match.reports = [];
		
	
	});

		this.getMatchReport = function() {
			gapi.client.matchreportapi.getMatchReport({'matchid':'128'}).execute(function(resp) {
				match.reports = resp.items;
	  });
	}

	
})();