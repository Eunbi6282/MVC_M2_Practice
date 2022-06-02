package logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {

	public static void main(String[] args) {
		base64();  //메인 메서드에서 자신의 클래스에 있는 다른 메서드 호출
	}
	
	public static void base64() {
		String text = "1234"; // 인코딩할 데이터를 변수에 등록
							// 인코딩: 암호화
		
		// 인코딩 전 텍스트를 바이트배열에 저장
		byte[] targetBytes = text.getBytes();
		
		///// 인코딩
		Encoder encoder = Base64.getEncoder();
		
		byte[] encoderByte = encoder.encode(targetBytes);
		String encoderTxt = new String (encoderByte);  // 암호화된 데이터를 String 형식으로 적용
		
		System.out.println("=====인코딩=====");
		System.out.println("인코딩 전 데이터 : " + text);
		System.out.println("인코딩 후 데이터 : " + encoderTxt);
		
		System.out.println("=====디코딩(복호화)=====");
		
		///// 디코딩	
			// Decorder 객체 생성
		Decoder decoder = Base64.getDecoder();
			//decoder에 byte를 넣을 수도 있고 String으로 넣을 수도 있음
		byte[] decoderBytes = decoder.decode(encoderByte);
		byte[] decoderBytes2 = decoder.decode(encoderTxt); // 암호화된 String을 복호화
		
		String decorderTxt = new String(decoderBytes);
		String decorderTxt2 = new String(decoderBytes2);
		
		System.out.println("복호화된 데이터 : " + decorderTxt);
		System.out.println("복호화된 데이터2 : " + decoderBytes2);
		
		
		
	}

}
