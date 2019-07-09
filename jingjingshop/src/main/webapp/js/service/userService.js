// 定义服务层:
app.service("userService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../user/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
	this.dele = function(ids){
		return $http.post("../user/delete.do?ids="+ids);
	}
	
	this.add = function(entity){
		return $http.post("../user/add.do",entity);
	}
	
	this.findOne = function(userId){
		return $http.post("../user/findOne.do?id="+userId);
	}
	
	this.update = function(entity){
		return $http.post("../user/update.do",entity);
	}
	
	this.findPurchaseOrder = function(userId){
		return $http.post("../user/findPurchaseOrder.do?userId="+userId);
	}
});