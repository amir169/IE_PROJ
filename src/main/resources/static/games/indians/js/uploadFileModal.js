


 angular.module("gameApp").directive("fileModel", ["$parse", function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;

                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);

 angular.module("gameApp").service('fileUpload', ['$http', function ($http ) {
                              this.uploadFileToUrl = function(file, uploadUrl , scope){
                                 var fd = new FormData();
                                 fd.append('file', file);
                                 $http.post(uploadUrl, fd, {
                                    transformRequest: angular.identity,
                                    headers: {'Content-Type': undefined},
                                    params: {

                                            }

                                 })
                                 .success(function(response){
                                    scope.notif = "فایل با موفقیت بارگذاری شد";
                                    var data = {
                                       'code':response.key,
                                       'game':'1'
                                    } ;
                                    $http({
                                         url: '/api/code/upload',
                                         method: "POST",
                                         data: data
                                     }).then(function (){
                                             scope.change_context("upload_code");
                                         }
                                     );
                                 })
                                 .error(function(){
                                 });
                              }
                           }]);

  angular.module("gameApp").controller('uploadCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
                               $scope.uploadFile = function(){
                                  var file = $scope.myFile;
                               //   console.dir(file);
                                  var uploadUrl = "../../api/files/upload";
                                  fileUpload.uploadFileToUrl(file, uploadUrl , $scope);
                               };
                            }]);

