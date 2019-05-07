package com.pinyougou.controller.user;

import java.util.List;

import com.pinyougou.common.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.user.UserService;

import entity.PageResult;
import entity.Result;
import util.PhoneFormatCheckUtils;

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
    public ApiResult userupdate(@RequestBody TbUser user) {
        try {
            if (user.getId()==null) {
                userService.add(user);
                return new ApiResult(200, "新增成功", "新增成功");
                }
             userService.update(user);
                return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(00004, "操作失败", user);
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
        return new ApiResult(00005, "无此人信息", users);
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
     * 增加
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbUser user, String smscode) {
        try {
            userService.add(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbUser user) {
        try {
            userService.update(user);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbUser findOne(Long id) {
        return userService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            userService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
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

    @RequestMapping("/sendCode")
    public Result sendCode(String phone) {

        if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
            return new Result(false, "手机格式不正确");
        }

        try {
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "验证码发送失败");
        }
    }

}
