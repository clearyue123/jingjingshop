package util;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @desc:MD5加密
 * @author:yue
 * @date：2019.7.1
 */
public class MyMD5Utils {

	public static String getMD5Str(String content,String charset){
		return DigestUtils.md5Hex(getContentBytes(content, charset));
	}
	
	/**  
     * @param content  
     * @param charset  
     * @return  
     * @throws SignatureException  
     * @throws UnsupportedEncodingException  
     */   
    public static byte[] getContentBytes(String content, String charset) {   
        if (charset == null || "".equals(charset)) {   
            return content.getBytes();   
        }   
        try {   
            return content.getBytes(charset);   
        } catch (UnsupportedEncodingException e) {   
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);   
        }   
    }   
    
    public static void main(String[] args) {
		System.out.println(getMD5Str("123", "utf-8"));
	}
}
