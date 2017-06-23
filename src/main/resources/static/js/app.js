/**
 * Created by Amir Shams on 3/30/2017.
 */
angular.module("app", []);
angular.module("app").controller("main_controller",function($scope,$http) {


    $scope.editResponse = {
        type : null,
        message : null
    }

    init($http,$scope);

    $scope.rePass21={
        text:""
    }

    $scope.edit_profile = function () {
        $http({
            url: 'api/user/edit-profile',
            method: "POST",
            data: $scope.user,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.editResponse.type = "success";
            $scope.editResponse.message = response.data;
        },function (response) {
            $scope.editResponse.type = "error";
            $scope.editResponse.message = response.data;
        });

    }

    $scope.change_page = function (page_number) {

        if(page_number < 1 || page_number > $scope.page_count)
            return;

        $scope.current_page = page_number;
        $scope.data = [];
        $http.get($scope.context.data_url).then( function(response) {
            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
        });
    };

    $scope.passwordIsSame = function () {

        return $scope.user.newPassword == $scope.rePass21.text ||
            ($scope.user.newPassword == null || $scope.user.newPassword=="") && $scope.rePass21.text=="";
    }

    $scope.change_context = function (context_name) {
        $scope.editResponse = {
            type : null,
            message : null
        }

        $scope.context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = $scope.context.template_url;
        $scope.current_page = 1;
        $http.get($scope.context.data_url).then( function(response) {

            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
            $scope.page_count = Math.ceil(response.data.length / page_capacity);
        });
    };

    $scope.teams = function () {
        var context_name="teams";
        $scope.context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = $scope.context.template_url;
        $scope.current_page = 1;
        $http.get($scope.context.data_url).then(function(response) {
            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
            console.log($scope.data);
            for (var i = 0, len = $scope.data.length; i < len; i++) {
                var name = "";
                for(var j = 0, members_len = $scope.data[i].members.length; j < members_len; j++)
                    name += ($scope.data[i].members[j].username + "  ");
                $scope.data[i].member_names = name;
            }

            $scope.page_count = Math.ceil(response.data.length / page_capacity);
        });
    };

    $scope.profile = function() {
        $scope.data = [];
        $scope.context = dict['profile'];
        $scope.template = $scope.context.template_url;
    };
});
var page_capacity;
var dict;
function init($http,$scope) {

    $scope.page_count = 0;
    $scope.current_page= 0;
    page_capacity = 6;

    $scope

    $scope.pagination = "templates/pagination.html";

    $http({
        url: 'api/user/whoami',
        method: "GET",
        params: {extent: "full"}
    }).then(function (response) {
        $scope.user = response.data;
        $http({
            url: 'api/files/download/'+$scope.user.imageAddress,
            method: "GET",
            responseType: 'arraybuffer'

        })
            .then(function(response) {
                    $scope.profile_pic = _arrayBufferToBase64(response.data);;
                },
                function(response) {
                });
    });

    $http.get("js/context_dictionary.json").then(function (response) {
        dict = response.data;
        $scope.change_context('games');
    });

    function _arrayBufferToBase64(buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    }
}

