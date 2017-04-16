package top.builbu.common.util;

public abstract class CharacterUtil {
	public static String createRandomId(int paramInt, String[] paramArrayOfString) {
		if (paramInt <= 0) {
			return null;
		}
		StringBuilder localStringBuilder = new StringBuilder("");
		int i = paramArrayOfString != null ? paramArrayOfString.length : 0;
		for (int j = 0; j < paramInt; j++) {
			int k = (int) (Math.random() * 1000000000.0D) % i;
			localStringBuilder.append(paramArrayOfString[k]);
		}
		return localStringBuilder.toString();
	}

	public static String createRandomId(int paramInt) {
		String[] arrayOfString = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
				"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		return createRandomId(paramInt, arrayOfString);
	}

	public static String createRandomNumId(int paramInt) {
		String[] arrayOfString = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		return createRandomId(paramInt, arrayOfString);
	}

	public static String bytes2HexString(byte[] paramArrayOfByte) {
		StringBuilder localStringBuilder = new StringBuilder("");
		int i = paramArrayOfByte != null ? paramArrayOfByte.length : 0;
		for (int j = 0; j < i; j++) {
			String str = Integer.toHexString(paramArrayOfByte[j] & 0xFF);
			if (str.length() == 1) {
				localStringBuilder.append("0");
			}
			localStringBuilder.append(str.toLowerCase());
		}
		return localStringBuilder.toString();
	}

	public static byte[] hexString2Bytes(String paramString) {
		int i = paramString != null ? paramString.length() : 0;
		if (i == 0) {
			return null;
		}
		if (i % 2 != 0) {
			throw new IllegalArgumentException("长度无效");
		}
		String str = "0123456789abcdef";
		int j = i / 2;
		char[] arrayOfChar = paramString.toLowerCase().toCharArray();
		byte[] arrayOfByte = new byte[j];
		for (int k = 0; k < j; k++) {
			int m = arrayOfChar[(2 * k)];
			int n = arrayOfChar[(2 * k + 1)];
			int i1 = (byte) str.indexOf(m);
			int i2 = (byte) str.indexOf(n);
			arrayOfByte[k] = ((byte) (i1 << 4 | i2));
		}
		return arrayOfByte;
	}
}
