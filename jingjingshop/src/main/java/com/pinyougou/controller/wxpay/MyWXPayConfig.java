package com.pinyougou.controller.wxpay;

public class MyWXPayConfig{
	//小程序appid
	public static final String APPID="wx4819448530673176";
	//微信支付的商户id
	public static final String MCHID = "1536964241";
	//微信支付的商户密钥
	public static final String KEY = "e9rt48ztrh7zirm2ftlv339dydi66o72";
	//支付成功后的服务器回调url
	public static final String NOTIFYURL = "http://www.weixin.qq.com/wxpay/pay.php";
	//签名方式，固定值
	public static final String SIGNTYPE = "MD5";
	//交易类型，小程序支付的固定值为JSAPI
	public static final String TRADETYPE = "JSAPI";
	//微信统一下单接口地址
	public static final String PAYURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//微信统一下单接口地址(测试)
   // public static final String PAYURL = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
}
