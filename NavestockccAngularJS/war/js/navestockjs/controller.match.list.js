/**
 * 
 */
var controller = angular.module('Navestockcc-Admin.Controller.Match.List', []);

controller.controller('Navestockcc-Admin.Controller.Match.List', ['$scope', 'GApi',
    function homeCtl($scope, GApi) {
	var d = new Date();
	$scope.teamidNav = 1;
	$scope.yearNav= d.getFullYear();
	
        GApi.executeAuth('navestockccapi', 'match.all',{'teamId': $scope.teamidNav, 'matchYear': $scope.yearNav}).then(function(resp) { 
        	$scope.matchlists = resp.items;
            });

        $scope.getTeamMatchTeamIdList = function(teamId){
        	$scope.teamidNav = teamId;
        	GApi.executeAuth('navestockccapi', 'match.all',{'teamId': $scope.teamidNav, 'matchYear': $scope.yearNav}).then(function(resp) {
            	$scope.matchlists = resp.items;
            });
        };
        $scope.getTeamMatchDateList = function(matchYear){
        	$scope.yearNav = matchYear;
        	GApi.executeAuth('navestockccapi', 'match.all',{'teamId': $scope.teamidNav, 'matchYear': $scope.yearNav}).then(function(resp) {
            	$scope.matchlists = resp.items;
            });
        };
      
    }
]);