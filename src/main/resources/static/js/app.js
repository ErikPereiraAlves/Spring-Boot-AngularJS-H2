var app = angular.module('storeApp', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/products',{
            templateUrl: '/storeApp/views/products.html',
            controller: 'ProductController'
        })

        .otherwise(
            { redirectTo: '/'}
        );
});

