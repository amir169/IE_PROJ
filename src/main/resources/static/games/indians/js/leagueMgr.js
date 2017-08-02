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
    }


});

function init($http,$scope) {
    // $http.get( "/api/leagues/list/indians").then(function (response) {
    $http.get("test_data/leagues.json").then(function (response) {

        $scope.list = response.data;
    });
}
/*
 "/api/leagues/list/indians"
then(function() {
        $window.location.href = "/";
    },
    function(response) {
        if(response.status == 400)
        {
            $scope.message.text=response.data;
            $scope.message.type="error";
        }
    });*/
