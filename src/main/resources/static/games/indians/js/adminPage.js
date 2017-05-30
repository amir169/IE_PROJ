
angular.module("adminPage", []);
angular.module("adminPage").controller("admin_controller",function($scope,$http) {
    $scope.qMessage = [];
    $scope.getComment = function () {
        var len = ""+$scope.len;
        $scope.data = [];
        $http({
            url: '/api/question/list',
            method: "GET",
            data: len
        }).then(function (response){
        //    $scope.change_context("comment");
            $scope.data = response.data;
        }
        );
    };

    $scope.submitAnswer = function (qId) {
            console.log(qId);
            console.log($scope.qMessage[qId]);

            var obj = { "qId":qId, "message":$scope.qMessage[qId]};
            var myJSON = JSON.stringify(obj);
            var data = myJSON;
            console.log(data);
                    $http({
                        url: '/api/question/reply',
                        method: "POST",
                        data: data
                    }).then(function (response){
                        if (response.data =="ok")
                            console.log("ok");
                    }
                    );
        };

});