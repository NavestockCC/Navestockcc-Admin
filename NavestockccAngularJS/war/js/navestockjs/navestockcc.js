/**
 * 
 */
var app = angular.module('navestockcc-admin', [
    'ui.router',
    'angular-google-gapi',
    'navestockcc-admin.router',
    'navestockcc-admin.controller',
]);

app.run(['GAuth', 'GApi', '$state', '$rootScope', '$window',
    function(GAuth, GApi, $state, $rootScope, $window) {
        var CLIENT = '763435628729-o1gpr621ras6vgeuk40dq87il8v9qrmi.apps.googleusercontent.com';
        var BASE;
        if($window.location.hostname == 'localhost') {
            BASE = '//localhost:8888/_ah/api';
        } else {
            BASE = 'https://navestockcc-002.appspot.com/_ah/api';
        }

        GApi.load('navestockccapi', 'v1', BASE);
        GAuth.setClient(CLIENT);
        GAuth.setScope('https://www.googleapis.com/auth/userinfo.email');
        GAuth.checkAuth().then(
            function () {
                if($state.includes('login'))
                    $state.go('matchlist');
            },
            function() {
                $state.go('login');
            }
        );

        $rootScope.logout = function() {
            GAuth.logout().then(
            function () {
                $state.go('login');
            });
        };
    }
]);