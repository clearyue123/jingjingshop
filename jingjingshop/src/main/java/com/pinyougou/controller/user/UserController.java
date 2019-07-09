package com.pinyougou.controller.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.user.UserService;

import entity.PageResult;

/**
 * 用户管理 controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping("/userupdate")
    public ApiResult userupdate(@RequestParam(required = false, value = "id" ) Long id,
                                @RequestParam(required = false, value = "username" ) String  username,
                                @RequestParam(required = false, value = "name" ) String  name,
                                @RequestParam(required = false, value = "phone" ) String  phone,
                                @RequestParam(required = false, value = "head_pic" ) String  head_pic,
                                @RequestParam(required = false, value = "birthday" ) String  birthday
                              ) {
        try {
            TbUser  user=new TbUser();
            user.setId(id);
            user.setUsername(username);
            user.setName(name);
            user.setPhone(phone);
            user.setBirthday(birthday);
            user.setHeadPic(head_pic);
            System.out.println(user);
            if (user.getId()==null||user.getId().equals("null")) {
                userService.add(user);
                return new ApiResult(200, "新增成功", "新增成功");
                }else{
             userService.update(user);
                return new ApiResult(200, "编辑成功", "编辑成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(00004, "操作失败", "字段超出范围或者格式不正确");
    }


    /**
     * 查询个人信息
     *
     * @param
     * @return
     */
    @RequestMapping("/userfind")
    public ApiResult userfind(Long id) {
        TbUser users = userService.findOne(id);
        if (users != null) {
            return new ApiResult(200, "获取成功", users);
        }
        return new ApiResult(00005, "无此人信息", "无此人信息");
    }

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbUser> findAll() {
        return userService.findAll();
    }


    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return userService.findPage(page, rows);
    }
    
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public ApiResult delete(final Long[] ids) {
        try {
            userService.delete(ids);
            return new ApiResult(200,"删除成功","");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201,"删除失败","");
        }
    }

    @RequestMapping("/deleteAll")
    public ApiResult deleteAll(){
    	try{
    		userService.deleteAll();
    		return new ApiResult(200, "", "");
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "", "");
    	}
    }
    
    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbUser user, int page, int rows) {
        return userService.findPage(user, page, rows);
    }

    /**
     * 后台用户管理 新增用户
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public ApiResult addUser(@RequestBody TbUser user){
    	try{
    		int i = userService.add(user);
    		if(i>0){
    			return new ApiResult(200, "新增成功！", "");
    		}else{
    			return new ApiResult(201, "新增失败！", "");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "新增失败！", "");
    	}
    }
    
    /**
     * 后台用户管理 修改用户信息
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public ApiResult findOne(Long id) {
    	try{
    		TbUser user = userService.findOne(id);
    		return new ApiResult(200, "查询失败！", user);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "查询失败！", "");
    	}
    }

    
    /**
     * 后台管理 用户修改
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public ApiResult update(@RequestBody TbUser user) {
        try {
            userService.update(user);
            return new ApiResult(200, "修改成功！","");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201, "修改失败！","");
        }
    }
    
    /**
     * 后台管理 用户id查用户购买记录
     * @param userId
     * @return
     */
    @RequestMapping("/findPurchaseOrder")
    public ApiResult findPurchaseOrder(String userId){
    	try{
    		List<Map<String,Object>> orderList = userService.findPurchaseOrder(Long.parseLong(userId));
    		return new ApiResult(200, "用户购买记录查询成功！",orderList);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "用户购买记录查询失败！","");
    	}
    }
}
