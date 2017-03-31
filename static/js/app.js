/**
 * Created by Amir Shams on 3/30/2017.
 */
angular.module("app", []);
function main_controller($scope,$http) {


    $http.get("test_data/user.json").then(function (response) {
        $scope.user = response.data;
    });

    $scope.current_content = "24635";

    $scope.page_numbering = "templates/page-numbering.html";
    $scope.records = function() {

        $scope.current_content = "records";

        $scope.template = "templates/scores.html";
        $scope.data =[];
        $scope.record = {};
        var url = "test_data/score_data.json";
        $http.get(url).then( function(response) {
            // console.log(response.data);
            $scope.data = response.data;
        });
        $scope.changeStyle();
    };

    $scope.games = function() {

        $scope.current_content = "games";

        $scope.template = "templates/game-itroducer.html";
        $scope.data =[];
        $scope.record = {};
        var url = "test_data/game_data.json";
        $http.get(url).then( function(response) {
            $scope.data = response.data;
            console.log(response.data);
        });
        $scope.changeStyle();
    };

    $scope.teams = function() {
        $scope.current_content = "teams";

        $scope.template = "templates/teams.html";
        $scope.data =[];
        $scope.record = {};
        var url = "test_data/team_data.json";
        $http.get(url).then( function(response) {
            $scope.data = response.data;
            // console.log(response.data);
        });
        $scope.changeStyle();
    };

    $scope.announcements = function() {
        $scope.current_content = "announcements";

        $scope.template = "";
        $scope.data =[];
        $scope.record = {};
        // var url = "test_data/announcement_data.json";
        // // $http.get(url).then( function(response) {
        // //     $scope.data = response.data;
        //     // console.log(response.data);
        // // });
        $scope.changeStyle();
    };

    $scope.changeStyle = function () {
        $("#" + $scope.current_content + "-selector-sidebar").addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
        $("#" + $scope.current_content + "-selector-nav").addClass("active").siblings().removeClass("active");
    }
}