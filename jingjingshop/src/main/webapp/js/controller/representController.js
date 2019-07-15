// 定义控制器:
app.controller("representController",function($scope,$controller,$http,representService){
	// AngularJS中的继承:伪继承
	$controller('baseController',{$scope:$scope});

    $scope.searchEntity={};
	// 假设定义一个查询的实体：searchEntity
	$scope.search = function(page,rows){
		// 向后台发送请求获取数据:
		representService.search(page,rows,$scope.searchEntity).success(function(response){
			$scope.paginationConf.totalItems = response.data.total;
			$scope.list = response.data.rows;
		});
	}
	
	
	//新增代表
	$scope.save = function(){
		var serviceObject;
		if($scope.entity.rid == null){
			serviceObject = representService.add($scope.entity);
		}else{
			serviceObject = representService.update($scope.entity);
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
	
	//删除代表
	$scope.dele = function(){
		representService.dele($scope.selectIds).success(
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
	
	//通过代表id查代表信息
	$scope.findRepresentById = function(id){
		representService.findOne(id).success(
		    function(response){
		    	if(response.code == 200){
		    		$scope.entity = response.data;
		    	}else{
		    		alert(response.message);
		    	}
		    }		
		
		);
	}
	
	//查看代表关联医生列表
	$scope.findRelatedDoctors = function(id){
		representService.findRelatedDoctors(id).success(
		     function(response){
		    	 if(response.code == 200){
		    		 $scope.relatedDoctorList = response.data;
		    	 }else{
		    		 alert(response.message);
		    	 }
		     }		
		 );
	}
});
