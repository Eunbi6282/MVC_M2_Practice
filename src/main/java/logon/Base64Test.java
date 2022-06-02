package logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {

	public static void main(String[] args) {
		base64();  //���� �޼��忡�� �ڽ��� Ŭ������ �ִ� �ٸ� �޼��� ȣ��
	}
	
	public static void base64() {
		String text = "1234"; // ���ڵ��� �����͸� ������ ���
							// ���ڵ�: ��ȣȭ
		
		// ���ڵ� �� �ؽ�Ʈ�� ����Ʈ�迭�� ����
		byte[] targetBytes = text.getBytes();
		
		///// ���ڵ�
		Encoder encoder = Base64.getEncoder();
		
		byte[] encoderByte = encoder.encode(targetBytes);
		String encoderTxt = new String (encoderByte);  // ��ȣȭ�� �����͸� String �������� ����
		
		System.out.println("=====���ڵ�=====");
		System.out.println("���ڵ� �� ������ : " + text);
		System.out.println("���ڵ� �� ������ : " + encoderTxt);
		
		System.out.println("=====���ڵ�(��ȣȭ)=====");
		
		///// ���ڵ�	
			// Decorder ��ü ����
		Decoder decoder = Base64.getDecoder();
			//decoder�� byte�� ���� ���� �ְ� String���� ���� ���� ����
		byte[] decoderBytes = decoder.decode(encoderByte);
		byte[] decoderBytes2 = decoder.decode(encoderTxt); // ��ȣȭ�� String�� ��ȣȭ
		
		String decorderTxt = new String(decoderBytes);
		String decorderTxt2 = new String(decoderBytes2);
		
		System.out.println("��ȣȭ�� ������ : " + decorderTxt);
		System.out.println("��ȣȭ�� ������2 : " + decoderBytes2);
		
		
		
	}

}
