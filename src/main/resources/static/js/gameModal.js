/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("game_modal",function($scope,$http,$window) {

    $http.get("api/team/get-teams").then( function(response) {
        $scope.teams = response.data;
    });

    $scope.changedValue = function (s) {
        $scope.selected_team = s;
    };

    $scope.register_game = function () {
        var dto = {
            teamId: $scope.selected_team.name,
            gameId: $scope.selected_game.id
        };

        $http({
            url: 'api/teamgame/register',
            method: "POST",
            data: dto,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.is_registration_failed = true;
            $window.location.href = "/" + $scope.selected_game.sourcePath + "/index.html";
        },function (response) {
            if(response.status == 400) {
                $scope.message = response.data;
            }
            else{
                $scope.message = "ثبت نام با موفقیت انجام نشد."
            }
        });
    };

});


