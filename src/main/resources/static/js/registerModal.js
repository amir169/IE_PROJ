/**
 * Created by Amir Shams on 5/24/2017.
 */
angular.module("login").controller("register_modal",function($scope,$http) {


    $scope.validation = {
        re_pass : 'valid',
        strong_pass : 'valid',
        email : 'valid'
    };

    $scope.credentials = {
        'username' : '',
        'password' : '',
        'email' : ''
    };

    $scope.re_password = '';

    $scope.register = function () {

        if($scope.validation.strong_pass == 'invalid'
            || $scope.validation.email == 'invalid'
            || $scope.validation.strong_pass == 'invalid'
            || $scope.credentials.email.length == 0
            || $scope.re_password == 0
            || $scope.credentials.password.length == 0)
        {
            $scope.message.text = "اطلاعات را اصلاح کنید و دوباره تلاش کنید."
            $scope.message.type="error";
            return;
        }
        $scope.message.text="لطفا صبر کنید...";
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
                    if(response.status == 400)
                    {
                        $scope.message.text=response.data;
                        $scope.message.type="error";
                    }

                });
    };

    $scope.username_changed = function () {


    };

    $scope.email_changed = function () {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if(re.test($scope.credentials.email)==false && $scope.credentials.email.length > 0)
            $scope.validation.email = 'invalid';
        else
            $scope.validation.email = 'valid';
    };

    $scope.password_changed = function () {
        if($scope.credentials.password != $scope.re_password && $scope.re_password.length > 0)
            $scope.validation.re_pass = 'invalid';
        else
            $scope.validation.re_pass = 'valid';

        if($scope.credentials.password.length < 8 && $scope.credentials.password.length > 0)
            $scope.validation.strong_pass = 'invalid';
        else
            $scope.validation.strong_pass = 'valid';
    };

    $scope.re_password_changed = function () {
        if($scope.credentials.password != $scope.re_password)
            $scope.validation.re_pass = 'invalid';
        else
            $scope.validation.re_pass = 'valid';
    };

});