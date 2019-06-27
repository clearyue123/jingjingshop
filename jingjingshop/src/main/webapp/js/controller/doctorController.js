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
	
	//新增医生
	$scope.save = function(){
		var serviceObject;
		if($scope.entity.did == null){
			serviceObject = doctorService.add($scope.entity);
		}else{
			serviceObject = doctorService.update($scope.entity);
		}
		serviceObject.success(
				function(response){
				   if(response.code == 200){
					   alert(response.message);
					   $scope.reloadList();
				   }else{
					   alert(response.message);
				   }
			});
	}
	
	//删除医生
	$scope.dele = function(){
		doctorService.dele($scope.selectIds).success(
		    function(response){
		    	if(response.code == 200){
					   alert(response.message);
					   $scope.reloadList();
				   }else{
					   alert(response.message);
				   }
		    }		
		);
	}
	
	//通过医生id查医生信息
	$scope.findDoctorById = function(id){
		doctorService.findOne(id).success(
		    function(response){
		    	if(response.code == 200){
		    		$scope.entity = response.data;
		    	}else{
		    		alert(response.message);
		    	}
		    }		
		
		);
	}
});
