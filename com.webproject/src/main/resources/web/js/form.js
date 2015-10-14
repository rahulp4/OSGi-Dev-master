function UserController($scope, $http)
{
  $scope.user = {};
 
  $scope.createUser = function() 
  {
	  alert('Creating User');
    $http({
      method: 'POST',
      url: 'http://localhost:8080/hello',
      headers: {'Content-Type': 'application/json'},
      data:  $scope.user
    }).success(function (data) 
      {
    	$scope.status=data;
      });
  };
}