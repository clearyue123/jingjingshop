package util;

import java.util.Random;

/**
 * @author：yue
 * @date:2019.5.29
 * @desc：字符串生成
 */
public class MyStringUtils {
     
	public static String getRandomStringByLength(Integer length){
		String oriStr = "qwertyuiopadsfghjklzxcvbnm0123456789";
		StringBuffer buffer = new StringBuffer();
		Random rand = new Random();
		for(int i=0;i<length;i++){
			Integer nextInt = rand.nextInt(oriStr.length());
			buffer.append(oriStr.charAt(nextInt));
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomStringByLength(16));
	}
}
