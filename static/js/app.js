/**
 * Created by Amir Shams on 3/30/2017.
 */
angular.module("app", []);
function main_controller($scope,$http) {

    init($http,$scope);

    $scope.page_count = 0;
    $scope.current_page= 0;

    $scope.getNumberOfPages = function () {
        if($scope.page_count == null)
        {
            $scope.page_count
        }
        return new Array($scope.page_count);
    };

    $scope.change_page = function (page_number) {
        $scope.current_page = page_number;
        $scope.data = [];
        $http.get(context.data_url).then( function(response) {
            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
        });
    };
    
    $scope.change_context = function (context_name) {
        context = dict[context_name];
        $scope.data = [];
        $scope.record = {};
        $scope.template = context.template_url;
        $scope.current_page = 1;
        $http.get(context.data_url).then( function(response) {

            // console.log(response.data);
            $scope.data = response.data.slice(($scope.current_page - 1) * page_capacity, $scope.current_page * page_capacity);
            $scope.page_count = Math.ceil(response.data.length / page_capacity);
        });

        change_style(context.sidebar_selector_id,context.navbar_selector_id);
    };

    $scope.page_numbering = "templates/page-numbering.html";

}
var page_capacity = 6;
var dict;
var context;
function init($http,$scope) {
    $http.get("test_data/user.json").then(function (response) {
        $scope.user = response.data;
    });

    $http.get("js/context_dictionary.json").then(function (response) {
        dict = response.data;
    });
}
function change_style(sidebar,navbar) {
    $("#" + sidebar).addClass("sidebar-selected").siblings().removeClass("sidebar-selected");
    $("#" + navbar).addClass("active").siblings().removeClass("active");
}