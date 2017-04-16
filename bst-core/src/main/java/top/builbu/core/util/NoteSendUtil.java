package top.builbu.core.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import top.builbu.core.wechat.utils.MD5Util;

public class NoteSendUtil {

	private static String userName = "niurendaoyzm";
	private static String password = "nrd123456";
	private static String sendSmsUrl = "http://www.mxintong.cn/SmsBatch.do";
	private static String getBalanceUrl = "http:// 123.57.75.124/getBalanceSmsBatch.do";
	private static String getReceiveUrl = "http:// 123.57.75.124/getReceiveSmsBatch.do";
	
	/**
	 * 短信发送 is 选择供应商 rop 选择操作 phone 发送的手机号 context 发送的文本 文本结束必须有【】标签
	 */
	public static String noteSend(String phone, String context, int is, int rop) throws Exception {
		String result = "";// 返回值
		// rop==1 发送
		if (rop == 1) {
			// 根据发送方式不同选择供应商发送**单条单次发送
			if (is == 1 && rop == 1) {
				result = sendSms(phone, context, is, rop);
				System.out.print(result.split(",")[0]);
				// 助通 拼接发送单次
			} else if (is == 3 && rop == 1) {
				System.out.println("手机号：" + phone.substring(0, phone.length() - 1));
				System.out.println("内容：" + context.substring(0, context.length() - 1));
				System.out.println("调用接口..............");
				result = sendSms(phone.substring(0, phone.length() - 1), context.substring(0, context.length() - 1), is,
						rop);
				System.out.println("调用结束");
				System.out.print("返回值：" + result.split(",")[0]);
			}
			// rop==2 查询余额
		} else if (rop == 2) {
			if (is == 1 && rop == 2) {
				result = sendSms(null, null, is, rop);
			} else if (is == 3 && rop == 2) {
				result = sendSms(null, null, is, rop);
				System.out.print("余额：" + result);
			}
			// rop==3 状态报告提取
		} else if (rop == 3) {
			if (is == 1 && rop == 3) {
				result = sendSms(null, null, is, rop);
				System.out.print(result);
			} else if (is == 3 && rop == 3) {
				result = sendSms(null, null, is, rop);
				String[] str = result.split(";");
				for (int i = 0; i < str.length; i++) {
					String[] strs = str[i].split(",");
					System.out.println("消息ID：" + strs[0]);
					System.out.println("手机号：" + strs[1]);
					System.out.println("发送状态：" + strs[2]);
					System.out.println("发送时间：" + strs[3]);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param sendUrl
	 *            发送路径
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param phone
	 *            手机号
	 * @param text
	 *            内容
	 * @param is
	 *            选择供应商
	 * @param rop
	 *            选择具体操作
	 * @return
	 */
	public static String sendSms(String phone, String text, int is, int rop) {
		String result = "";
		HttpClient httpClient = new HttpClient();
		// 根据供应商选择提交方式
		PostMethod postMethod = null;
		httpClient.getParams().setContentCharset("utf-8");

		if (rop == 1) {// 1 发送
			postMethod = new PostMethod(sendSmsUrl);
			postMethod.addParameter("username", userName);
			postMethod.addParameter("password", MD5Util.MD5Encode(password,"UTF-8"));
			postMethod.addParameter("mobile", phone);
			postMethod.addParameter("content", text);
		} else if (rop == 2) {// 2 查询余额
			postMethod = new PostMethod(getBalanceUrl);
			postMethod.addParameter("username", userName);
			postMethod.addParameter("password",  MD5Util.MD5Encode(password,"UTF-8"));
		} else if (rop == 3) {// 3 状态报告提取
			postMethod = new PostMethod(getReceiveUrl);
			postMethod.addParameter("username", userName);
			postMethod.addParameter("password",  MD5Util.MD5Encode(password,"UTF-8"));
		}
		
		try {
			if (postMethod != null) {
				httpClient.executeMethod(postMethod);
				result = postMethod.getResponseBodyAsString().trim();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}

		return result;// getUrl(sendUrl);
	}


	public static void main(String[] args) {
		try {
			noteSend("15121118965", "你好【上海】", 1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
