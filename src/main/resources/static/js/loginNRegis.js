/**
 * 
 */

var index=angular.module("app",["ngRoute"])

index.config(['$routeProvider',($routeProvider)=>{
	$routeProvider.when('/register',{
		//controller:'register',
		templateUrl:'/views/register.html'
		
	})
}])

