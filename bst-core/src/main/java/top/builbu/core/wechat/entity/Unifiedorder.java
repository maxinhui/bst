package top.builbu.core.wechat.entity;

/**
 * 统一下单提交为微信的参数
 */
public class Unifiedorder {
	
	private String appId;//微信分配的公众账号ID（企业号corpid即为此appId）,例如：wxd678efh567hg6787
	private String mchId;//商户id
	private String deviceInfo;//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String nonceStr;//随机字符串:数字+大写字母的组合，32位
	private String sign;//签名
	private String body;//商品或支付单简要描述
	private String detail;//商品名称明细列表
	private String attach;//附加参数
	private String outTradeNo;//商户系统内部的订单号
	private String feeType;//货币类型:符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private int totalFee;//总金额
	private String spbillCreateIp;//APP和网页支付提交[用户端ip]，Native支付填调用微信支付API的机器IP。
	private String timeStart;//订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	private String timeExpire;//订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010;最短失效时间间隔必须大于5分钟[支付宝是30分钟，同样30分钟]
	private String goodsTag;//商品标记，代金券或立减优惠功能的参数
	private String notifyUrl;//接收微信支付异步通知回调地址
	private String tradeType;//交易类型:JSAPI，NATIVE，APP
	private String productId;//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	private String limitPay;//no_credit--指定不能使用信用卡支付
	private String openId;//trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getLimitPay() {
		return limitPay;
	}
	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openid) {
		this.openId = openid;
	}
	
	
}
