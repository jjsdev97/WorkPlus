package et.member.action;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberverifyEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String recipientemail = request.getParameter("email"); //입력된 이메일 주소
		
		Random random = new Random();
		int verificationcode = 100000 + random.nextInt(900000); //6자리 난 수 생성
		
		//이메일로 인증번호 전송
		String senderemail = "j2sunnn@naver.com"; //보내는이 메일 주소
		String senderpassword = "wltjshta10!"; 	  //이메일 비밀번호
		
		String subject = "[workplus+] 이메일 인증 번호";
		String content = "고객님의 인증 번호는 다음과 같습니다. [ " + verificationcode + " ] ";
		
		String server = "smtp.naver.com";
		int port=465;
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", server);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", "smtp.naver.com");
			props.put("mail.smtp.ssl.protocols","TLSv1.2");
			
			  Session session = Session.getInstance(props, new Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(senderemail, senderpassword);
	                }
	            });
			
			//메일을 보내기 위한 정보를 입력하기 위해 Message객체를 생성  
			Message message = new MimeMessage(session);
			message.setHeader("content-type", "text/html;charset=euc-kr"); //보내는 메일의 내용이 한글일 경우 깨지지 않도록 content-type을 지정
			message.setFrom(new InternetAddress(senderemail));	//보내는 메일 주소 정보 설정  
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientemail));
			message.setSubject(subject);	//제목 정보 설정
			message.setText(content);
			message.setSentDate(new java.util.Date());
			
			Transport.send(message);
			
			//인증번호를 클라이언트에게 전송
			response.getWriter().write(Integer.toString(verificationcode));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return null;
	}

}
