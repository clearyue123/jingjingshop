// 定义服务层:
app.service("doctorService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../doctor/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
	this.add = function(entity){
		return $http.post("../doctor/add.do",entity);
	}
	
	this.dele = function(ids){
		return $http.post("../doctor/delete.do?ids="+ids);
	}
	
	this.findOne = function(id){
		return $http.post("../doctor/findOne.do?id="+id);
	}
	
	this.update = function(entity){
		return $http.post("../doctor/update.do",entity);
	}
});