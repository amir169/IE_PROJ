/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("app").controller("team_modal",function($scope,$http) {

    $scope.sequence = ['اول', 'دوم', 'سوم', 'چهارم', 'پنجم', 'ششم'];   //a temporary spit

    $scope.selected_team = $scope.data[0];
    $scope.teams = $scope.data;
    $scope.is_registration_failed = false;
    $scope.message = "";

    $scope.init_modal = function (index) {

        $scope.selected_team = $scope.data[0];
        $scope.teams = $scope.data;
        $scope.is_registration_failed = false;
        $scope.message = "";

        $scope.selected_team = $scope.teams[index];
    };

    $scope.create_team = function () {
        var new_team = {};
        new_team.manager = $scope.user;
        new_team.members = [];
        new_team.members[0] = $scope.user;

        $scope.selected_team = new_team;
        $scope.selected_team.members[0].is_valid = true;
    };

    $scope.team_register = function () {
        var dto = {
            name: $scope.selected_team.name,
            manager: $scope.selected_team.manager,
            emails: []
        };

        for (var i = 0, len = $scope.selected_team.members.length; i < len; i++) {
            dto.emails.push($scope.selected_team.members[i].email);
        }

        $http({
            url: 'api/team/register',
            method: "POST",
            data: dto,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
                $scope.is_registration_failed = false;
        },function (response) {
            $scope.is_registration_failed = true;
            if(response.status == 400) {
                $scope.message = response.data;
             }
            else{
                $scope.message = "ثبت تیم با موفقیت انجام نشد."
            }
        });
        $scope.change_context("teams");
    };

    $scope.add_new_member = function () {
        $scope.selected_team.members.push({});
    };

    $scope.check_email_exist = function (index) {
        if ($scope.selected_team.members[index].email !== "") {
            $http({
                url: 'api/user/exists',
                method: "GET",
                params: {email: $scope.selected_team.members[index].email}
            }).then(function (response) {
                    $scope.selected_team.members[index].is_valid = true;
                    $scope.selected_team.members[index].name = response.data.name;
                    $scope.selected_team.members[index].username = response.data.username;
                },
                function (response) {
                    if (response.status == 400) {
                        $scope.selected_team.members[index].is_valid = false;
                        $scope.selected_team.members[index].username = response.data;
                    }
                    else {
                        $scope.selected_team.members[index].is_valid = false;
                        $scope.selected_team.members[index].usernaname = "کاربری با این ایمیل وجود ندارد.";
                    }
                });
        }
    };

    $scope.validate = function () {

        if($scope.selected_team == null)
            return false;

        var valid = true;
        for (var i = 0, len = $scope.selected_team.members.length; i < len; i++) {
            valid = valid & $scope.selected_team.members[i].is_valid;
        }
        return valid;
    }

});