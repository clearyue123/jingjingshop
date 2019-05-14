app.service("loginService",function($http){
	
	this.showName = function(){
		alert("111");
		return $http.get("../login/showName.do");
	}
	
});