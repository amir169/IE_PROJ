/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("game_modal",function($scope,$http) {

    $http.get("test_data/team_data.json").then( function(response) {
        $scope.teams = response.data;
    });

    $scope.changedValue = function (s) {
        $scope.selected_team = s;
    };

    $scope.register_game = function () {
        console.log($scope.selected_team);
        console.log($scope.selected_game);
    };

});


