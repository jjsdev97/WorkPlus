package net.cafeboard.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("filename");
		System.out.println("filename: " + fileName);
		
		String savePath = "boardupload";
		
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		String sFilePath = sDownloadPath + File.separator + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType >>> " + sMimeType);
		
		if (sMimeType == null) {
			sMimeType = "application/octet-stream";
		}
		response.setContentType(sMimeType);
		
		// 한글 파일 깨짐 방지
		String sEncoding = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		System.out.println(sEncoding);
		// Content-Disposition: 처리 X
		// attachment: 해당 Content 다운
		response.setHeader("Content-Disposition", "attachment; filename: " + sEncoding);
		
		try (
			// 웹 브라우저로 출력 스트림 생성
			BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
			// sFilePath로 지정한 파일에 대한 입력 스트림 생성
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
			)
			{
				int numRead;
				while((numRead = in.read(b, 0, b.length)) != -1) {
					out2.write(b, 0, numRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

}
