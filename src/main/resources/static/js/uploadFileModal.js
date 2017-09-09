 //
 //
 //
 // angular.module("app").directive("fileModel", ["$parse", function ($parse) {
 //            return {
 //               restrict: 'A',
 //               link: function(scope, element, attrs) {
 //                  var model = $parse(attrs.fileModel);
 //                  var modelSetter = model.assign;
 //
 //                  element.bind('change', function(){
 //                     scope.$apply(function(){
 //                        modelSetter(scope, element[0].files[0]);
 //                     });
 //                  });
 //               }
 //            };
 //         }]);
 //
 // angular.module("app").service('fileUpload', ['$http', function ($http ) {
 //                              this.uploadFileToUrl = function(file, uploadUrl , scope){
 //                                 var fd = new FormData();
 //                                 fd.append('file', file);
 //                                 $http.post(uploadUrl, fd, {
 //                                    transformRequest: angular.identity,
 //                                    headers: {'Content-Type': undefined},
 //                                    params: {
 //
 //                                            }
 //
 //                                 })
 //                                 .success(function(response){
 //                                    scope.user.imageAddress  = response.data;
 //                                    scope.notif = "فایل با موفقیت بارگذاری شد";
 //                                 })
 //                                 .error(function(){
 //                                 });
 //                              }
 //                           }]);
 //
 //  angular.module("app").controller('uploadCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
 //                               $scope.uploadFile = function(){
 //                                  var file = $scope.myFile;
 //                               //   console.dir(file);
 //                                  var uploadUrl = "api/files/upload-image";
 //                                  fileUpload.uploadFileToUrl(file, uploadUrl , $scope);
 //                               };
 //                            }]);
 //
