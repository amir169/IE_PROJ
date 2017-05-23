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

    $scope.message = {
        text : null,
        type : null
    };

    $scope.register = function () {

        $scope.message.text="لطفا صبر کنید!";
        $scope.message.type="info";
        $http({
            url: 'api/user/register',
            method: "POST",
            data: $scope.credentials,
            headers: {
            'Content-Type': 'application/json'
            }
        })
            .then(function(response) {
                    $scope.message.text=response.data;
                    $scope.message.type="info";
                },
                function(response) {
                    $scope.message.text=response.data;
                    $scope.message.type="error";
                });
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
                    $scope.message.text=response.data;
                    $scope.message.type="error";
                    // console.log(response.data);
                });
    }
});

// function login($scope,$http,$window) {
//
//     var data = "username=".concat($scope.credentials.username,"&password=",$scope.credentials.password);
//     console.log(data);
//     $http({
//         url: '/api/user/login',
//         method: "POST",
//         data: data,
//         headers : {
//             'Content-Type':'application/x-www-form-urlencoded'
//         }
//     })
//         .then(function() {
//             $window.location.href = "/";
//         },
//         function(response) {
//             console.log(response.data);
//         });
// }


