package top.builbu.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	public static byte[] md5(String data) {
		return md5(data.getBytes());
	}

	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data));
	}

	public static boolean validateData(String src, String dest) {
		src = StringUtil.null2String(src);
		dest = StringUtil.null2String(dest);
		MD5 md5 = new MD5();
		if ((("".equals(src)) && (src.equals(dest)))
				|| (md5Hex(src).equalsIgnoreCase(dest))) {
			return true;
		}

		return (md5.getMD5ofStr(src).equalsIgnoreCase(dest));
	}
	
	public static void main(String [] args){

		  MD5Util md5 = new MD5Util();
		  String password = "test002111111";
		  String md5Str = md5.md5Hex(password);
		  System.out.println(md5Str);
		  System.out.println("4297f44b13955235245b2497399d7a93");
	}
}
