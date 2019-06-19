app.controller("indexController",function($scope,$http){
	$scope.loginName='yuejingjing';
	
	//消息提醒列表
	$scope.showMsgList = function(){
		$http.get('../indexMsg/showMsgList.do').success(
				function(response){
					$scope.msgList=response.data.msgList;
					$scope.msgCount=response.data.count;
				}			
			);
	}
});