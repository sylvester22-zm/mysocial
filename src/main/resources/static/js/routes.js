angular.module('home.routes', ['ngRoute'])
	.config(['$routeProvider', '$locationProvider', ($routeProvider, $locationProvider) => {
		console.log("routes")
		// $locationProvider.hashPrefix('');
		//$locationProvider.html5Mode(true)
		$routeProvider
			.when('/newsfeed', {
				controller: 'feeds',
				templateUrl: '/views/newsfeed.html'
			})
			.when('/active', {
				controller: 'active-users',
				templateUrl: '/views/active.html'
			}).
			when('/chat/:sendingTo', {
				controller: 'ChatWith',
				templateUrl: '/views/chat.html'

			}).
			when('/messages', {
				templateUrl: '/views/messages.html',
				controller: 'chatMessages'

			}).


			when('/check', {
				controller: 'ChatWith',
				templateUrl: '/views/chat.html'
			}).
			when('/search/:username', {
				controller: 'searchfriend',
				templateUrl: '/views/search.html'

			}).

			when('/profile/:userprofileId', {
				controller: 'profile',
				templateUrl: '/views/profile.html'
			}).
			when('/createpost', {
				controller: 'newpost',
				templateUrl: '/views/newpost.html'
			}).
			when('/upload', {
				controller: 'uploadProfile',
				templateUrl: '/views/upload.html'
			}).
			when('/myprofile/:id', {
				controller: 'myprofile',
				templateUrl: '/views/myprofile.html'
			}).
		 when('/youmayknow', {
			controller: 'peopleyoumayknow',
            templateUrl: 'views/youmayknow.html'
		})

	}


	])