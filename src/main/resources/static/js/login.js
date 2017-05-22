/**
 * Created by Amir Shams on 5/20/2017.
 */
angular.module("login", []);
angular.module("login").controller("login_controller",function($scope,$http,$window) {

    $scope.credentials = {
        'username' : '',
        'password' : '',
        'email_address' : ''
    };

    $scope.register = function () {

        $http({
            url: 'api/user/register',
            method: "POST",
            data: $scope.credentials,
            headers: {
            'Content-Type': 'application/json'
            }
        })
            .then(function() {
                    // login($scope,$http,$window);
                },
                function() {
                    console.log("bye");
                });
    };

});

function login($scope,$http,$window) {

    var data = "username=".concat($scope.credentials.username,"&password=",$scope.credentials.password,"&submit=submit");
    console.log(data);
    $http({
        url: '/login.html',
        method: "POST",
        data: data,
        headers : {
            'Content-Type':'application/x-www-form-urlencoded'
        }
    })
        .then(function() {
            $window.location.href = "/";
        },
        function() {
            console.log("bye2");
        });
}


