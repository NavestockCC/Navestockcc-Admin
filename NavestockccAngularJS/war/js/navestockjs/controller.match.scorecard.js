/**
 * controller.match.scorecard 
 */

var controller = angular.module('Navestockcc-Admin.Controller.Match.Scorecard', []);

controller.controller('Navestockcc-Admin.Controller.Match.Scorecard', ['$scope', 'GApi','$state', '$stateParams',
    function scorecardCtl($scope, GApi, $state, $stateParams) {
		$scope.selectedplayer=[];
		$scope.scorecardlist=[];
		$scope.listofplayers=[];
		$scope.newPlayertoAdd=[];
		$scope.match=[];
        GApi.executeAuth('navestockccapi', 'match.scorecard', {'matchid': $stateParams.mid}).then(function(resp) {   
        	$scope.scorecardlist = resp.items;
        	});
        GApi.executeAuth('navestockccapi', 'player.playerlist').then(function(resp) {
        		$scope.listofplayers = resp.items;
            });
        GApi.executeAuth('navestockccapi', 'match.get', {'matchid': $stateParams.mid}).then(function(resp) {
            	$scope.match = resp;
        	});
        $scope.howoutlist=[
        	{"idHowOut":"-2", "HowOutDescription":"Retired not out"},
        	{"idHowOut":"-1","HowOutDescription":"Did not bat"},
        	{"idHowOut":"0","HowOutDescription":"Not out"},
        	{"idHowOut":"1","HowOutDescription":"Bowled"},
        	{"idHowOut":"2","HowOutDescription":"Caught"},
        	{"idHowOut":"3","HowOutDescription":"LBW"},
        	{"idHowOut":"4","HowOutDescription":"Run out"},
        	{"idHowOut":"5","HowOutDescription":"Stumped"},
        	{"idHowOut":"6","HowOutDescription":"Retired"},
        	{"idHowOut":"7","HowOutDescription":"Hit the ball twice"},
        	{"idHowOut":"8","HowOutDescription":"Handled the ball"},
        	{"idHowOut":"9","HowOutDescription":"Hit wicket"},
        	{"idHowOut":"10","HowOutDescription":"Obstructing the field"},
        	{"idHowOut":"11","HowOutDescription":"Timed out"},
        	{"idHowOut":"12","HowOutDescription":"Retired out"}];
		

		$scope.submitAddPlayertoTeam = function(playertoAdd) {
			if(angular.isUndefined($scope.scorecardlist)){
				$scope.scorecardlist = [{"idPlayer":playertoAdd.idPlayer, "player":playertoAdd.name}];
				recindex = 0;
			}else{
				$scope.scorecardlist.push({"idPlayer":playertoAdd.idPlayer, "player":playertoAdd.name});
				var recindex = $scope.scorecardlist.length - 1;
			}
			GApi.executeAuth('navestockccapi', 'match.insertPlayerInScorecard', {"idMtch": $stateParams.mid, "idPlyr":playertoAdd.idPlayer, "idTm": "1"}).then(function(resp){
				if(!resp.code){$scope.scorecardlist[recindex].objServiceSaved = true};
	        	});
		}
		
		$scope.removePlayerScorecard=function(index, idPlayer){
			$scope.scorecardlist[index].objServiceSaved = false;
			GApi.executeAuth('navestockccapi', 'match.removePlayerFromScorecard', {"idMtch": $stateParams.mid, "idPlyr": idPlayer}).then(function(resp){
				$scope.scorecardlist[index].objServiceSaved = true;
				$scope.scorecardlist.splice(index, 1);
				});
			}
		
		$scope.updatePlayerScorecard=function(index){
			$scope.scorecardlist[index].objServiceSaved = false;
			GApi.executeAuth('navestockccapi', 'match.updatePlayerScoreCard', $scope.scorecardlist[index]).then(function(resp){
				$scope.scorecardlist[index].objServiceSaved = true;
				});
			}
		$scope.submitnewAddPlayertoDB = function(playerdata){
			GApi.executeAuth('navestockccapi', 'player.addPlayer', playerdata).then(function(resp){
				$scope.newPlayertoAdd=[];
				});
			GApi.executeAuth('navestockccapi', 'player.playerlist').then(function(resp) {
				$scope.listofplayers = resp.items;
				$scope.$apply;
	            });
		}
		}
	]);