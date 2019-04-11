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
		//登陆验证:如果接入成功原样返回一个数值//
		if(WxService.check(signature , timestamp , nonce)){
			//System.out.println("接入成功");
			PrintWriter out = response.getWriter();
			out.print(echostr);
			out.flush();
			out.close();
		}
		
		else{
			System.out.println("接入失败");
		}
	}
	/*
	 * doPost用来接收消息和事件的推送
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理消息和事件推送
		Map<String , String> requestMap = WxService.parseRequest(request.getInputStream());
		System.out.println(requestMap);
	}

}
