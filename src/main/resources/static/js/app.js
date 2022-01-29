
var home=angular.module('home',['home.controller','home.service','home.routes'])
   home.controller('searchback',['$scope','$window',function($scope,$window){
	$scope.searchBack=()=>{
		$('#searchBack').css('display','block')
  console.log("back cliked app.js")
         $window.history.back()
 }

}])
var  clicked=0;
 var  drawerButton=()=>{
	
	clicked++;
	     $('.sidebar').css('display','block')
         $('.newsfeed').css('display','none')
         $('.common-header').css('display','none')
	console.log(clicked)
           if(clicked==2){
	        clicked=0;
	       $('.sidebar').css('display','none')
         $('.newsfeed').css('display','block')
         $('.common-header').css('display','block')
	
}
		console.log("hay there")
	}
   
  
 
	/* */

	/*var noti=()=>{
		if (Notification.permission == 'granted') {
    navigator.serviceWorker.getRegistration().then(function(reg) {
      console.log('nitifi me')
    });
  }
		console.log('hay there')
	}
	noti();*/
	////
	 /* if( 'Notification' in window){
            
            if (Notification.permission === 'granted') {
                // If it's okay let's create a notification
                doNotify();
            }else{
                //notification == denied
                Notification.requestPermission()
                    .then(function(result) {
                        console.log(result);  //granted || denied
                        if( Notification.permission == 'granted'){ 
                            doNotify();
                        }
                    })
                    .catch( (err) => {
                        console.log(err);
                    });
            }
        
        }
        
        function doNotify(){
            let title = "The Title";
            let t = Date.now() + 120000;    //2 mins in future
            let options = {
                body: 'Hello from JavaScript!',
                data: {prop1:123, prop2:"Steve"},
                lang: 'en-CA',
                icon: './img/calendar-lg.png',
                timestamp: t,
                vibrate: [100, 200, 100]
            }
            let n = new Notification(title, options);

            n.addEventListener('show', function(ev){
                console.log('SHOW', ev.currentTarget.data);
            });
            n.addEventListener('close', function(ev){
               console.log('CLOSE', ev.currentTarget.body); 
            });
            setTimeout( n.close.bind(n), 8000); //close notification after 3 seconds
        }*/