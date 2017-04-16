package top.builbu.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public abstract class MessageDigestUtil {
	public static final String ENCODING = "UTF-8";

	private static String hash(byte[] paramArrayOfByte, String paramString) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
			return CharacterUtil.bytes2HexString(localMessageDigest.digest(paramArrayOfByte));
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			Logger.getLogger(MessageDigestUtil.class).error(localNoSuchAlgorithmException);
		}
		return null;
	}

	private static String hmac(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString) {
		SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte2, paramString);
		try {
			Mac localMac = Mac.getInstance(paramString);
			localMac.init(localSecretKeySpec);
			return CharacterUtil.bytes2HexString(localMac.doFinal(paramArrayOfByte1));
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			Logger.getLogger(MessageDigestUtil.class).error(localNoSuchAlgorithmException);
			return null;
		} catch (InvalidKeyException localInvalidKeyException) {
			Logger.getLogger(MessageDigestUtil.class).error(localInvalidKeyException);
		}
		return null;
	}

	public static String md5(String paramString, boolean paramBoolean) {
		try {
			return md5(paramString.getBytes(ENCODING), paramBoolean);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String md5(String paramString1, boolean paramBoolean, String paramString2) {
		try {
			return md5(paramString1.getBytes(paramString2), paramBoolean);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String md5(byte[] paramArrayOfByte, boolean paramBoolean) {
		if (paramBoolean) {
			return hash(paramArrayOfByte, "MD5").substring(8, 24);
		}
		return hash(paramArrayOfByte, "MD5");
	}

	public static String sha1(String paramString) {
		try {
			return sha1(paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha1(String paramString1, String paramString2) {
		try {
			return sha1(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha1(byte[] paramArrayOfByte) {
		return hash(paramArrayOfByte, "SHA-1");
	}

	public static String sha256(String paramString) {
		try {
			return sha256(paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha256(String paramString1, String paramString2) {
		try {
			return sha256(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha256(byte[] paramArrayOfByte) {
		return hash(paramArrayOfByte, "SHA-256");
	}

	public static String sha512(String paramString) {
		try {
			return sha512(paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha512(String paramString1, String paramString2) {
		try {
			return sha512(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String sha512(byte[] paramArrayOfByte) {
		return hash(paramArrayOfByte, "SHA-512");
	}

	public static String hmacmd5(String paramString1, String paramString2) {
		try {
			return hmacmd5(paramString1.getBytes(ENCODING), paramString2.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacmd5(String paramString1, String paramString2, String paramString3) {
		try {
			return hmacmd5(paramString1.getBytes(paramString3), paramString2.getBytes(paramString3));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacmd5(byte[] paramArrayOfByte, String paramString) {
		try {
			return hmacmd5(paramArrayOfByte, paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacmd5(byte[] paramArrayOfByte, String paramString1, String paramString2) {
		try {
			return hmacmd5(paramArrayOfByte, paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacmd5(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
		return hmac(paramArrayOfByte1, paramArrayOfByte2, "HmacMD5");
	}

	public static String hmacsha1(String paramString1, String paramString2) {
		try {
			return hmacsha1(paramString1.getBytes(ENCODING), paramString2.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha1(String paramString1, String paramString2, String paramString3) {
		try {
			return hmacsha1(paramString1.getBytes(paramString3), paramString2.getBytes(paramString3));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha1(byte[] paramArrayOfByte, String paramString) {
		try {
			return hmacsha1(paramArrayOfByte, paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha1(byte[] paramArrayOfByte, String paramString1, String paramString2) {
		try {
			return hmacsha1(paramArrayOfByte, paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha1(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
		return hmac(paramArrayOfByte1, paramArrayOfByte2, "HmacSHA1");
	}

	public static String hmacsha256(String paramString1, String paramString2) {
		try {
			return hmacsha256(paramString1.getBytes(ENCODING), paramString2.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha256(String paramString1, String paramString2, String paramString3) {
		try {
			return hmacsha256(paramString1.getBytes(paramString3), paramString2.getBytes(paramString3));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha256(byte[] paramArrayOfByte, String paramString) {
		try {
			return hmacsha256(paramArrayOfByte, paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha256(byte[] paramArrayOfByte, String paramString1, String paramString2) {
		try {
			return hmacsha256(paramArrayOfByte, paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha256(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
		return hmac(paramArrayOfByte1, paramArrayOfByte2, "HmacSHA256");
	}

	public static String hmacsha512(String paramString1, String paramString2) {
		try {
			return hmacsha512(paramString1.getBytes(ENCODING), paramString2.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha512(String paramString1, String paramString2, String paramString3) {
		try {
			return hmacsha512(paramString1.getBytes(paramString3), paramString2.getBytes(paramString3));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha512(byte[] paramArrayOfByte, String paramString) {
		try {
			return hmacsha512(paramArrayOfByte, paramString.getBytes(ENCODING));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha512(byte[] paramArrayOfByte, String paramString1, String paramString2) {
		try {
			return hmacsha512(paramArrayOfByte, paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			Logger.getLogger(MessageDigestUtil.class).error(localUnsupportedEncodingException);
		}
		return null;
	}

	public static String hmacsha512(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
		return hmac(paramArrayOfByte1, paramArrayOfByte2, "HmacSHA512");
	}
}
