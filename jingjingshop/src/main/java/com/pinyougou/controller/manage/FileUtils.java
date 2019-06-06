package com.pinyougou.controller.manage;

/**
 * @desc:文件工具类
 * @author:yue
 * @date:2019.6.6 
 */
public class FileUtils {
   
	/**
	 * 校验是否是图片文件
	 * @param ext
	 * @return
	 */
	public static boolean isImgFile(String ext){
		if(".jpg".equals(ext.toLowerCase())||
				".bmp".equals(ext.toLowerCase())||
				".gif".equals(ext.toLowerCase())||
				".pnf".equals(ext.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isVideoFile(String ext){
		if(".wmv".equals(ext.toLowerCase())||
				".rm".equals(ext.toLowerCase())||
				".mp4".equals(ext.toLowerCase())||
				".avi".equals(ext.toLowerCase())||
				".flv".equals(ext.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(FileUtils.isVideoFile(".mp4"));
	}
}
