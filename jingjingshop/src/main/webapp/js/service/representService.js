// 定义服务层:
app.service("representService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../representative/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
});