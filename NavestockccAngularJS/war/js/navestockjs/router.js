/**
 * 
 */
var router = angular.module('navestockcc-admin.router', []);

router
    .config(['$urlRouterProvider',
        function($urlRouterProvider) {

            $urlRouterProvider.otherwise("/login");

        }]);

router
    .config(['$stateProvider',
        function($stateProvider) {

            $stateProvider

                .state('login', {
                    url :'/login',
                    views :  {
                        '': {
                            templateUrl: 'partials/login.html',
                            controller: 'navestockcc-admin.controller.login',
                        },
                    },
                })
                .state('editmatch', {
                    url :'/editmatch/{mid}',
                    views :  {
                        '': {
                            controller: 'navestockcc-admin.controller.match.edit',
                            templateUrl: 'partials/editresult.html',
                        },
                    },
                })
            
                .state('matchlist', {
                    url :'/matchlist',
                    views :  {
                        '': {
                            templateUrl: 'partials/matchlist.html',
                        },
                    },
                })
                .state('scorecard', {
                    url :'/scorecard/{mid}',
                    views :  {
                        '': {
                            templateUrl: 'partials/scorecard.html',
                        },
                    },
                })
    }])