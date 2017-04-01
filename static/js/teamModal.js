/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("team_modal",function($scope,$http) {

    $http.get("test_data/user.json").then(function (response) {
        $scope.user = response.data;
    });

    $http.get('test_data/team_data.json').then( function(response) {
        console.log(response.data[0]);
        $scope.selected_team = response.data[0];
        $scope.teams = response.data;
    });

    $scope.init_modal = function (index) {
        $scope.selected_team = $scope.teams[index];
    };

    $scope.create_team = function () {
        var new_team = new Object();
        new_team.manager = $scope.user;
        new_team.members_array = [];
        new_team.members_array[0] = $scope.user;

        $scope.selected_team = new_team;
    }
});