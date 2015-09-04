/**
 * 
 */
var controller = angular.module('angular-google-api-example.controller.add', []);

controller.controller('angular-google-api-example.controller.add', ['$scope', 'GApi', '$state',
    function homeCtl($scope, GApi, $state) {
    	$scope.submitAdd = function() {
    		GApi.executeAuth('matchreportapi', 'addMatchReport', $scope.MatchReport).then(function(resp) {
            	$state.go('home');
        	});
    	}
    }
]);