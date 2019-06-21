// 定义服务层:
app.service("doctorService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../doctor/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
});