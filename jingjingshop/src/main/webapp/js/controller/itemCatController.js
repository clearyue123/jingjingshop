 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){	
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.code==200){
					$scope.selectIds = [];
					alert(response.message);
					//刷新列表
					if($scope.grade == 1){
						$scope.selectList({id:0});
					}
					if($scope.grade == 2){
						$scope.selectList($scope.entity_1);
					}
					if($scope.grade == 3){
						$scope.selectList($scope.entity_2);
					}
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	// 根据父ID查询分类
	$scope.findByParentId =function(parentId){
		itemCatService.findByParentId(parentId).success(function(response){
			$scope.list=response;
		});
	}
	
	// 定义一个变量记录当前是第几级分类
	$scope.grade = 1;
	
	$scope.setGrade = function(value){
		$scope.grade = value;
	}
	
	$scope.selectList = function(p_entity){
		if($scope.grade == 1){
			$scope.entity_1 = null;
			$scope.entity_2 = null;
		}
		if($scope.grade == 2){
			$scope.entity_1 = p_entity;
			$scope.entity_2 = null;
		}
		if($scope.grade == 3){
			$scope.entity_2 = p_entity;
		}
		$scope.findByParentId(p_entity.id);
	}
	
	//新增类别
	$scope.addCategory = function(){
		if($scope.grade == 1){
			$scope.categoryEntity.parentId=0;
		}
		if($scope.grade == 2){
			$scope.categoryEntity.parentId=$scope.entity_1.id;
		}
		if($scope.grade == 3){
			$scope.categoryEntity.parentId=$scope.entity_2.id;
		}
		
		var serviceObject;//服务层对象  				
		if($scope.categoryEntity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.categoryEntity ); //修改  
		}else{
			serviceObject=itemCatService.add( $scope.categoryEntity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.flag){
					//重新查询 
					if($scope.grade == 1){
						$scope.selectList({id:0});
					}
					if($scope.grade == 2){
						$scope.selectList($scope.entity_1);
					}
					if($scope.grade == 3){
						$scope.selectList($scope.entity_2);
					}
					$scope.categoryEntity = null;
				}else{
					alert(response.message);
				}
			}		
		);		
	}
	
	//修改类别
	$scope.findOne = function(categoryId){
		itemCatService.findOne(categoryId).success(function(response){
			if(response.code==200){
				$scope.categoryEntity = response.data;
			}
		});
	}
    
	//文件上传
	$scope.uploadFile = function(){
		alert("111...");
		// 向后台传递数据:
		var formData = new FormData();
		var categoryPicture = document.querySelector('input[id=categoryPicture]').files[0];
		formData.append("uploadFile",categoryPicture);
		return $http({
			method:'post',
			url:'../upload/uploadFile.do',
			data:formData,
			headers:{'Content-Type': undefined} ,// Content-Type : text/html  text/plain
			transformRequest: angular.identity
		});
	}

	
	//上传分类图片
	$scope.uploadCategoryPicture = function(){
		$scope.uploadFile().success(function(response){
    		if(response.code==200){
    			alert(response.data);
				// 获得url
				$scope.entity.categoryPicture = response.data;
				alert(response.message);
			}else{
				alert(response.message);
			}
		});
	}
	
});	
