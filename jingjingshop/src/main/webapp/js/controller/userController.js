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
	
	//删除用户
	$scope.del = function(){
		userService.dele($scope.selectIds).success(
			function(response){
				if(response.code==200){
					alert(response.message);
					$scope.reloadList();
				}else{
					alert(response.message);
					$scope.reloadList();
				}
			}
		
		)
	}
	
	//设置用户性别
	$scope.setSex = function(sexVal){
		$scope.entity.sex = sexVal;
	}
	
	//保存用户
	$scope.save = function(){
		var serviceObject;
		if($scope.entity.id != null){
			serviceObject = userService.update($scope.entity);
		}else{
			serviceObject = userService.add($scope.entity);
		}
		serviceObject.success(
				function(response){
			      if(response.code==200){
			    	  alert(response.message);
			    	  $scope.reloadList();
			      }else{
			    	  alert(response.message);
			      }
		    }
	)
	}
	
	//通过id查询用户信息
	$scope.findUserById = function(userId){
		userService.findOne(userId).success(
				function(response){
			      if(response.code == 200){
			    	  $scope.entity = response.data;
			      }else{
			    	  alert(response.message);
			      }
		})
	}
	
});
