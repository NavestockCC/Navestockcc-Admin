/**
 * 
 */
var controller = angular.module('angular-google-api-example.controller.edit', []);

controller.controller('angular-google-api-example.controller.edit', ['$scope', 'GApi', '$state', '$stateParams',
    function homeCtl($scope, GApi, $state, $stateParams) {
    	GApi.executeAuth('matchreportapi', 'getMatchReport', {'matchid': $stateParams.mid}).then(function(resp) {
            $scope.match = resp;
        });
    	$scope.submitEdit = function() {
    		GApi.executeAuth('matchreportapi', 'getMatchReport', {'matchid' : $stateParams.mid}).then(function(resp) {
            	$state.go('home');
        	});
    	}
    }
]);