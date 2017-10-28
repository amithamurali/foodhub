
angular.module('app')  
.config(function($httpProvider) {
  $httpProvider.responseInterceptors.push('securityInterceptor');
})
.provider('securityInterceptor', function() {
  this.$get = function($location, $q) {
    return function(promise) {
      return promise.then(null, function(response) {
        if(response.status === 403 || response.status === 401) {
          $location.path('/unauthorized');
        }
        return $q.reject(response);
      });
    };
  };
});