/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("game_modal",function($scope,$http) {

    $http.get("test_data/team_data.json").then( function(response) {
        $scope.teams = response.data;
    });

    $scope.changedValue = function (s) {
        console.log("hi");
        console.log(s);
    }

});


