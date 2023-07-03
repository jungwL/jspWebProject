package servletAPI.ex04;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl implements HttpSessionBindingListener {
	String user_id;
	String user_pwd;
	static int total_user=0;
	
	public LoginImpl() {
		
	}
	public LoginImpl(String user_id, String user_pwd) {
		this.user_id=user_id;
		this.user_pwd=user_pwd;
	}
	//세션에 저장시 접속자수를 증가시킴
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("사용자가 접속하였습니다.");
		++total_user;
	}
	// 세션에서 소멸시 접속자수를 감소시킴
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("사용자가 접속을 해제했습니다.");
		total_user--;
	}
	
}
