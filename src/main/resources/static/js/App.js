var app = angular.module('storeApp', ['ngRoute','ngResource']);

app.config(function($routeProvider){
    $routeProvider
        .when('/products',{
            templateUrl: '/views/products.html',
            controller: 'productsController',
            controllerAs:'ctrl',
        })

        .otherwise(
            { redirectTo: '/'}
        );
});

