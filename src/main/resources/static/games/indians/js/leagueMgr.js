/**
 * Created by SalehJFZ on 30/07/2017.
 */

angular.module("leagueMgr", []);

angular.module("leagueMgr").controller("league_controller",function($scope,$http,$window) {
    init($http,$scope);

    $scope.message = {
        text : null,
        type : null
    };

    $scope.changeLeagueName = function (index) {
        $http({
            url: '/api/leagues/change_name/'+list[index].id,
            method: "POST",
            data: list[index].newLeagueName
        }).
        then(function(response) {
                $window.location.href = "/";

                $scope.message.text="با موفقیت تغییر کرد";
                $scope.message.type="";
            },
            function(response) {
                if(response.status == 400)
                {
                    $scope.message.text=response.data;
                    $scope.message.type="error";
                }
            });
    };

    $scope.addNewTeamLeague= function(index){
        $http({
            url : '/api/leagues/add_team/'+list[index].id,
            method : 'POST',
            data : list[index].newTeamName
        }).
        then(function(response) {
                $window.location.href = "/";

                $scope.message.text="تیم با موفقیت اضافه شد";
                $scope.message.type="";
            },
            function(response) {
                if(response.status == 400)
                {
                    $scope.message.text=response.data;
                    $scope.message.type="error";
                }
            });
    };

    $scope.addLeague = function () {
        $http({
            url : '/api/leagues/add_league?'+
                "league="+$scope.newLeagueName+"&gid="+$scope.gameID,
            method : 'POST'
        }).
        then(function(response) {
                $window.location.href = "/games/indians/leaguesMgr.html";

                $scope.message.text="لیگ با موفقیت اضافه شد";
                $scope.message.type="";
            },
            function(response) {
                if(response.status == 400)
                {
                    $scope.message.text=response.data;
                    $scope.message.type="error";
                }
            });

    }


});

function init($http,$scope) {
    $scope.gameID = 15;
    $http.get( "/api/leagues/list/"+15).then(function (response) {
        $scope.list = response.data;
    },function(response) {
        if(response.status == 400)
        {
            $scope.message.text=response.data;
            $scope.message.type="error";
        }
    });
}