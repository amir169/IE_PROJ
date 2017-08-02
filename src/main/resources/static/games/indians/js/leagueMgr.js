/**
 * Created by SalehJFZ on 30/07/2017.
 */

angular.module("leagueMgr", []);

angular.module("leagueMgr").controller("league_controller",function($scope,$http) {
    init($http,$scope);
});

function init($http,$scope) {
    $http.get("test_data/leagues.json").then(function (response) {
        $scope.list = response.data;
    });
}