package top.builbu.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseResultCode
 * @Description: TODO 基础返回码
 * @date 2016年3月11日 上午10:14:38
 *  
 */
public abstract class BaseResultCode {

	public static Map<String, String> map = new HashMap<String, String>();
	
	public final static String TRUE = "0000";
	public final static String COMMON_FAIL = "0001";
	public final static String COMMON_MISSING_PARAMS = "0002";
	public final static String COMMON_WRONG_PARAMS = "0003";
	public final static String COMMON_DB_ERRORS = "0004";
	public final static String COMMON_TOO_MANY_RESULTS = "0005";
	public final static String COMMON_ERROR_FORMAT = "0006";
	public final static String COMMON_ERROR_FORWARD = "0007";
	public final static String COMMON_NO_DATA = "0008";
	public final static String COMMON_PERMISSION_DENIED = "0009";
	public final static String COMMON_CONTAIN_SENSITIVE = "0010";
	public final static String COMMON_CONTAIN_ZUOFEI = "0011";
	public final static String COMMON_NO_ERRORS = "0012";
	public final static String COMMON_DICTIONARY = "0013";
	public final static String COMMON_CONTAIN_SHENHE = "0014";
	public final static String COMMON_CONTAIN_WEISHENHE = "0015";
	public final static String COMMON_CONTAIN_DELETE = "0016";
	public final static String COMMON_CONTAIN_DELETEI = "0017";
	public final static String COMMON_CONTAIN_YIQIYONG = "0018";
	public final static String COMMON_CONTAIN_WEIQIYONG = "0019";
	public final static String COMMON_CONTAIN_YITINGYONG = "0020";
	public final static String COMMON_VIALID_FLAG = "0021";
	public final static String COMMON_APPROVE_STATUS = "0022";
	public final static String COMMON_MESSAGE_ALREADY_UPDATED = "0025";
	public final static String COMMON_MESSAGE_ZUOFEI = "0074";
	public final static String COMMON_HUIFU_DEFEATED = "0070";
	public final static String COMMON_MESSAGE_LOSE= "0023";
	public final static String COMMON_MESSAGE_CHENGGONG= "0071";
	public final static String COMMON_MESSAGE_REPETITION= "0073";
	public final static String COMMON_MESSAGE_ONE= "0075";
	public final static String COMMON_MESSAGE_TWO= "0076"; 
	public final static String COMMON_MESSAGE_THREE= "0032";
	public final static String COMMON_MESSAGE_WSH= "0033";
	public final static String COMMON_MESSAGE_CYSZERO= "0077";
	public final static String COMMON_USER_ALE= "0076";
	public final static String COMMON_TELISNULL= "0078";
	public final static String COMMON_INSERT_FAIL= "0079";
	public final static String COMMON_QRCODE_FAIL= "0080";
	public final static String COMMON_SCANCODE_FAIL= "0081";
	public final static String COMMON_CHECK_FLAG= "0082";
	public final static String COMMON_SALE_DELIVERY= "0083";
	public final static String COMMON_SALE_RETURN= "0084";
	public final static String COMMON_NO_USER= "0085";
	public final static String COMMON_NO_CHECK = "0100";
	public final static String COMMON_NO_PRIZE = "0110";
	public final static String COMMON_NO_ONE = "0120";
	public final static String COMMON_YES_CHECK = "0200";
	public final static String COMMON_NO = "9999";
	// TODO 缺少分库键
	public final static String COMMON_MISSING_SHARDING_KEY = "0999";

	static {
		map.put(TRUE, "操作成功！");
		map.put(COMMON_FAIL, "操作失败，请重试操作或联系客服人员！");
		map.put(COMMON_MISSING_PARAMS, "您输入的数据不完整！");
		map.put(COMMON_WRONG_PARAMS, "您输入的数据有误！");
		map.put(COMMON_DB_ERRORS, "操作失败，请重试或联系客服人员！");
		map.put(COMMON_TOO_MANY_RESULTS, "返回结果超过一行记录！");
		map.put(COMMON_ERROR_FORMAT, "用户不存在！");
		map.put(COMMON_ERROR_FORWARD, "错误页面跳转提示！");
		map.put(COMMON_NO_DATA, "数据不存在！");
		map.put(COMMON_PERMISSION_DENIED, "没有权限！");
		map.put(COMMON_CONTAIN_SENSITIVE, "含有敏感信息！");
		map.put(COMMON_CONTAIN_ZUOFEI, "单据已作废,不用重复作废！");
		map.put(COMMON_CONTAIN_SHENHE, "单据已审核,无法作废！");
		map.put(COMMON_CONTAIN_WEISHENHE, "单据未审核！");
		map.put(COMMON_NO_ERRORS,"按钮编号重复");
		map.put(COMMON_DICTIONARY,"字典编号重复");
		map.put(COMMON_CONTAIN_DELETE,"明细删除成功");
		map.put(COMMON_CONTAIN_DELETEI,"明细删除失败");
		map.put(COMMON_CONTAIN_YIQIYONG,"单据已启用");
		map.put(COMMON_CONTAIN_WEIQIYONG,"单据未启用");
		map.put(COMMON_CONTAIN_YITINGYONG,"单据已停用");
		map.put(COMMON_MESSAGE_ZUOFEI,"作废失败 该信息已是作废信息");
		map.put(COMMON_HUIFU_DEFEATED,"该信息已是回复信息");
		map.put(COMMON_MESSAGE_LOSE,"操作失败");
		map.put(COMMON_MESSAGE_CHENGGONG,"操作成功");
		map.put(COMMON_MESSAGE_REPETITION,"以发送信息不能作废");
		map.put(COMMON_MESSAGE_ONE,"作废信息不能发送");
		map.put(COMMON_MESSAGE_TWO,"发送完成信息不能重复发送");
		map.put(COMMON_MESSAGE_THREE,"以发送信息不能重复发送");
		map.put(COMMON_VIALID_FLAG,"单据已作废,不能提交");
		map.put(COMMON_CHECK_FLAG,"单据已审核,不能提交");
		map.put(COMMON_APPROVE_STATUS,"单据已提交,不用重复提交");
		map.put(COMMON_MESSAGE_ALREADY_UPDATED, "该单据信息已经被更新，请重新打开");
		map.put(COMMON_MESSAGE_WSH, "该优惠券没有应用到门店，不能审核");
		map.put(COMMON_MESSAGE_CYSZERO, "差异数为0，不能生成损益单");
		map.put(COMMON_USER_ALE, " 请选择要保存的数据");
		map.put(COMMON_TELISNULL, " 输入的手机号不能为空");
		map.put(COMMON_INSERT_FAIL, "有数据未保存成功，请重试");
		map.put(COMMON_QRCODE_FAIL, "二维码过期");
		map.put(COMMON_SCANCODE_FAIL, "您已经扫过此二维码，不能重复扫码");
		
		map.put(COMMON_MISSING_SHARDING_KEY, "缺少数据库分库键！");
		map.put(COMMON_SALE_DELIVERY, "该订单不是未发货状态，不能发货！");
		map.put(COMMON_SALE_RETURN, "TOKEN获取错误，请联系管理员！");
		map.put(COMMON_NO_USER, "用户名或密码不正确！");
		
		map.put(COMMON_NO_CHECK, "请从正规渠道进入！");
		map.put(COMMON_NO_PRIZE, "无剩余奖品！");
		map.put(COMMON_NO_ONE, "只能抽一次");
		map.put(COMMON_YES_CHECK, "恭喜中奖！");
		
		map.put(COMMON_NO, "手机号已被使用");
	}

	public static String getValueWithKey(String key) {
		return map.get(key);
	}

}
