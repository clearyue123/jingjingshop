package util;  
  
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;  
  
/**
 * @desc:调用鱼跃保存订单接口
 * @author:yue
 * @date:2019.6.19
 */
public class APIHttpClient {  
  
    // 接口地址  
    private static String apiURL = "http://47.111.66.112:9001/yuwellws/request/order/saveOrder";  
    private Log logger = LogFactory.getLog(this.getClass());  
    private HttpClient httpClient = null;  
    private HttpPost method = null;  
    private long startTime = 0L;  
    private long endTime = 0L;  
    private int status = 0;  
    
    /** 
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误 
     * @return the status 
     */  
    public int getStatus() {  
        return status;  
    }  
  
    /** 
     * @param status 
     * the status to set 
     */  
    public void setStatus(int status) {  
        this.status = status;  
    }  
  
    /** 
     * @return the startTime 
     */  
    public long getStartTime() {  
        return startTime;  
    }  
  
    /** 
     * @return the endTime 
     */  
    public long getEndTime() {  
        return endTime;  
    }  
    
    /** 
     * 接口地址 
     *  
     * @param url 
     */  
    public APIHttpClient() {  
        httpClient = new DefaultHttpClient();  
        method = new HttpPost(apiURL);  
    }  
  
    /** 
     * 调用 API 
     *  
     * @param parameters 
     * @return 
     */  
    public String post(String parameters) {  
        String body = null;  
        logger.info("parameters:" + parameters);  
  
        if (method != null & parameters != null  
                && !"".equals(parameters.trim())) {  
            try {  
                // 建立一个NameValuePair数组，用于存储欲传送的参数  
            	String encoding = DatatypeConverter.printBase64Binary("admin:nimda".getBytes("UTF-8"));
            	method.addHeader("Authorization","Basic "+encoding);  
                method.addHeader("Content-type","application/json; charset=utf-8");  
                method.setHeader("Accept", "application/json");  
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));  
                startTime = System.currentTimeMillis();  
                HttpResponse response = httpClient.execute(method);  
                endTime = System.currentTimeMillis();  
                int statusCode = response.getStatusLine().getStatusCode();  
                logger.info("statusCode:" + statusCode);  
                logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));  
                if (statusCode != HttpStatus.SC_OK) {  
                    logger.error("Method failed:" + response.getStatusLine());  
                    status = 1;  
                }  
                // Read the response body  
                body = EntityUtils.toString(response.getEntity());  
            } catch (Exception e) {  
                // 网络错误  
                status = 3;  
                e.printStackTrace();
            } finally {  
                logger.info("调用接口状态：" + status);  
            }  
  
        }  
        return body;  
    }  
    
    public static void main(String[] args) {  
        APIHttpClient ac = new APIHttpClient();  
        Map<String, Object> data = new HashMap<String,Object>(); 
        List<Map<String,Object>> orderDataList = new ArrayList<>();
        Map<String,Object> orderMap=new HashMap<String,Object>(); 
        orderMap.put("code", "1103726592");
        orderMap.put("organization", "1099");
        orderMap.put("orderType", "ZS12");
        orderMap.put("unit", "22000748");
        orderMap.put("payMethod", "online");
        orderMap.put("currency", "CNY");
        orderMap.put("submitDate", "20190615");
        orderMap.put("creater", "10050465");
        orderMap.put("shipDate", "20190715");
        orderMap.put("salesMan", "admin");
        orderMap.put("manager", "admin");
        orderMap.put("note", "订单备注");
        orderMap.put("country", "CN");
        orderMap.put("region", "010");
        orderMap.put("city", "北京市");
        orderMap.put("cityDistict", "海淀区");
        orderMap.put("streeName", "世纪大道1050弄");
        orderMap.put("firstName", "张三");
        orderMap.put("companyName", "阿里巴巴");
        orderMap.put("cellPhone", "18674059182");
        Map<String, Object> entries = new HashMap<String,Object>(); 
        entries.put("code", "1103726592");
        entries.put("entryNumber", "10");
        entries.put("product", "30001007");
        entries.put("quantity", "1");
        entries.put("basePrice", "11000");
        entries.put("note", "备注");
        orderMap.put("entries", entries);
        orderDataList.add(orderMap);
        data.put("orderDataList", orderDataList);
        String jsonString = JSON.toJSONString(data);
        System.out.println("json:"+jsonString);
        System.out.println(ac.post(jsonString));  
    }  
}