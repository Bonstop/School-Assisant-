package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.WxService;

public class WxServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//��½��֤:�������ɹ�ԭ������һ����ֵ//
		if(WxService.check(signature , timestamp , nonce)){
			//System.out.println("����ɹ�");
			PrintWriter out = response.getWriter();
			out.print(echostr);
			out.flush();
			out.close();
		}
		
		else{
			System.out.println("����ʧ��");
		}
	}
	/*
	 * doPost����������Ϣ���¼�������
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");//���͵����ݺͻظ�������������ܺ������ģ���Ҫת��//
		response.setCharacterEncoding("utf8");
		//������Ϣ���¼�����
		Map<String , String> requestMap = WxService.parseRequest(request.getInputStream());
		System.out.println(requestMap);
		//׼���ظ������ݰ�//
		String respXml = WxService.getResponse(requestMap);
		System.out.println(respXml);
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.flush();
		out.close();
	}

}
