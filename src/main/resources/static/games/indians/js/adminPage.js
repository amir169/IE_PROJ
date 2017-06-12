
angular.module("adminPage", []);
angular.module("adminPage").controller("admin_controller",function($scope,$http) {

    init($scope,$http);

    $scope.submitAnswer = function (id) {

        var dto = { "id":id, "answer":$scope.qMessage[id]};

        $http({
            url: '/api/question/reply',
            method: "POST",
            data: dto,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function () {
            alert("پاسخ با موفقیت ثبت شد.");
            init($scope,$http);
        })

    };

    $scope.deleteQuestion = function(id)
    {
        if (!confirm('آیا مطمئنید میخواهید این سوال را حذف کنید؟')) {
            return;
        }

        var url = "/items/questions/" + id;
        $http({
            url: url,
            method: "DELETE"

        }).then(function () {
            init($scope,$http);
        })
    };

    $scope.change_page = function (page_number) {

        if(page_number < 1 || page_number > $scope.page_count)
            return;

        $scope.current_page = page_number;
        $scope.data = [];
        $http.get("/items/questions",{params:{page:$scope.current_page-1,sort:"submissionDate,desc"}}).then( function(response) {
            $scope.data = response.data.content;
        });
    };


});

function init($scope,$http) {

    $scope.qMessage = [];
    $scope.page_count = 0;
    $scope.current_page= 0;

    $scope.pagination = "../../templates/pagination.html";

    $scope.data = [];
    $scope.current_page = 1;
    $http.get("/items/questions",{params:{page:$scope.current_page-1,sort:"submissionDate,desc"}}).then( function(response) {

        $scope.data = response.data.content;
        $scope.page_count = response.data.page.totalPages;

        response.data.content.forEach(function (item) {
            $scope.qMessage[item.id] = item.ans;
        })
    });


}