app.controller("indexController",function($scope,$http,loginService){
	
	
	//消息提醒列表
	$scope.showMsgList = function(){
		$http.get('../indexMsg/showMsgList.do').success(
				function(response){
					$scope.msgList=response.data.msgList;
					$scope.msgCount=response.data.count;
				}			
			);
	}
	
	//展示登陆用户名
	loginService.showName().success(function(response){
		$scope.loginName= response.loginName;
	});
});