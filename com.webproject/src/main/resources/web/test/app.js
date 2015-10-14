var app = angular.module('app', []);

function MyController($scope, $http,$location) {

        $scope.getDataFromServer = function() {
        	alert("Alert and submit "+$scope.user.firstName);
                $http({
                        method : 'POST',
                        url : '/init?action=login',
                        headers : {'Content-Type': 'application/json'},
                        data : $scope.user
                        //data : 'firstName=' + $scope.user.firstName + '&lastName=' + $scope.user.lastName
//                        data : 'firstName=' + $scope.user.firstName + '&lastName=' + $scope.user.lastName,
//                        headers : {
//                            'Content-Type' : 'application/x-www-form-urlencoded'
//                        }
                    }).success(function(data, status, headers, config) {
                	alert('DOne');
                        $scope.user = data;
                        alert($scope.user.firtName);
                        $location.path('/hello');
                }).error(function(data, status, headers, config) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });

        };
};
