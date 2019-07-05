// 定义控制器:
app.controller("orderController",function($scope,$controller,$http,orderService){
	// AngularJS中的继承:伪继承
	$controller("baseController",{$scope:$scope});
	
	//分页 条件查询
    $scope.searchEntity={};
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(){
		var page = $scope.paginationConf.currentPage;
		var rows = $scope.paginationConf.itemsPerPage;
		// 向后台发送请求获取数据:
		orderService.search(page,rows,$scope.searchEntity).success(function(response){
			$scope.paginationConf.totalItems = response.data.total;
			$scope.list = response.data.rows;
		});
	}
	
	$scope.update = function(){
		orderService.update($scope.entity);
	}

	$scope.findById = function(id){
		orderService.findById(id).success(function(response){
			$scope.entity = response.data;
		});
	}
	
	//查看订单明细
	$scope.showOrderDetatil = function(orderId){
		orderService.showOrderDetatil(orderId).success(function(response){
			$scope.orderDetaiId = orderId;
			$scope.orderItemList = response.data.itemMapList;
			$scope.orderTotal = response.data.orderDetailMap.payment;
		})
	}
});
