package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

		// ��Ű ���� �޼���
			// ����� �̸�, ��, �����Ⱓ�� �������� ���ο� ��Ű�� ����
		public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
			Cookie cookie = new Cookie (cName, cValue);
			cookie.setPath("/"); //��μ���
			cookie.setMaxAge(cTime); // ��Ű ���� �Ⱓ
			response.addCookie(cookie);	// ��Ű ����
		}
		
		// ��Ű ������ �д� �޼���
			// ����� ��Ű �̸��� ã�� �� ���� ��ȯ�ϴ� �޼���
		public static String readCookie (HttpServletRequest request, String cName) {
			String cookieValue = "";	// ��ȯ ��
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					
					// �Ű������� ��ǲ�Ǵ� cName������ �ش��ϴ� �̸��� Value�� ���Ͻ����ش�.  
					if(cookieName.equals(cName)) {
						cookieValue = c.getValue();
					}
				}
			}
			
			return cookieValue;
		}
		
		
		
		// ��Ű ���� �޼���
			// ����� �̸��� ��Ű�� �����ϴ� �޼���
		public static void deleteCookie (HttpServletResponse response, String cName) {
			makeCookie(response, cName, "", 0);
		}
}

