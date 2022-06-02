package logon;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Test2_lib {

	public static void main(String[] args) {
		base64();
	}
	
	public static void base64() {
		String text = "1234";
		
		// 인코딩
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		String encodedTxt = new String(encodedBytes);
 		System.out.println("인코딩 전 데이터 : " + text);
 		System.out.println("인코딩 후 데이터 : " + encodedTxt);
		
		
		// 디코딩
 		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
 		byte[] decodedBytes2 = Base64.decodeBase64(encodedTxt);  //String 타입
 		
 		String decodeTxt = new String(decodedBytes);
 		String decodeTxt2 = new String(decodedBytes2);
 		
 		
 		System.out.println("=====디코딩=====");
 		System.out.println("디코딩 (byte배열) : " + decodeTxt);
 		System.out.println("디코딩 (인코딩된 String) : " + decodeTxt2);
	}

}
