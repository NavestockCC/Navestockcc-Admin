/**
 * 
 */
var controller = angular.module('navestockcc-admin.controller.match.edit', []);

controller.controller('navestockcc-admin.controller.match.edit', ['$scope', 'GApi', '$state', '$stateParams',
    function homeCtl($scope, GApi, $state, $stateParams) {
    	GApi.executeAuth('navestockccapi', 'match.get', {'matchid': $stateParams.mid}).then(function(resp) {
            $scope.match = resp;
            $scope.winningteams=[{name:$scope.match.oppositionTeamName , idTeamWinning:$scope.match.oppositionTeamId}, {name:$scope.match.navestockTeamName, idTeamWinning:$scope.match.navestockTeamId}, {name:"Drawn", idTeamWinning:"-3"}, {name:"Abandoned", idTeamWinning:"-2"}, {name:"Still to be played", idTeamWinning:"-1"}];
            for (i = 0; i < $scope.winningteams.length; i++) {
                if($scope.match.idWinningTeam==$scope.winningteams[i].idTeamWinning){
                	$scope.resultTeam=$scope.winningteams[i];
                	break;
                }
            }
    	});
    	
    	$scope.submitEdit = function(resultInfo, matchInfo) {
    		$scope.match = matchInfo;
    		$scope.match.idWinningTeam = resultInfo.idTeamWinning;
    		GApi.executeAuth('navestockccapi', 'match.updateresult', $scope.match).then(function(resp) {
            	$state.go('matchlist');
        	});
    	}  	
    }
]);