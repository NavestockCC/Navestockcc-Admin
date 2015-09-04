/**
 * 
 */
var controller = angular.module('angular-google-api-example.controller.home', []);

controller.controller('angular-google-api-example.controller.home', ['$scope', 'GApi',
    function homeCtl($scope, GApi) {
        GApi.executeAuth('matchreportapi', 'getMatchReport', {'matchid' : '128'}).then(function(resp) {
        	console.log(resp);    
        	$scope.match = resp;
                console.log($scope.match.report);
            });

        $scope.remove = function(contact){
            GApi.executeAuth('matchreportapi', 'MatchReport', {'matchid' : '128'}).then( function(resp) {
                for(var i= 0; i < $scope.contacts.length; i++){
                    if($scope.contacts[i]['id'] == contact.id) {
                        if (i > -1) {
                            $scope.contacts.splice(i--, 1);
                        }
                    }
                }
            });
        };
    }
]);