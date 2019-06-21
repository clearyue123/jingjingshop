// 定义控制器:
app.controller("userController",function($scope,$controller,$http,userService){
	// AngularJS中的继承:伪继承
	$controller('baseController',{$scope:$scope});

    $scope.searchEntity={};
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(page,rows){
		// 向后台发送请求获取数据:
		userService.search(page,rows,$scope.searchEntity).success(function(response){
			$scope.paginationConf.totalItems = response.total;
			$scope.list = response.rows;
		});
	}
	
	$scope.del = function(){
		userService.dele($scope.selectIds).success(
			function(response){
				if(response.code==200){
					alert("删除成功！");
					$scope.reloadList();
				}else{
					alert("删除失败！");
					$scope.reloadList();
				}
			}
		
		)
	}
	
});
