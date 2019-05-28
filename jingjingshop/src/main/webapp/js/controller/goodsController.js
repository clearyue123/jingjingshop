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
		var serviceObject;
		if($scope.entity.goodsId != null){
			// 更新
			serviceObject = goodsService.update($scope.entity);//修改
		}else{
			// 保存
			serviceObject = goodsService.add($scope.entity);//增加 
		}
		serviceObject.success(
				function(response){
					if(response.code==200){
						//重新查询 
						alert(response.message);
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
		goodsService.dele($scope.selectIds).success(
			function(response){
				if(response.code==200){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(){
		var page = $scope.paginationConf.currentPage;
		var rows = $scope.paginationConf.itemsPerPage
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
    //设置是否上架 默认1:上架
    $scope.setIsMarketable = function(val){
    	$scope.entity.isMarketable=val
    }
    //设置是否启用规格 默认1:启用
    $scope.setIsEnableSpec = function(val){
    	$scope.entity.isEnableSpec=val;
    	$scope.tempSpeList = $scope.speList;
    	if(val=0){
    		$scope.speList=[];
    	}else{
    		$scope.speList=$scope.tempSpeList;
    	}
    }
    
    $scope.uploadFile = function()
    {
		// 向后台传递数据:
		var formData = new FormData();
		var smallPic = document.querySelector('input[id=smallPic]').files[0];
		// 向formData中添加数据:
		formData.append("smallPic",smallPic);
		
		return $http({
			method:'post',
			url:'../upload/uploadFile.do',
			data:formData,
			headers:{'Content-Type':undefined} ,// Content-Type : text/html  text/plain
			transformRequest: angular.identity
		});
	}
    //上传列表图片
    $scope.uploadSmallPic = function(){
    	// 调用uploadService的方法完成文件的上传
    	$scope.uploadFile().success(function(response){
    		if(response.code==200){
				// 获得url
				$scope.entity.smallPic=  response.data;
				alert(response.message);
			}else{
				alert(response.message);
			}
		});
    }
    $scope.uploadFiles = function(){
		// 向后台传递数据:
		var formData = new FormData();
		var itemImages = document.querySelector('input[id=itemImages]').files;
		// 向formData中添加数据:
		 for (var i=0; i<itemImages.length; i++) {
			 formData.append("itemImage", itemImages[i]);
         }
		return $http({
			method:'post',
			url:'../upload/uploadItemImages.do',
			data:formData,
			headers:{'Content-Type': undefined} ,// Content-Type : text/html  text/plain
			transformRequest: angular.identity
		});
	}
    //上传详情图片数组
    $scope.uploadItemImages = function(){
    	$scope.uploadFiles().success(function(response){
			if(response.code==200){
				// 获得url
				$scope.entity.itemImages =  response.data;
				alert(response.message);
			}else{
				alert(response.message);
			}
		});
    }
    //通过商品id查商品数据
    $scope.findById = function(goodsId){
    	goodsService.findOne(goodsId).success(function(response){
    		$scope.entity = response;
    	});
    }
});	
