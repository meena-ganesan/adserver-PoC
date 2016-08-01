(function () {
    "use strict";

    angular.module('AdServerApp').service('httpSvc', httpSvc);

    httpSvc.$inject = ['$http', '$q', '$resource', '$state'];
    function httpSvc($http, $q, $resource, $state) {
        function getHttpResponse(methodType,url,errorMessage,postData) {
            var deferred = $q.defer();
            if(methodType === 'get') // check in a method instead
            {
                $http.get(url)
                    .success(function (data, status, headers, config) {
                        deferred.resolve(data);
                    })
                    .error(function (data, status, headers, config) {
                         deferred.reject(errorMessage);
                    });
            }
            else if(methodType == 'post'){
                $http({
                    url: url,
                    method: "POST",
                    data: angular.toJson(postData),
                    headers: { 'Content-Type': 'application/json' }
                }).success(function (data, status, headers, config) {
                    deferred.resolve(data);
                }).error(function (data, status, headers, config) {
                    deferred.reject(errorMessage);
                });
            }

            return deferred.promise;
        }
        return ({ getHttpResponse: getHttpResponse });
    }
}

    )
();