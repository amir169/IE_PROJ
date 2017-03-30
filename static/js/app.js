/**
 * Created by Amir Shams on 3/30/2017.
 */
angular.module("app", []);
function main_controller($scope,$http) {
    $scope.show=false;

    $scope.getNumberOfPages = function () {
        return new Array(5);
    };
    $scope.records = function() {
        $scope.data =[];
        $scope.record = {};
        $scope.show="record";
        var url = "test_data/score_data.json";
        $http.get(url).then( function(response) {
            // console.log(response.data);
            $scope.data = response.data;
        });
    };

    $scope.games = function() {
        $scope.data =[];
        $scope.record = {};
        $scope.show="game";
        var url = "test_data/game_data.json";
        $http.get(url).then( function(response) {
            $scope.data = response.data;
            // console.log(response.data);
        });
    };

    $scope.teams = function() {
        $scope.data =[];
        $scope.record = {};
        $scope.show="team";
        var url = "test_data/team_data.json";
        $http.get(url).then( function(response) {
            $scope.data = response.data;
            // console.log(response.data);
        });
    };

    $scope.announcements = function() {
        $scope.data =[];
        $scope.record = {};
        $scope.show="announcement";
        var url = "test_data/announcement_data.json";
        // $http.get(url).then( function(response) {
        //     $scope.data = response.data;
            // console.log(response.data);
        // });
    };

}