/**
 * Created by Amir Shams on 3/30/2017.
 */
angular.module("app", []);
angular.module("app").controller("main_controller",function($scope,$http) {

    $scope.game_profiles = [];

    $scope.editResponse = {
        type : null,
        message : null
    };

    init($http,$scope);

    $scope.rePass21={
        text:""
    };

    $scope.temp2 = null;

    $scope.getImage = function(index,adr){
        $http({
            url: 'api/files/download/'+$scope.user.imageAddress,
            method: "GET",
            responseType: 'arraybuffer'
        })
            .then(function(response) {
                    var temp = $scope.arrayBufferToBase64(response.data);
                    $scope.game_profiles[index].image=temp;
                    $scope.temp2=temp;
                    console.log("##"+$scope.game_profiles[index].image+":"+temp);
            },
                function(response) {
                });
    };
    
    $scope.getHTML = function (index,adr) {
        $http({
            url:adr,
            method: "GET"
        })
            .then(function(response) {
                    var temp = response.data;
                    $scope.game_profiles[index].html=temp;
                },
                function(response) {
                });

    };
    
    $scope.isRegistered = function (game) {
        //TODO
    };
    
    $scope.enterGame = function (game) {
        
    };

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

    };

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
    };

    $scope.getMembersName = function (record) {
        var ret = record.members[0].username;
        for (var i = 1, len = record.members.length; i < len; i++) {
           ret = ret +", "+record.members[i].username;
        }
        return ret;
    };

    $scope.getInvitedMembersName = function (record) {
        var ret ="";
        for (var i = 0, len = record.invitedMembers.length; i < len; i++) {
            if(i == len-1) {
                ret = ret+ record.invitedMembers[i].username;
            }
            else{
                ret = ret + record.invitedMembers[i].username+ ", "
            }
        }
        return ret;
    };

    $scope.change_context = function (context_name) {
        $scope.editResponse = {
            type : null,
            message : null
        };

        $scope.context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = $scope.context.template_url;
        $scope.current_page = 1;
        $http.get($scope.context.data_url).then( function(response) {

            $scope.data = response.data.content.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
            $scope.page_count = Math.ceil(response.data.content.length / page_capacity);

            if(context_name == 'games'){
                for(var i=0,len = response.data.content.length;i<len;i++){
                    $scope.game_profiles[i]={image:null,html:""};
                    $scope.getImage(i,response.data.content[i].logoAddress);
                    // $scope.getHTML(i,response.data.content[i].descriptionAddress);
                    console.log("#"+$scope.game_profiles[i].image);
                }
            }

        });
    };

    $scope.profile = function() {
        $scope.data = [];
        $scope.context = dict['profile'];
        $scope.template = $scope.context.template_url;
    };

    $scope.arrayBufferToBase64 = function(buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    }

});
var page_capacity;
var dict;
function init($http,$scope) {

    $scope.page_count = 0;
    $scope.current_page= 0;
    page_capacity = 6;

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

