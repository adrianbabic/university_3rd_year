package hr.fer.oprpp1.hw05.crypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Please enter a command: ");
			System.out.print("> ");
			if(scanner.hasNext()) {
				String uneseno = scanner.nextLine().trim();
				if(uneseno.equals("exit")) {
					System.out.println("Goodbye!");
					break;
				} 
				if(uneseno.startsWith("checksha")) {
					String[] split = uneseno.split(" ");
					if(split.length != 2) {
						System.out.println("Error: checksha command should come with a single file name!");
						continue;
					}
					checksha(split[1], scanner);
					continue;
				}
				if(uneseno.startsWith("encrypt") || uneseno.startsWith("decrypt")) {
					String[] split = uneseno.split(" ");
					if(split.length != 3) {
						System.out.println("Error: encrypt/decrypt command should come with two file names!");
						continue;
					}
					try {
						encryptFile(split[0], split[1], split[2], scanner);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					continue;
				}
			
				
				System.out.println("Entered command is not supported. \nOnly supported commands are checksha, encrypt, decrypt and exit.");
				continue;
			} else {
				System.out.println("Scanner does not have next token!");
			}
		}
		scanner.close();
	}
	 
	
	 	
 	public static void checksha(String fileName, Scanner tmpScanner) throws NoSuchAlgorithmException, IOException {
 		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String hex = null;
		try {
			hex = checksum("src/main/resources/" + fileName, md);
		} catch(FileNotFoundException ex) {
			System.out.println("Error: No such file: src/main/resources/" + fileName);
			return;
		}
		System.out.println("Please provide expected sha-256 digest for " + fileName +": ");
	 	System.out.print("> ");
		if(tmpScanner.hasNextLine()) {
			String input = tmpScanner.nextLine().trim();
	        if(hex.equals(input)) {
	        	System.out.println("Digesting completed. Digest of " + fileName +" matches expected digest.");
	        } else {
	        	System.out.println("Digesting completed. Digest of " + fileName +" does not match the expected digest.\nDigest"
	        			+ " was: " + hex);
	        }
		}
    }
 	
 	public static void encryptFile(String option, String inputFile, String outputFile, Scanner scanner) throws IOException, NoSuchPaddingException,
 		    BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
 			if(!Files.exists(Paths.get("src/main/resources/" + inputFile))) 
 				throw new IOException("There is no such input file 'src/main/resources/" + inputFile + "'.");
 			String keyText = null;
			String ivText = null;
 			System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):");
 		 	System.out.print("> ");
 			if(scanner.hasNextLine()) {
 				keyText = scanner.nextLine().trim();
 			}
 			System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
 		 	System.out.print("> ");
 			if(scanner.hasNextLine()) {
 				ivText = scanner.nextLine().trim();
 			}
			boolean encrypt = option.equals("encrypt") ? true : false;
			SecretKeySpec keySpec = new SecretKeySpec(Util.hexToByte(keyText), "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hexToByte(ivText));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
 		    
			BufferedInputStream inputStream = new BufferedInputStream(
					Files.newInputStream(Paths.get("src/main/resources/" + inputFile))
					);
			BufferedOutputStream outputStream = new BufferedOutputStream(
					Files.newOutputStream(Paths.get("src/main/resources/" + outputFile))
					);
// 		    FileInputStream inputStream = new FileInputStream("src/main/resources/" + inputFile);
// 		    FileOutputStream outputStream = new FileOutputStream("src/main/resources/" + outputFile);
 		    byte[] buffer = new byte[512];
 		    int bytesRead;
 		    while ((bytesRead = inputStream.read(buffer)) != -1) {
 		        byte[] output = cipher.update(buffer, 0, bytesRead);
 		        if (output != null) {
 		            outputStream.write(output);
 		        }
 		    }
 		    byte[] outputBytes = cipher.doFinal();
 		    if (outputBytes != null) {
 		        outputStream.write(outputBytes);
 		    }
 		    inputStream.close();
 		    outputStream.close();
 		    if(encrypt) 
 		    	System.out.print("Encrypt"); 
 		    else 
 		    	System.out.print("Decrypt");
 		    System.out.println("ion completed. Generated file " + outputFile + " based on file " + inputFile +".");
 	}
 	
    private static String checksum(String filepath, MessageDigest md) throws IOException {

        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        } 
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }
	    
}
