package logon;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Test2_lib {

	public static void main(String[] args) {
		base64();
	}
	
	public static void base64() {
		String text = "1234";
		
		// ���ڵ�
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		String encodedTxt = new String(encodedBytes);
 		System.out.println("���ڵ� �� ������ : " + text);
 		System.out.println("���ڵ� �� ������ : " + encodedTxt);
		
		
		// ���ڵ�
 		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
 		byte[] decodedBytes2 = Base64.decodeBase64(encodedTxt);  //String Ÿ��
 		
 		String decodeTxt = new String(decodedBytes);
 		String decodeTxt2 = new String(decodedBytes2);
 		
 		
 		System.out.println("=====���ڵ�=====");
 		System.out.println("���ڵ� (byte�迭) : " + decodeTxt);
 		System.out.println("���ڵ� (���ڵ��� String) : " + decodeTxt2);
	}

}
