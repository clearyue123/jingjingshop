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
	
	$scope.save=function(){
		var object = userService.save($scope.entity);
		object.success(function(response){
			alert(response);
			if(response.flag==true){
				// 保存成功
				alert(response.message);
				$scope.reloadList();
			}else{
				alert(response.message);
			}
		})
	}
	
	// 查询一个:
	$scope.findById = function(id){
		userService.findById(id).success(function(response){
			$scope.entity = response;
		});
	}
	
});
