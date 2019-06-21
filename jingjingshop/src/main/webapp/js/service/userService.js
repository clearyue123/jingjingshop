// 定义服务层:
app.service("userService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../user/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
	this.dele = function(ids){
		return $http.post("../user/delete.do?ids="+ids);
	}
	
});