// 定义控制器:
app.controller("orderController",function($scope,$controller,$http,orderService){
	// AngularJS中的继承:伪继承
	$controller("baseController",{$scope:$scope});
	
	//分页 条件查询
    $scope.searchEntity={};
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(page,rows){
		// 向后台发送请求获取数据:
		orderService.search(page,rows,$scope.searchEntity).success(function(response){
			$scope.paginationConf.totalItems = response.total;
			$scope.list = response.rows;
		});
	}
	
	$scope.update = function(){
		alert("update ...");
		orderService.update($scope.entity);
	}

	$scope.findById = function(id){
		alert(id);
		orderService.findById(id).success(function(response){
			$scope.entity = response;
		});
	}
	
	$scope.engineer = {
            name: "Dani",
            currentActivity: "Fixing bugs"
        };
     
    $scope.activities =
        [
            "Writing code",
            "Testing code",
            "Fixing bugs",
            "Dancing"
        ];
	
});
