package hr.fer.oprpp1.hw05.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UtilTest {
	
	@Test
	public void byteToHexTest() {
		assertEquals("01ae22", Util.byteToHex(new byte[] {1, -82, 34}));
	}
	
	@Test
	public void hexToByteTest() {
		byte[] byteArray = Util.hexToByte("01aE22");
		assertEquals(byteArray.length, 3);
		assertEquals(byteArray[0], 1);
		assertEquals(byteArray[1], -82);
		assertEquals(byteArray[2], 34);
	}
	
	@Test
	public void lowercaseAndUppercaseHexToByteTest() {
		byte[] byteArray1 = Util.hexToByte("01ae22");
		byte[] byteArray2 = Util.hexToByte("01AE22");
		assertEquals(byteArray1.length, byteArray2.length);
		assertEquals(byteArray1[0], byteArray2[0]);
		assertEquals(byteArray1[1], byteArray2[1]);
		assertEquals(byteArray1[2], byteArray2[2]);
		
	}
	
	@Test
	public void oddSizedHexStringTest() {
		assertThrows(IllegalArgumentException.class, () -> Util.hexToByte("ace43"));
	}
	
	@Test
	public void invalidHexStringTest() {
		assertThrows(IllegalArgumentException.class, () -> Util.hexToByte("aahh"));
	}
}
