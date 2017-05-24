/**
 * Created by Amir Shams on 5/20/2017.
 */
angular.module("login", []);
angular.module("login").controller("login_controller",function($scope,$http,$window) {

    $scope.message = {
        text : null,
        type : null
    };

    $scope.login = function () {
        $scope.message.text=null;
        var data = "username=".concat($scope.username,"&password=",$scope.password);
        $http({
            url: '/api/user/login',
            method: "POST",
            data: data,
            headers : {
                'Content-Type':'application/x-www-form-urlencoded'
            }
        })
            .then(function() {
                    $window.location.href = "/";
                },
                function(response) {
                    if(response.status == 400)
                    {
                        $scope.message.text=response.data;
                        $scope.message.type="error";
                    }
                });
    };

});

