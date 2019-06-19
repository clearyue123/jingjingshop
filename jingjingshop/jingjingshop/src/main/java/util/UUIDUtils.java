package util;

import java.util.UUID;

/**
 * @author:yue
 * @desc:图片名生成
 * @date:2019.5.11
 */
public class UUIDUtils {

	public static String getRandomImgName(){
		return UUID.randomUUID().toString().replaceAll("-", "").substring(15);
	}
	
	public static void main(String[] args) {
		String randomString = UUIDUtils.getRandomImgName();
		System.out.println(randomString);
	}
}
