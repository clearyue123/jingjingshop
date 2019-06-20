package com.pinyougou.controller.cart;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbAddressMapper;
import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbAddressExample;
import com.pinyougou.pojo.TbAddressExample.Criteria;
import com.pinyougou.service.user.AddressService;

import entity.PageResult;
import entity.Result;
/**
 * @desc:地址管理
 * @author:yue
 * @date:2019.5.20
 */
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private TbAddressMapper addressMapper;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbAddress> findAll(){			
		return addressService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return addressService.findPage(page, rows);
	}
	
	/**
	 * 修改
	 * @param address
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbAddress address){
		try {
			addressService.update(address);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbAddress findOne(Long id){
		return addressService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			addressService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbAddress address, int page, int rows  ){
		return addressService.findPage(address, page, rows);		
	}
	
	
	/**
	 * 小程序 用户id查收货地址
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findListByUserId")
    public Object findListByUserId(@RequestParam(value="userId",required=true)String userId){
		try{
			Map<String, Object> address = addressService.findListByUserId(userId);
			if(address==null){
				return new ApiResult(202, "未查到地址信息，请新增地址！", "");
			}else{
				return new ApiResult(200, "地址信息查询成功", address);
			}
			
		}catch(Exception e){
			return new ApiResult(201, "地址信息查询失败", null);
		}
		
	}	
	
	/**
	 * 小程序  新增地址
	 * @param userId 用户id
	 * @param provinceId 省id
	 * @param cityId 市id
	 * @param townId 区id
	 * @param mobile 手机号
	 * @param address 详细地址
	 * @param contact 联系人
	 * @param alias 别名
	 * @param notes 备注
	 * @return
	 */
	@RequestMapping("/save")
	public Object save(@RequestParam(required = true, value = "userId")String userId,
			          @RequestParam(required = true, value = "provinceId")String provinceId,
			          @RequestParam(required = true, value = "cityId")String cityId,
			          @RequestParam(required = true, value = "townId")String townId,
			          @RequestParam(required = true, value = "mobile")String mobile,
			          @RequestParam(required = true, value = "address")String address,
			          @RequestParam(required = true, value = "contact")String contact,
			          @RequestParam(required = false,value = "alias")String alias,
			          @RequestParam(required=false,value="notes")String notes){
		try {
			if(address==null||"undefined".equals(address)||address.trim().length()==0){
				return new ApiResult(201, "请输入正确格式地址！", "");
			}else{
				TbAddress tbAddress = new TbAddress();
				tbAddress.setUserId(userId);
				tbAddress.setProvinceId(provinceId);
				tbAddress.setCityId(cityId);
				tbAddress.setTownId(townId);
				tbAddress.setMobile(mobile);
				tbAddress.setAddress(address);
				tbAddress.setContact(contact);
				tbAddress.setAlias(alias);
				tbAddress.setIsDefault("1");//是否默认地址 1:是 0:否
				tbAddress.setNotes(notes);
				TbAddressExample addressExample = new TbAddressExample();
				Criteria criteria = addressExample.createCriteria();
				criteria.andUserIdEqualTo(userId);
				List<TbAddress> adddList = addressMapper.selectByExample(addressExample);
				if(adddList!=null&&adddList.size()!=0){
					tbAddress.setId(adddList.get(0).getId());
					tbAddress.setCreateDate(adddList.get(0).getCreateDate());
					addressMapper.updateByPrimaryKey(tbAddress);
					return new ApiResult(200, "地址修改成功", "");
				}else{
					tbAddress.setCreateDate(new Date());
					addressService.add(tbAddress);
					return new ApiResult(200, "地址新增成功", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "地址保存失败,请检查参数是否异常！", "");
		}
	}
	
}
