var app = angular.module('cinema', []);

app.controller('filmCtrl', function($scope, $http) {
    $http.get("films/most-popular").then(function (response) {
        $scope.films = response.data;
    });
});

app.controller('hallCtrl', function($scope, $http) {
    $scope.hall = {};
    $http.get("halls").then(function (response) {
        $scope.halls = response.data;
    });

    $scope.save = function(hall) {
        $http.post("halls", hall).then(function(response) {
            alert(response.code);
        })
    }
});