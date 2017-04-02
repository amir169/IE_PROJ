/**
 * Created by Amir Shams on 4/2/2017.
 */
angular.module("gameApp", []);
angular.module("gameApp").controller("game_controller",function($scope,$http) {

    init($http,$scope);

    $scope.change_page = function (page_number) {

        if(page_number < 1 || page_number > $scope.page_count)
            return;

        $scope.current_page = page_number;
        $scope.data = [];
        $http.get($scope.context.data_url).then( function(response) {
            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
        });
    };

    $scope.change_context = function (context_name) {
        $scope.context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = $scope.context.template_url;
        $scope.current_page = 1;
        $http.get($scope.context.data_url).then( function(response) {

            // console.log(response.data);
            if(context.name == 'sch_ranking')       //a temporary spit. fix it later
                $scope.data = response.data;
            else {
                $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
                $scope.page_count = Math.ceil(response.data.length / page_capacity);
            }
        });
    };
    
    $scope.game_document = function () {
        $scope.context = dict['document'];
        $scope.template = $scope.context.template_url;
    };
        


});
var page_capacity;
var dict;
function init($http,$scope) {

    $scope.page_count = 0;
    $scope.current_page= 0;
    page_capacity = 8;

    $scope.pagination = "../../templates/pagination.html";
    $http.get("js/context_dictionary.json").then(function (response) {
        dict = response.data;
        $scope.game_document();
    });
    $http.get("test_data/game_document.json").then( function(response) {
        $scope.game = response.data;
    });
}