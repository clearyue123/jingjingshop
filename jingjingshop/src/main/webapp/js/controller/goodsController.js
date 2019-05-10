 //控制层 
app.controller('goodsController' ,function($scope,$controller,itemCatService,goodsService,$http){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//新增
	$scope.add=function(){
		alert("add...");
		var serviceObject=goodsService.add($scope.entity);//增加 
		serviceObject.success(
				function(response){
					if(response.flag){
						//重新查询 
			        	$scope.reloadList();//重新加载
					}else{
						alert(response.message);
					}
				}		
			);	
	}
	//修改
	$scope.save=function(){				
		var serviceObject=goodsService.update($scope.entity); //修改  
		serviceObject.success(
			function(response){
				if(response.flag){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.flag){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.list;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    
	// 显示状态
	$scope.status = ["未审核","审核通过","审核未通过","关闭"];
	
	$scope.itemCatList = [];
	// 显示分类:
	$scope.findItemCatList = function(){
		
		itemCatService.findAll().success(function(response){
			for(var i=0;i<response.length;i++){
				$scope.itemCatList[response[i].id] = response[i].name;
			}
		});
	}
	
	// 审核的方法:
	$scope.updateStatus = function(status){
		goodsService.updateStatus($scope.selectIds,status).success(function(response){
			if(response.flag){
				$scope.reloadList();//刷新列表
				$scope.selectIds = [];
			}else{
				alert(response.message);
			}
		});
	}
	
	 //品牌列表
	 $scope.initBrandList = function() {
	    $http.get("../brand/findAll.do").success(function(response){
	    	$scope.brandList=response;
	    });
	 }
	//店铺列表
	$scope.initSellerList = function(){
		$http.get("../seller/findAll.do").success(function(response){
	    	$scope.sellerList=response;
	    });
	}
	//类别列表
    $scope.initCategoryList = function(){
    	$http.get("../itemCat/findAll.do").success(function(response){
	    	$scope.categoryList=response;
	    });
    }
	//规格列表
    $scope.initSpeList = function(){
    	$http.get('../specification/findAll.do').success(function(response){
    		$scope.speList=response;
    		alert("spe test...");
    	});
    }
    //设置是否上架
    $scope.setIsMarketable = function(val){
    	$scope.entity.isMarketable=val
    }
    //设置是否启用规格
    $scope.setIsEnableSpec = function(val){
    	$scope.entity.isEnableSpec=val
    }
    //设置规格ids
    $scope.setSpeIds = function($event,speId){
    	// 复选框选中
		if($event.target.checked){
			// 向数组中添加元素
			$scope.entity.speIds.push(speId);
		}else{
			// 从数组中移除
			var idx = $scope.entity.speIds.indexOf(speId);
			$scope.entity.speIds.splice(idx,1);
		}
    }
    
    
});	
