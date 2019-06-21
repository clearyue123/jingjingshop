// 定义控制器:
app.controller("doctorController",function($scope,$controller,$http,doctorService){
	// AngularJS中的继承:伪继承
	$controller('baseController',{$scope:$scope});

    $scope.searchEntity={};
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(page,rows){
		// 向后台发送请求获取数据:
		doctorService.search(page,rows,$scope.searchEntity).success(function(response){
			$scope.paginationConf.totalItems = response.data.total;
			$scope.list = response.data.rows;
		});
	}
	
});
