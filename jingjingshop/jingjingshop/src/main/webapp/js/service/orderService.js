// 定义服务层:
app.service("orderService",function($http){
	
	//分页 查询
	this.search = function(page,rows,searchEntity){
		return $http.post("../order/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
	//新增功能
	this.save = function(){
		return $http.post("../order/save.do",entity);
	}
	
	//更新功能
	this.update = function(entity){
		return $http.post("../order/update.do",entity);
	}
	
	this.findById = function(id){
		return $http.post("../order/findOne.do?id="+id);
	}
});