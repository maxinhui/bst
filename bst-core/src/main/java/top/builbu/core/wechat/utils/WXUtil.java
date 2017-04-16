package top.builbu.core.wechat.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import top.builbu.core.wechat.entity.Unifiedorder;
import top.builbu.core.wechat.entity.UnifiedorderResult;
import top.builbu.core.wechat.entity.WXUrl;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信
 * @author Administrator
 *
 */
public class WXUtil {

	
	/**
	 * 微信前端签名
	 * @param jsapiTicket
	 * @param url
	 * 前端传入路径
	 * location.href.split('#')[0].replace(new RegExp(/(&)/g), '%26')
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getResultData(String jsapiTicket, String url,String appid)
			throws Exception {
		    Map<String,Object> map = null;
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			String noncestr=RandCharsUtils.getRandomString(16);//生成16位随机字符串
			String timestamp=(System.currentTimeMillis() / 1000) + "";//获取当前时间戳
		  
			//组装签名数据
			parameters.put("jsapi_ticket",jsapiTicket );//获取当前公众号的jsapiTicket
			parameters.put("noncestr", noncestr);
			parameters.put("timestamp", timestamp);
			parameters.put("url", url);		
			String sign=SHA1.encode(WXSignUtils.createSign(parameters));//签名生成   
			
			//组装返回数据
			map=new HashMap<String, Object>();
			map.put("noncestr", noncestr);
			map.put("timestamp", timestamp);
			map.put("sign", sign);	
			map.put("appId", appid);
			return map;
		 
	}
	
	
	
	/**
	 * 调起支付
	 * @param appid
	 * @param mch_id
	 * @param body
	 * @param detail
	 * @param attach
	 * @param notify_url
	 * @param trade_type
	 * @param openid
	 * @param nonce_str
	 * @param total_fee
	 * @param spbill_create_ip
	 * @return
	 * @throws Exception
	 */
	public static JSONObject wxPayPrepay(Unifiedorder unifiedorder) throws Exception {
				
		unifiedorder.setNonceStr(RandCharsUtils.getRandomString(16));
		
		unifiedorder.setTimeStart(RandCharsUtils.timeStart());
		unifiedorder.setTimeExpire(RandCharsUtils.timeExpire());
	
		//参数：开始生成签名
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", unifiedorder.getAppId());
		parameters.put("mch_id", unifiedorder.getMchId());
		parameters.put("nonce_str", unifiedorder.getNonceStr());
		parameters.put("body", unifiedorder.getBody());
		parameters.put("detail", unifiedorder.getDetail());
		parameters.put("attach", unifiedorder.getAttach());
		parameters.put("out_trade_no", unifiedorder.getOutTradeNo());
		parameters.put("total_fee", unifiedorder.getTotalFee());
		parameters.put("time_start", unifiedorder.getTimeStart());
		parameters.put("time_expire", unifiedorder.getTimeExpire());
		parameters.put("notify_url", unifiedorder.getNotifyUrl());
		parameters.put("trade_type", unifiedorder.getTradeType());
		parameters.put("spbill_create_ip", unifiedorder.getSpbillCreateIp());
		parameters.put("openid",unifiedorder.getOpenId());
		String sign = WXSignUtils.createSign("UTF-8", parameters);

		unifiedorder.setSign(sign);
		
		//构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		
		//条用统一支付接口
		String weixinPost = HttpXmlUtils.httpsRequest(WXUrl.wxUrl, "POST", xmlInfo).toString();

		//接口返回
		Map<String,Object> dataMap=ParseXMLUtils.jdomParseXml(weixinPost);
		
		UnifiedorderResult unifiedorderResult = null;
		JSONObject jsonObject = null;
		unifiedorderResult = JSONObject.parseObject(JSONObject.toJSONString(dataMap), UnifiedorderResult.class);
		//生成客户端支付签名
		if(null != unifiedorderResult){
			SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	        params.put("appId", unifiedorder.getAppId());
	        String timestamp=(System.currentTimeMillis() / 1000) + "";
	        params.put("timeStamp", timestamp);
	        params.put("nonceStr", unifiedorder.getNonceStr());
	        params.put("package", "prepay_id="+unifiedorderResult.getPrepay_id());
	        params.put("signType", "MD5");
	        String paySign =WXSignUtils.createSign("UTF-8",params);
	        params.put("packageValue", "prepay_id="+unifiedorderResult.getPrepay_id());//这里弄个packageValue，是方便ajax传值到页面中package是js的关键字出错
	        params.put("paySign", paySign);
	        jsonObject = new JSONObject();
			jsonObject.put("dataMap", params);
		
		}
		return jsonObject;
	}


}
