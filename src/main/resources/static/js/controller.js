
// var app=angular.module('home',[])
angular.module('home.controller', [])

	.controller('connect', ['$scope', '$location', 'socket', function($scope, $location, socket) {

		$scope.participants = [];
		$scope.sendTo = '';
		$scope.message = '';
		$scope.messages = [];
		$scope.messageInbox = [];
		$scope.post = [];
		console.log($scope.username, "hearders");
		/*
					 if ('serviceWorker' in navigator) {
	  window.addEventListener('load', function() {
		navigator.serviceWorker.register('http://localhost:8080/./sw.js').then(function(registration) {
		  // Registration was successful
	  var body="sent u a message";
	  var data="its your boy here"
		  console.log('ServiceWorker registration successful with scope: ', registration.scope);
		 
	 
		}, function(err) {
		  // registration failed :(
		  console.log('ServiceWorker registration failed: ', err);
		});
	  });
	}*/

		var initStompClient = function() {
			socket.socketBinder('/websocket');
			socket.connect(function(frame) {
				$scope.username = frame.headers['user-name'];
				var username = $scope.username;
				// if ('serviceWorker' in navigator) {

				socket.subscribeActiveNow("/app/active.participants", function(active) {
					$scope.participants = JSON.parse(active.body);
					console.log($scope.participants)
					let user = 'sent u a message';
					$scope.$on('notification', (evn, data) => {
						console.log(data.payload.body)
					})


				});

				/* function displayNotification() {
				
			  if (Notification.permission == 'granted') {
				navigator.serviceWorker.getRegistration().then(function(reg) {
				console.log(reg)
				//  reg.showNotification('Hello world!');
				});
			  }
			}*/

				// socket.subscribeLogin("/topic/chat.login", function (message) {
				//   $scope.participants.unshift(
				//     { username: JSON.parse(message.body).username, typing: false });
				// }, 3000);

				socket.subscribeMessages('/user/top/', function(message) {
					/*function displayNotification() {
						if (Notification.permission == 'granted') {
							navigator.serviceWorker.getRegistration().then(function(reg) {
								reg.showNotification('Hello world!');
							});
						}
					}*/
					var newMessage = '';
					$scope.inbox = 0;
					// $scope.messages.push(JSON.parse(message.body));

					$scope.messages = JSON.parse(message.body);

					if ($scope.messages) {
						$scope.inbox = $scope.inbox + 1;
						$scope.messageInbox.push($scope.inbox)

						console.log($scope.messageInbox.length, "ns");
						console.log($scope.inbox, "n");
						//  message in the inbox of a targeted user
						$('.inbox').append(`${$scope.messageInbox.length}`)
						newMessage = '<div class="text-left" style="width:100%">' +
							'<diV style="width:100%;background: #f1f0e8;">' +
							'<p class="pl-2" style="border-radius:25px;background-color:green;width:70%">' +
							$scope.messages.message + '<br>' +
							'<small>now</small>' +
							'</p>' +
							'</div>' +
							'</div>'
						$('.chat-box').append(newMessage)
						newMessage = '';

					} else {
						$scope.notifications.shift($scope.messageInbox)
					}

				})

				///the mapping responsible for newsfeed
				var postHolder = '';

				socket.newsFeed('/newsfeed/post.', function(newsfeed) {


				})

				//newsfeed for update profilepic

				socket.newsFeed('/newsfeed/profile.', function(newsfeed) {
					/*
										var post = JSON.parse(newsfeed.body)
										var img = new Image();
										img.src = post.photo;
										console.log(post);
										$('.post-body').append('<div class="box box-widget">' +
											'<div class="box-header with-border">' +
											'<div class="user-block">' +
											'<img class="userpost-img " src="' + img.src + '">' +
											'<span class="username" ><a href="#!/profile/' + post.id + '">' + `${post.firstname}` + ' ' + `${post.lastname}`, + '</a> updated his/her proifle</span>' +
											'<span class="posted-at">Shared at - 7:30 PM Today</span>' +
											'</div>' +
											'<div class="box-tools">' +
											'<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" >' +
											'<i class="fa fa-circle-o"></i>' +
											'</button>' +
											'<button type="button" class="btn btn-box-tool" data-widget="collapse">' +
											'<i class="fa fa-minus"></i>' +
											'</button>' +
											'<button type="button" class="btn btn-box-tool" data-widget="remove">' +
											'<i class="fa fa-times"></i>' +
											'</button>' +
											'</div>' +
											'</div>' +
					
											'<div class="box-body " style="display: block;">' +
											'<div class="row no-gutters ">' +
											'<div class="col-12 postimage">' +
											'<div class="post-message-area">' +
											'<span post-message>' + post.payload + '</span>' +
					
											'</div>' +
											'<img class="post-img" src="' + img.src + '"/>' +
											'</div>' +
											'</div>' +
					
											'<div class="row text-center">' +
											'<div class="col-12 post-tools">' +
											'<i class="fas fa-heart fa-2x"/>' +
											'<i class="fa fa-thumbs-up "/>' +
											'<i class="fas fa-comment "></i>' +
											'<i class="fa fa-share  "/>' +
											'</div>' +
					
											'</div>' +
					
											'</div>' +
											'</div>' +
										'<hr style="height: 2px;width: 100%;color:grey">')
					*/


					//var newsFeed='<span class="username">'+`${$scope.post.sender}`+'<span>';	


				})
				socket.shareMessage(function(payload) { });
			})
		}

		initStompClient()
		$location.path('/newsfeed')


	}])




angular.module('home.controller')
	.controller('ChatWith', ['$scope', '$routeParams', 'socket', '$window',
		function($scope, $routeParams, socket, $window) {
			var message = $scope.message;
			$scope.sendTo = $routeParams.sendingTo;
			const xhr = new XMLHttpRequest();
			var home = $('.common-header').hide()
			$('.form-inline').hide();
			$('.searcharea').hide();
			$('#searchfriend').hide();
			$('#sidebar').hide()
			var otherProfile = new Image();

			angular.element(home).$scope;

			const url = 'https://wesocialites.herokuapp.com/conversation/' + $scope.sendTo;
			var rowClass = '';
			xhr.open('POST', url, true);
			xhr.send();
			xhr.onload = function() {
				
				var messages = '';
				var otherUser = '';
				var messageHolder = '';

				if (xhr.status === 200) {
					var mymessage = JSON.parse(xhr.response);
					if(mymessage.length!==0){
					mymessage.forEach(message => {
						//otherProfile.src=message.profilepic;


						console.log(message.isSender)

						if (message.isSender) {
							rowClass = rowClass + '<div class="text-right " style="border-radius:25px;width:100%">' +
							'<small>' + message.localDatetime + '</small>' +
								'<div class="pl-2"  style="background:#f1f0e8;border-radius:25px;width:100%;">' +
								'<p class="pr-2 message" style="border-radius:25px;background-color: rgb(192, 192, 192);;width:100%">' +
								message.message + '<br>' +
								
								'</p>' +
								'</div>' +
								'</div>'

							//username=message.senderId;

						}//background='sent-message'
						else {

							rowClass = rowClass + '<div class="text-left" style="width:100%">' +
								'<diV style="width:100%;background: #f1f0e8;">' +
								'<small>' + message.localDatetime + '</small>' +
								'<p class="pl-2 message" style="border-radius:25px;background-color:green;width:70%">' +
								message.message + '<br>' +
								'</p>' +
								'</div>' +
								'</div>'
							username = message.receiverId;
							background = ' received-message'
						}
					})
					autosize();
				}else{
	console.log("You have no!1")
	           $('.chat-box').append("Send Your Fisrt Message to This Person");
	    
}
				$('.chat-box').append(rowClass);
         }
			}
			var chatbox = document.querySelector('.chat-box')
			console.log("scrol called")
			var message = document.querySelector('.message')
			///the function responsible for controlling the chat-area

			function autosize() {
				var chatbox = document.querySelector('.chat-box')



				// message.scrollTop = message.scrollHeight;

				//window.scrollTo=message.scrollHeight
				window.scrollTo(0, chatbox.scrollHeight || chatbox.scrollHeight);
				var scrollInterval = setInterval(function() {
					chatbox.scrollTop = chatbox.scrollHeight;
				}, 3000);

			}

			var sentMessage = ''
			$scope.sendMessage = () => {
				//scroll();
				// message();
				autosize();
				socket.send('/app/chat.private.' + $scope.sendTo, {},
					JSON.stringify(
						{
							message: $scope.message,
							toUser: $scope.sendTo
						}
					))

				sentMessage = '<div class="text-right " style="padding-bottom:5px;border-radius:25px;width:100%">' +
					'<div class="pl-5"  style="background:#f1f0e8 ;border-radius:25px;width:100%;">' +
					'<p class="pr-2 message" style="border-radius:25px;background-color:rgb(192, 192, 192);width:100%">' +
					$scope.message + '<br>' +
					'<small>sent now</small>' +
					'</p>' +
					'</div>' +
					'</div>';

				$('.chat-box').append(sentMessage)
				$scope.message = "";
				sentMessage = "";
			}

			$scope.$window = $window;
			$scope.myChat = () => {
				$('.searcharea').show();
				$('#searchfriend').show();
				$window.history.back()


			}

		}])

/*serach friend fuctionality*/

angular.module('home.controller')
	.controller('searchfriend', ['$scope', 'socket', '$routeParams', function($scope, socket, $routeParams) {
		var xhr = new XMLHttpRequest();
		const url = 'https://wesocialites.herokuapp.com/search/';
		$scope.username = $routeParams.username;
		$('.common-header ').hide();
		$('#searchBack').css('display', 'block');
		$('#drawer').hide();
		console.log($scope.username)
		var seearchResult = '';
		xhr.open("POST", url + $scope.username, true)
		xhr.send();
		xhr.onload = () => {
			var searchList = JSON.parse(xhr.response);
			console.log(searchList)
			var image = new Image(200, 200);

			image.src = '';
			searchList.forEach(user => {
				image.src = user.profilepic;
               user.firstname= user.firstname.charAt(0).toUpperCase() + user.firstname.slice(1)
                 user.firstname.charAt(0).toUpperCase() + user.firstname.slice(1)
				seearchResult = seearchResult + '<a class="searchAtag"  href="#!/profile/' + user.id + '">' +
					/*'<li class="searchedUserList">' +*/
					'<div class="row">'+
					'<div class="col-4">'+
					'<img class="search-image ml-3" src="' + image.src + '"><img>' +
					'</div>'+
					'<span class="col-4 mt-8 ml-2 mt-3 serchnames">' + user.firstname +" "+user.lastname+ '</br>'+
					'<span class="search-username ">@'+user.username+'<span>'+
					'</span>' +
					'</div>'+
					'<a>' 
					'<hr style="width:100%;height:0.5px;background: grey">';

				//document.write("<img src='"+image.src+"'><img>")
				/*console.log(image.src)
				$('.search-display').append(image.src)*/
			})
           

			$('.search-display').append(seearchResult)

		}

		$scope.searchBack = () => {
			$scope.username ="";
			$('#sidebar').show();
			console.log("search back ")
		}
		//$location.path('/userprofile/'+$location.search);


	}])
///my profile logic comes here

angular.module('home.controller')
	.controller('myprofile', ['$scope', '$location','$window', function($scope, $location,$window) {
		$('.common-header ').hide();
		$('.searcharea').hide();
		$('#searchfriend').hide();
		$('#drawer').hide();
		var editProfile=new XMLHttpRequest()
		var xhr = new XMLHttpRequest()
		url='https://wesocialites.herokuapp.com/'
		var image = new Image()
		 var profile='';
		//where the user wil choose his/her profile
		//xhr.open('POST',)
		xhr.open("POST", url+'myprofile', true)
		xhr.send();
		xhr.onload = () => {
			 profile = JSON.parse(xhr.responseText);
			image.src = profile.profilepic;
			console.log( profile, 'userProfile')
			$('.profile').prepend( '<img src="'+image.src+'" alt="picture" class="card-img-top myprofile-img">' +
         '<span class="text-center">'+ profile.firstname +" "+ profile.lastname+'</span>'+
     '<span class="text-muted text-center">@'+ profile.username+'</span>'+
        '<span class="text-center">'+[profile.bio].join('')+'<span>'+'<br>'+
           '<span class="following">'+
      '<span class="count text-center"> followers 170k following 24</span>')
        
			
		}
		
		
		///save profile edit function
		$scope.saveEdit=()=>{
			var fn=$('#firstname').val();
			var ln=$('#lastname').val();
			var status=$('#status').val();
			var bio=$('#bio').val();
			var profile={
				firstname:fn,
				lastname:ln,
				status:status,
				bio:bio
			}
			console.log(profile)
			xhr.open('POST',url+'editProfile')
			xhr.setRequestHeader('Content-type', 'application/json')
			
			xhr.send(JSON.stringify(profile))
			xhr.onload=()=>{
				if(xhr.status===200){
				console.log(xhr.response)
				var alert=xhr.response;
				$('.alert').append(alert)
				}
			}
			
			
		     	$('#firstname').val('');
			$('#lastname').val('')
			$('#bio').val('');
			$('#status').val('');
		     
			
		}
		//the function taking us to upload.html
		
	
	  $scope.uploadProfile= ()=> {

			$location.path('/upload')

		}
		
		$scope.myprofileBack=()=>{
			$('#searchfriend').show();
			$('#drawer').show();
			$window.history.back();
		}
     /*for update profile dialog box*/
  // Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
	$("#firstname").val($("#firstname").val() + profile.firstname);
	$("#lastname").val($("#lastname").val() + profile.lastname);
	$("#relationship").val($("#relationship").val() + profile.relationship);
	$("#bio").val($("#bio").val() + [profile.bio].join(''));
	console.log(profile);
	
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
	}])


angular.module('home.controller')
	.controller('active-users', ['$scope', 'socket', function($scope, socket) {

		$scope.participants = socket.shareMessage(function(payload) { })
		console.log($scope.participants)


	}])


angular.module('home.controller')
	.controller('uploadProfile', ['$scope', 'socket', '$location','$window', function($scope, socket, $location,$window) {
		console.log("in upload profile")

		//	$location.path("/newsfeed")


		$(document).ready(function() {
			      	const MAX_WIDTH = 400;
                    const MAX_HEIGHT = 300;
                 const MIME_TYPE = "image/jpeg,image/jpeg";
                const QUALITY = 0.8;

			// Prepare the preview for profile picture
			$("#wizard-picture").change(function(ev) {
                    
				var data = readURL(this);
				//for(const i=0;i<data.length;i++){
					console.log(data)
				//}
				    const file = ev.target.files[0]; // get the file
                    console.log(file.size)
                     const blobURL = URL.createObjectURL(file);
                  const img = new Image();
            img.src = blobURL;
               img.onerror = function () {
         URL.revokeObjectURL(this.src);
    // Handle the failure properly
    console.log("Cannot load image");
           };
              img.onload = function () {
    //URL.revokeObjectURL(this.src);
    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
              console.log('width=',newWidth ,'newHeight=',newHeight)
            
       
       
    const canvas = document.createElement("canvas");
    canvas.width = newWidth;
    canvas.height = newHeight;
    const ctx = canvas.getContext("2d");
   ctx.drawImage(img, 0, 0, newWidth, newHeight);
    canvas.toBlob(
      (blob) => {
        // Handle the compressed image. es. upload or save in local state
        displayInfo('Original file', file);
        displayInfo('Compressed file', blob);
          $scope.uploadProfile = () => {
	                var payload =  $('#payload').val();
                 console.log(payload)
	       var pic = document.getElementById('wizard-picture').files[0],
				 normal = new FileReader();
			console.log("normal file=",file.size,"compressed=",blob.size)
			
			       if(file.size/1024>1024){
				
					var reader = new FileReader();
				
				reader.onloadend = function(e) {
					var data=e.target.result;
					
					var json = JSON.stringify({

						payload: payload,

						photo: data

					});
	              console.log(data,"over IMB")
					//post of a photo upload request
					
					socket.send('/app/post.update.profile.', {},json)
					}
					 reader.readAsDataURL(blob)
					}else{
						
						console.log("its a normal image")
				
				normal.onloadend = function(e) {
					var data=e.target.result;
					var payload = $('#payload').val();
					var json = JSON.stringify({
						payload: payload,
						photo: data
					});
					console.log(data,"normal picture")
					socket.send('/app/post.update.profile.', {},json)
					}
				}
                  
				normal.readAsDataURL(pic)

				$location.path('/newsfeed')
			}
        
      },
      MIME_TYPE,
      QUALITY
    );
   // document.getElementById("img-data").append(canvas);
  };
 		});
			
			
		});
		
     
		        function readURL(input) {
              console.log(input.files[0].size)

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onloadend = function(e) {
					$('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');

					var data = e.target.result;
					var message = "hay there";
				}

				reader.readAsDataURL(input.files[0]);
			}
			
		}
		$scope.myprofileBack=()=>{
			$window.history.back();
		}
		
	

function calculateSize(img, maxWidth, maxHeight) {
  let width = img.width;
  let height = img.height;

  // calculate the width and height, constraining the proportions
  if (width > height) {
    if (width > maxWidth) {
      height = Math.round((height * maxWidth) / width);
      width = maxWidth;
    }
  } else {
    if (height > maxHeight) {
      width = Math.round((width * maxHeight) / height);
      height = maxHeight;
    }
  }
  return [width, height];
}

// Utility functions for demo purpose

function displayInfo(label, file) {
	
  const p = document.createElement('p');
  p.innerText = `${label} - ${readableBytes(file.size)}`;
           console.log(p)
  //document.getElementById('img-data').append(p);
}

           function readableBytes(bytes) {
        const i = Math.floor(Math.log(bytes) / Math.log(1024)),
    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

         return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
      }
           /* end*/
	}])

angular.module('home.controller')
	.controller('feeds', ['$scope', '$location', 'socket', function($scope, $location, socket) {
		var xhr = new XMLHttpRequest();
		var xhrpost = new XMLHttpRequest()
		const url = 'https://wesocialites.herokuapp.com'
		$('.common-header ').show();
		$('.searcharea').show();
		$('#searchfriend').show();
		$('#drawer').show();
		var image = new Image();

		//console.log(image.src)


		$scope.$on('newsfeed', (event, data) => {
			/*
				this is the xhr request object getting data from the 
				database and rendering it into the UI
	 */
			//var post = JSON.parse(newsfeed.body)
			console.log(data.payload.body, 'data post from user');
			var post = JSON.parse(data.payload.body);
			var img = new Image();
			img.src = post.photo;
			post.firstname =post.firstname.charAt(0).toUpperCase() + post.firstname.slice(1)
			post.firstname =post.firstname.charAt(0).toUpperCase() + post.firstname.slice(1)

			console.log(post);
			$('.post-body').prepend('<div class="box box-widget">' +
				'<div class="box-header with-border">' +
				'<div class="user-block">' +
				'<a href="#!/profile/' + post.profileId + '"> ' +
				'<img class="userpost-img " src="' + img.src + '">' +
				'</a>' +
				'<span class="username" ><a href="#!/profile/' + post.profileId + '">' + `${post.firstname}` + ' ' + `${post.lastname}` + '</a></span>' +
				'<span class="posted-at">'+ post.dateTime + ' </span>' +
				'</div>' +
				'<div class="box-tools">' +
				'<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" >' +
				'<i class="fa fa-circle-o"></i>' +
				'</button>' +
				'<button type="button" class="btn btn-box-tool" data-widget="collapse">' +
				'<i class="fa fa-minus"></i>' +
				'</button>' +
				'<button type="button" class="btn btn-box-tool" data-widget="remove">' +
				'<i class="fa fa-times"></i>' +
				'</button>' +
				'</div>' +
				'</div>' +

				'<div class="box-body " style="display: block;">' +
				'<div class="row no-gutters ">' +
				'<div class="col-12 postimage">' +
				'<div class="post-message-area">' +
				'<p post-message>' + [post.payload].join() + '</p>' +

				'</div>' +
				'<img class="post-img" src="' + img.src + '" alt=" "/>' +
				'</div>' +
				'</div>' +

				/*'<div class="row text-center">' +
				'<div class="col-12 post-tools">' +
				'<i class="fas fa-heart fa-2x"/>' +
				'<i class="fa fa-thumbs-up "/>' +
				'<i class="fas fa-comment "></i>' +
				'<i class="fa fa-share  "/>' +
				'</div>' +*/

				'</div>' +
				 	'</div>' +
				'</div>')

		})
      

		//xhrr api to get users profile details
		xhr.open("POST", url + '/myprofile', true)
		xhr.send();
		xhr.onload = () => {
			var myprofile = JSON.parse(xhr.responseText);
			image.src = myprofile.profilepic;
			console.log(myprofile, 'userProfile')
			$('.post-something').prepend('<a href="#!/myprofile/' + myprofile.id + '">' +
				'<img class="myprofile-img " src="' + image.src + '"/></a>')
				
		}
          
		//xhr object to grab all posts from the database
     //  if(xhr.DONE){
		xhrpost.open("POST", url + '/posts', true)
		xhrpost.send()
		xhrpost.onload = () => {
			var postImg = new Image();
			var profileimg=new Image();
			var posts = JSON.parse(xhrpost.responseText)
			
            console.log(posts)
			posts.forEach(post => {
				postImg.src = post.postimg;
				profileimg.src=post.profilePic;
				console.log(postImg.clientHeight)
			console.log(postImg.clientWidth)
				var fn= post.firstname.charAt(0).toUpperCase()+post.firstname.slice(1);
				var ln=post.lastname.charAt(0).toUpperCase()+post.lastname.slice(1);
				console.log(fn)
				console.log(post)
				
				
				$('.post-body').append('<div class="box box-widget">' +
					'<div class="box-header with-border">' +
					'<div class="user-block">' +
					'<a href="#!/profile/' + post.profileid + '"> <img class="userpost-img " src="' +profileimg.src + '"></a>' +				
					'<span class="username" ><a href="#!/profile/' + post.profileid + '">' + fn+ ' ' + ln + '</a></span>' +
					'<span class="posted-at">'+ post.localDatetime + ' </span>' +
					'</div>' +
					'<div class="box-tools">' +
					'<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" >' +
					'<i class="fa fa-circle-o"></i>' +
					'</button>' +
					'<button type="button" class="btn btn-box-tool" data-widget="collapse">' +
					'<i class="fa fa-minus"></i>' +
					'</button>' +
					'<button type="button" class="btn btn-box-tool" data-widget="remove">' +
					'<i class="fa fa-times"></i>' +
					'</button>' +
					'</div>' +
					'</div>' +

					'<div class="box-body " style="display: block;">' +
					'<div class="row no-gutters ">' +
					'<div class="col-12 postimage">' +
					'<div class="post-message-area">' +
					'<span post-message>' + post.posttext + '</span>' +
                    '</div>' +
				    	'<img class="post-img" src="'+postImg.src+'"   alt="  "/>' +
					'</div>' +
					'</div>' +
					  
/*
					'<div class="row text-center">' +
					'<div class="col-12 post-tools">' +
					'<i class="fas fa-heart fa-2x"/>' +
					'<i class="fa fa-thumbs-up "/>' +
					'<i class="fas fa-comment "></i>' +
					'<i class="fa fa-share  "/>' +
					'</div>' +

					'</div>' +*/

					'</div>' +
					'</div>')
					var img=document.querySelector("img")
					var h=img.clientHeight;
					var w=img.clientWidth;
					console.log(h,w)
					/*from here*/
					
			})
		}
		
		//}
		$scope.createPost = () => {
			$location.path('/createpost')

		}
		
        $('#searchBack').css('display','none')
	}])

/*
angular.module('home.controller')
	.controller('newpost', ['$scope', '$window', '$location', '$http', 'socket', function($scope, $window, $location, $http, socket) {
		$('.common-header ').hide();
		$('.searcharea').hide();
		console.log(socket)
		//the button which hundles post payload
		var xhr = new XMLHttpRequest();
		const url = "http://localhost:8080/upload"

		$scope.payload = '';
		//$scope.photo = '';
		
		 
		
		 /*socket.send('/app/post.public.', {},
			JSON.stringify({
			  payload:$scope.payload,
			  file:$scope.image
			}))*/


/*$scope.post = () => {
	//$scope.post = post;
var payload=$scope.payload;
var sender=	$scope.sender;
	   console.log(payload,sender )
	var pic = document.getElementById('pic').files[0],
		read = new FileReader();
	read.onloadend = function(e) {
		var data = e.target.result;
		console.log(data)

		var json = JSON.stringify({

			payload: payload,
			sender: sender,
			photo: data

		});
		console.log($scope.post)
		$http({
			method: 'POST',
			url: url,
			enctype: 'multipart/form-data',
			data: json,
			processData: false,
			contentType: false,
			cache: false,
		})
	}

	read.readAsDataURL(pic);
	

	//post success redirect
	//$location.path('/newsfeed')

}


$scope.createPostBack = () => {
	window.history.back();
}


}])
*/
//the folllowing is the implementation for a websocket

angular.module('home.controller')
	.controller('newpost', ['$scope', '$window', '$location', '$http', 'socket', function($scope, $window, $location, $http, socket) {
		$('.common-header ').hide();
		$('.searcharea').hide();
		$('#searchfriend').hide();
		$('#drawer').hide();
			$(document).ready(function() {
				 
			      	const MAX_WIDTH = 400;
                    const MAX_HEIGHT = 300;
                 const MIME_TYPE = "image/jpeg,image/jpeg";
                const QUALITY = 0.9;

			// Prepare the preview for profile picture
			$("#pic").change(function(ev) {
                    
				var data = readURL(this);
				//for(const i=0;i<data.length;i++){
					console.log(data)
				//}
				    const file = ev.target.files[0]; // get the file
                    console.log(file.size)
                     const blobURL = URL.createObjectURL(file);
                  const img = new Image();
            img.src = blobURL;
               img.onerror = function () {
         URL.revokeObjectURL(this.src);
    // Handle the failure properly
    console.log("Cannot load image");
           };
              img.onload = function () {
    //URL.revokeObjectURL(this.src);
    const [newWidth, newHeight] = calculateSize(img, MAX_WIDTH, MAX_HEIGHT);
              console.log('width=',newWidth ,'newHeight=',newHeight)
            
        var payload =  $('#payload').val();
       
    const canvas = document.createElement("canvas");
    canvas.width = newWidth;
    canvas.height = newHeight;
    const ctx = canvas.getContext("2d");
   ctx.drawImage(img, 0, 0, newWidth, newHeight);
    canvas.toBlob(
      (blob) => {
        // Handle the compressed image. es. upload or save in local state
        displayInfo('Original file', file);
        displayInfo('Compressed file', blob);
          $scope.post = () => {
	               
                 console.log(payload)
	       var pic = document.getElementById('pic').files[0],
				 normal = new FileReader();
			console.log("normal file=",file.size,"compressed=",blob.size)
			
			       if(file.size/1024>1024){
				
					var reader = new FileReader();
				
				reader.onloadend = function(e) {
					var data=e.target.result;
					
					var json = JSON.stringify({

						payload: payload,

						photo: data

					});
	              console.log("over IMB")
					//post of a photo upload request
					
					socket.send('/app/post.public.', {},json)
					}
					 reader.readAsDataURL(blob)
					}else{
						
						console.log("its a normal image")
				
				normal.onloadend = function(e) {
					var data=e.target.result;
					var payload = $('#payload').val();
					var json = JSON.stringify({
						payload: payload,
						photo: data
					});
				
					socket.send('/app/post.public.', {},json)
					}
				}
                  
				normal.readAsDataURL(pic)

				$location.path('/newsfeed')
			}
        
      },
      MIME_TYPE,
      QUALITY,
         
    );
   // document.querySelector("#wizardPicturePreview").append(canvas);
  };
 		});
			
			
		});
		$scope.post= () => {
			var payload=$('#payload').val();
					console.log("clikced",payload)
					socket.send('/app/post.public.', {},JSON.stringify({'payload':payload}))
				}
				
		$scope.payload = '';
		$scope.photo = '';
		   function readURL(input) {
              console.log(input.files[0].size)

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onloadend = function(e) {
					$('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');

					var data = e.target.result;
					var message = "hay there";
				}

				reader.readAsDataURL(input.files[0]);
			}
			
		}
		/////
		
function calculateSize(img, maxWidth, maxHeight) {
  let width = img.width;
  let height = img.height;

  // calculate the width and height, constraining the proportions
  if (width > height) {
    if (width > maxWidth) {
      height = Math.round((height * maxWidth) / width);
      width = maxWidth;
    }
  } else {
    if (height > maxHeight) {
      width = Math.round((width * maxHeight) / height);
      height = maxHeight;
    }
  }
  return [width, height];
}

// Utility functions for demo purpose

function displayInfo(label, file) {
	
  const p = document.createElement('p');
  p.innerText = `${label} - ${readableBytes(file.size)}`;
           console.log(p)
  document.getElementById('wizardPicturePreview').append(p);
}

           function readableBytes(bytes) {
        const i = Math.floor(Math.log(bytes) / Math.log(1024)),
    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

         return (bytes / Math.pow(1024, i)).toFixed(2) + ' ' + sizes[i];
      }
		
			
//////
		/*$scope.post = () => {
			console.log("clicked")
			//$scope.post = post;
			var payload = $scope.payload;
			var sender = $scope.sender;
			//console.log(payload, sender)
			var pic = document.getElementById('pic').files[0],
				read = new FileReader();
			read.onloadend = function(e) {
				var data = e.target.result;
				//console.log(data)

				var json = JSON.stringify({

					payload: payload,
					sender: sender,
					photo: data

				});
				//post of a photo upload request
				socket.send('/app/post.public.', {}, json)
			}

			read.readAsDataURL(pic);


			//post success redirect
			$location.path('/newsfeed')

		}*/


		$scope.createPostBack = () => {
			$('#searchfriend').show();
				$('.drawer').show();
			window.history.back();
		}


	}])

//////
angular.module('home.controller')
	.controller('chatMessages', ['$scope', 'socket', function($scope, socket) {
		$('.common-header').show();
		$('.feed').hide();
			$('#drawer').hide();
		$('.messageList').addClass('message-list')
		const xhr = new XMLHttpRequest();
		//const thisUser=new XMLHttpRequest();
		// thisUser.open("post", 'http://localhost:8080/currentUser')
			//thisUser.send();
		

		$scope.mymessages = '';
		const url = 'https://wesocialites.herokuapp.com/conversations/';

		/*$scope.messages = socket.shareMessage(function (pay) {
		
		 });*/
	  
		xhr.open('POST', url, true)

		xhr.send()
                 
	    xhr.onload = function() {
             var sentByMe = '';
              var fromUser=''
			if (xhr.status === 200) {
				var messages = JSON.parse(xhr.response)
				
				if(messages.length!==0){
				console.log(messages.length,"before filtering")
				
				var uniqueReceiverId = [...messages.reduce((map, obj) => map.set(obj.username, obj), new Map()).values()];
				
		
               var messageholder = ''

				messages.forEach(message => {
					
					var m=message.message;
					
					var subMessage=m.substring(0,7);
					console.log(subMessage,"after sub")
					var time=message.localDatetime;
					var t=time.substring(0,9)
					
                   if(message.isSender){
						console.log(message)
						//console.log(message.message+"----->"+message.toUsername)
					let   fromUser = message.toUsername.charAt(0).toUpperCase() + message.toUsername.slice(1)
                       
					messageholder = messageholder + '<a   href="#!/chat/' +message.toUsername + '">' +

						'<li class="message-list">' +
						'<span class="chat-from">' + `${fromUser}` + 
						'<small class="text-right time">'+t+'</small>'+
						'</span>' +
						'<br>' +
						'<span class="chat-message">you: ' + `${subMessage}...` + '<span>' +
						' </li> </a>';			
								
                  }  
        

              else{             
	                       let   fromUser = message.username.charAt(0).toUpperCase() + message.username.slice(1)
	                      messageholder = messageholder + '<a   href="#!/chat/' +message.username + '">' +

						'<li class="message-list">' +
						'<span class="chat-from">' + `${fromUser}` + 
						'<small class="text-right time">'+t+'</small>'+
						'</span>' +
						'<br>' +
						'<span class="chat-message">' + `${subMessage}` + '<span>' +
						' </li> </a>';		
 		       }
                  
				})
			        $(".message-details").prepend(messageholder);
                    //$(".message-details").append(sentByMe);	
}
	 else{
	               console.log('no-active-conversations')
	               $('.no-active-conversations').append("Whoops It Seems You Have No Active Conversations")
}	

			}

		}
	
		// console.log(xhr)



		//var message;
		// var mychatHolder='';

		/*   messages.forEach((message)=>{
				mychatHolder= mychatHolder + '<a ng-repeat="mess in messages" href="#!/chat/{{message.username}}">'+
				'<li class="message-list">'+
		'<p class="chat-from">'+'</p>'+
		  '<p class="chat-message">{{mess.message}}<p>'+
	   '</li><a>';
	   onsole.log(message.username,"share message cont:: ",message.message)
	eturn mess ;
console.log(message.username)
	  });*/
		// $('.message-details').html(mychatHolder)
	}])

//user profile controller
angular.module('home.controller')
	.controller('profile', ['$scope', '$routeParams', '$window', '$location', function($scope, $routeParams, $window, $location) {
		var xhr = new XMLHttpRequest();
		const url = 'https://wesocialites.herokuapp.com/userprofile/';
      var profile='';
     var image=new Image();
          
		$('.common-header ').hide();
		$('#searchfriend').hide();
			$('#drawer').hide();
		$scope.profileId = $routeParams.userprofileId;
		xhr.open("POST", url + $scope.profileId, true)
		xhr.send();
		xhr.onload = () => {
			if(xhr.status===200){
		 profile = JSON.parse(xhr.response)
	profile.firstname =profile.firstname.charAt(0).toUpperCase() + profile.firstname.slice(1)
	profile.lastname =profile.lastname.charAt(0).toUpperCase() + profile.lastname.slice(1)
	      image.src=profile.profilepic
		console.log(profile)
		 $('.profile-details').append('<section style="background-color: #eee;">'+
        '<div class="container py-2">'+

     '<div class="row">'+
     ' <div class="col-lg-4">'+
       ' <div class="card mb-4">'+
          '<div class="card-body text-center">'+
           '	<img class="profile-photo" src="' +  image.src + '"/>'+
            '<h5 class="my-3">'+profile.firstname +" "+ profile.lastname+'</h5>'+
           ' <span class="mb-1">'+[profile.bio].join('')+'</span>'+
           ' <p class="text-muted mb-4"><span>1.2kfollwers  10 following</span></p>'+
            '<div class="d-flex justify-content-center mb-2">'+
   ' <a  type="button" href="#!/chat/'+profile.username+'" class="btn btn-secondary btn-sm ">Message</a>'+
         ' </div>'+
        '</div>'+
        '<div class="card mb-4 mb-lg-0">'+
         ' <div class="card-body p-0">'+
            '<ul class="list-group list-group-flush rounded-3">'+
             ' <li style="height:20px" class="list-group-item d-flex justify-content-between align-items-center p-3">'+
                ' <p class="mb-0">city</p>'+
                '<i class="fas fa-globe fa-lg text-warning"></i>'+
            
             '</li>'+
              '<li style="height:20px"  class="list-group-item d-flex justify-content-between align-items-center p-3">'+
                 '<p class="mb-0">Joined</p>'+
              '<span class="" style="color: black;">'+profile.joined+'</span>'+
           
             ' </li>'+
              '<li style="height:20px"  class="list-group-item d-flex justify-content-between align-items-center p-3">'+
              ' <p class="mb-0">Born</p>'+
                '<i class="fab fa-calender fa-lg" style="color: #ac2bac;"></i>'+
              
             ' </li>'+
             '<li style="height:20px"  class="list-group-item d-flex justify-content-between align-items-center p-3">'+
              ' <p class="mb-0">Relationship</p>'+
                '<span class="" style="color: black;">'+profile.status+'</span>'+
              
             ' </li>'+
  
            '</ul>'+
          '</div>'+
        '</div>'+
      '</div>'+
       '</section>')
		}else{
			console.log('there was an error')
		}
		}
		
		
		
		$scope.profileBack = () => {

			$window.history.back()
			$('#searchfriend').show();
			$('.post-something').prepend('<a href="#!/profile/">' +
				'<img class="userpost-img " src="/images/profile.jpg"></a>')
			$('#drawer').show();
		}


	}])
	
	////people you may know controller
	angular.module('home.controller')
	.controller('peopleyoumayknow', ['$scope', '$routeParams', '$window', '$location', 
	function($scope,$routeParams, $window, $location) {
		$('.common-header').hide();
		$('.searcharea').hide();
		$('#searchfriend').hide();
		$('#drawer').hide();
		var xhr = new XMLHttpRequest();
		var youMayKnow='';
		var url='https://wesocialites.herokuapp.com/youmayknow/'
		xhr.open("POST",url,true);
		xhr.send()
			xhr.onload=()=>{
		    	
			var profiles=JSON.parse(xhr.response)
			console.log(profiles)
			profiles.forEach(profile=>{
				profile.firstname =profile.firstname.charAt(0).toUpperCase() + profile.firstname.slice(1)
			    profile.lastname  = profile.lastname.charAt(0).toUpperCase() + profile.lastname.slice(1)
				 youMayKnow=youMayKnow+' <li  class="list-group-item ">'+
			'<a href="#!profile/'+profile.id+'">'+
      '  <div class="row" >'+        
      '  <img  src="'+profile.profilepic+'">'+
       ' <span class="name">'+profile.firstname +" "+ profile.lastname+' </span>'+
        ' <span class="somoe-info ml-30 mt-10 text-muted">@'+profile.firstname+'</span>'+
        /*'  <span class="some-bio">'+profile.bio+'</span>'+*/
       ' </div>'+
        '</a>'+
        '</li>'
				
			})
		   
$('.list-group').append(youMayKnow)
		}
		
		     $scope.youMayKnowBack=()=>{
			$window.history.back();
				$('#drawer').hide();
			 $('#searchfriend').show();
		}
		
}
	
])