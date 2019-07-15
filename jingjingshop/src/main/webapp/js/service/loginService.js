app.service("loginService",function($http){
	
	this.showName = function(){
		return $http.get("../adminLogin/showName.do");
	}
	
});