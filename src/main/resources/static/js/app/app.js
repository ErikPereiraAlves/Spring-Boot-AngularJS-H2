var app = angular.module('store',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: '<a class="vglnk" href="http://localhost:8080/store" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>store</span></a>',
    PRODUCT_SERVICE_API : '<a class="vglnk" href="http://localhost:8080/store/api/v1/products/exclude/" rel="nofollow"><span>http</span><span>://</span><span>localhost</span><span>:</span><span>8080</span><span>/</span><span>store</span><span>/</span><span>api</span><span>/</span><span>v1</span><span>/</span><span>products</span>/</span><span>exclude</span><span>/</span></a>'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
        alert('will call products');
        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'products.html',
                controller:'ProductController',
                controllerAs:'ctrl',
                resolve: {
                    products: function ($q, ProductService) {
                        console.log('Load all products');
                        var deferred = $q.defer();
                        ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);