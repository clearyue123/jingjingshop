package util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @desc：获取当前IP地址
 * @author:yue
 * @date：2019.5.29
 */
public class IpUtils {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
	    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	         //多次反向代理后会有多个ip值，第一个ip才是真实ip
	    	int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	            return ip;
	        }
	    }
	    ip = request.getHeader("X-Real-IP");
	    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	       return ip;
	    }
	    return request.getRemoteAddr();
	}
}
