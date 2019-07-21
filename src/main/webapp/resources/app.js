var app = angular.module('cinema', []);

app.controller('filmCtrl', function($scope, $http) {
    $http.get("films/most-popular").then(function (response) {
        $scope.films = response.data;
    });
})