/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("team_modal",function($scope,$http) {

    $scope.sequence=['اول','دوم','سوم','چهارم','پنجم','ششم'];   //a temporary spit

    $scope.selected_team = $scope.data[0];
    $scope.teams = $scope.data;

    $scope.init_modal = function (index) {
        $scope.selected_team = $scope.teams[index];
    };

    $scope.create_team = function () {
        var new_team = {};
        new_team.manager = $scope.user;
        new_team.members_array = [];
        new_team.members_array[0] = $scope.user;

        $scope.selected_team = new_team;
    };

    $scope.team_register = function () {
        var dto = {
            name : $scope.selected_team.name,
            manager : $scope.selected_team.manager,
            members : []
        };

        for (var i = 0, len = $scope.selected_team.members_array.length; i < len; i++) {
            dto.members.push($scope.selected_team.members_array[i].email);
        }

        $http({
            url: 'api/team/register',
            method: "POST",
            data: dto,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    };

    $scope.add_new_member = function () {
        $scope.selected_team.members_array.push({});
    }

    $scope.check_email_exist = function (index) {
        if ($scope.selected_team.members_array[index].email!==""){
            $http.get("/api/user/exists",{params:{email:$scope.selected_team.members_array[index].email}})
                .then( function(response) {
                    if(response.status = 400){
                        $scope.selected_team.members_array[index].is_valid=false;
                        $scope.selected_team.members_array[index].name=response.data;
                    }
                    if(response.status = 200){
                        $scope.selected_team.members_array[index].is_valid = true;
                        $scope.selected_team.members_array[index].name = response.data.name;
                        $scope.selected_team.members_array[index].username = response.data.username;
                    }
            });

        }
    }
});