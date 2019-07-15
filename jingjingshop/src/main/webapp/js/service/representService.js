// 定义服务层:
app.service("representService",function($http){

	this.search = function(page,rows,searchEntity){
		return $http.post("../representative/search.do?page="+page+"&rows="+rows,searchEntity);
	}
	
	this.add = function(entity){
		return $http.post("../representative/add.do",entity);
	}
	
	this.dele = function(ids){
		return $http.post("../representative/dele.do?ids="+ids);
	}
	
	this.findOne = function(id){
		return $http.post("../representative/findOne.do?id="+id);
	}
	
	this.update = function(entity){
		return $http.post("../representative/update.do",entity);
	}
	
	this.findRelatedDoctors = function(id){
		return $http.post("../representative/selectRelatedDoctorList.do?id="+id);
	}
});