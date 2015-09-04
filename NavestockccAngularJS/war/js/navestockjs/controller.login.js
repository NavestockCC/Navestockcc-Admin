/**
 * 
 */
var controller = angular.module('navestockcc-admin.controller.login', []);

controller.controller('navestockcc-admin.controller.login', ['$scope', 'GAuth', 'GData', '$state',
    function clientList($scope, GAuth, GData, $state) {
    	if(GData.isLogin()){
    		$state.go('matchlist');
    	}

        $scope.doLogin = function() {
            GAuth.login().then(function(){
            	$state.go('matchlist');
            });
        };
    }
])