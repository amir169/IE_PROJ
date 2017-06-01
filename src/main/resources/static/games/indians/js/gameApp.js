/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("gameApp", []);
angular.module("gameApp").controller("game_controller",function($scope,$http) {

    $scope.submitComment = function () {
        var data = ""+$scope.qMessage;
        $http({
            url: '/api/question/submit',
            method: "POST",
            data: data
        }).then(function (){
            $scope.change_context("comment");
        }
        );
    };



    init($http,$scope);

    $scope.change_page = function (page_number) {

        if(page_number < 1 || page_number > $scope.page_count)
            return;

        $scope.current_page = page_number;
        $scope.data = [];
        $http.get($scope.context.data_url,{params:{page:$scope.current_page-1,sort:"submissionDate,desc"}}).then( function(response) {
            $scope.data = response.data.content;
        });
    };

    $scope.change_context = function (context_name) {
        $scope.context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = $scope.context.template_url;
        $scope.current_page = 1;
        $http.get($scope.context.data_url,{params:{page:$scope.current_page-1,sort:"submissionDate,desc"}}).then( function(response) {

            // console.log(response.data);
            if($scope.context.name == 'sch_ranking')       //a temporary spit. fix it later
                $scope.data = response.data;
            else {
                $scope.data = response.data.content;
                $scope.page_count = response.data.page.totalPages;
            }
        });

    };
    
    $scope.game_document = function () {
        $scope.context = dict['document'];
        $scope.template = $scope.context.template_url;
    };

});
var dict;
function init($http,$scope) {

    $scope.page_count = 0;
    $scope.current_page= 0;

    $scope.pagination = "../../templates/pagination.html";
    $http.get("js/context_dictionary.json").then(function (response) {
        dict = response.data;
        $scope.game_document();
    });
    $http.get("test_data/game_document.json").then( function(response) {
        $scope.game = response.data;
    });
}