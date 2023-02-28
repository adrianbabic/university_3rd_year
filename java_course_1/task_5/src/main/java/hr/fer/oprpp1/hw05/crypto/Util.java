package hr.fer.oprpp1.hw05.crypto;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static byte[] hexToByte(String keyText) {
		if(keyText.length() % 2 == 1) {
	        throw new IllegalArgumentException("Hexadecimal String should be even-sized.");
	    }
		
		byte[] bytes = new byte[keyText.length() / 2];
	    for (int i = 0; i < keyText.length(); i += 2) {
	    	bytes[i / 2] = singleHexToByte(keyText.substring(i, i + 2));
	    }
	    return bytes;
	}
	
	public static String byteToHex(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		for(byte one: byteArray) {
			char[] hexDigits = new char[2];
		    hexDigits[0] = Character.forDigit((one >> 4) & 0xF, 16);
		    hexDigits[1] = Character.forDigit((one & 0xF), 16);
		    sb.append(new String(hexDigits));
		}
		return sb.toString().toLowerCase();
	}
	
	private static byte singleHexToByte(String hexString) {
	    int firstDigit = toDigit(hexString.charAt(0));
	    int secondDigit = toDigit(hexString.charAt(1));
	    return (byte) ((firstDigit << 4) + secondDigit);
	}

	private static int toDigit(char hexChar) {
	    int digit = Character.digit(hexChar, 16);
	    if(digit == -1) {
	        throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
	    }
	    return digit;
	} 
}
