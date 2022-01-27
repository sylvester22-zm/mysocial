

angular.module('home.service', [])
	.factory('socket', ['$rootScope', function($rootScope, $scope, socket) {
	
        
		var stompClient;
		var messages = [];
		var wrapper = {


			socketBinder(url) {
				stompClient = Stomp.over(new SockJS(url))
			},
			connect(successCall, errorCall) {
				stompClient.connect({}, function(frame) {

					$rootScope.$apply(function() {

						successCall(frame);
					});
				}, function(error) {
					$rootScope.$apply(function() {
						errorCall(error);
					});
				});
			},
			subscribeMessages(destination, callback) {
				stompClient.subscribe(destination, function(message) {

					messages.push(JSON.parse(message.body))

					$rootScope.$apply(function() {
						callback(message);
					})


				})
				// the object function am trying out
				return messages;

			},
			subscribeActiveNow(destination, callback) {
				stompClient.subscribe(destination, function(active) {

					JSON.parse(active.body)

					$rootScope.$apply(function() {
						callback(active);
					})


				})

			},

			//the function responsible for newsfeed
			newsFeed(destination, callback) {
				stompClient.subscribe(destination, function(newsfeed) {


					$rootScope.$broadcast('newsfeed', { payload: newsfeed }, function() {

					})
					$rootScope.$apply(function() {
						 callback(newsfeed);

					})


				})
			},
			send(destination, headers, object) {
				//console.log("service destination  " + destination + " headers " + headers + " object " + object)
				stompClient.send(destination, headers, object)
			},
			shareMessage: function(payload) {
				/*no need to push any messages to the server here**/
				//messages.push(payload);
				// console.log(payload,"in share function")			

				// messages.forEach((message)=>{
				// 	for(let i=0;i<message.length;i++)
				// 	console.log(message[i].username,"share message in service:: ,message[i].message")
				// })


				return messages;

			}

		}
		return wrapper;




	}])
