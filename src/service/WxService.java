package service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class WxService {
	//微信服务的验证签名:
	private static final String TOKEN = "bonstop";//这里的Token的数值必须和微信开发的配置方式一致//
	private static String sha1(String s){ // 加密方法//
		try{
			//获取一个加密的对象//
			MessageDigest md = MessageDigest.getInstance("sha1");
			//加密//
			byte[] digest = md.digest(s.getBytes());
			char[] chars = {'0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f'};
			StringBuilder sb = new StringBuilder();
			//处理加密的结果//
			for(byte b : digest)
			{
				sb.append(chars[(b >> 4) & 15]);//取其高四位//
				sb.append(chars[b & 15]);//取其第四位//
			}
			return sb.toString();
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}
	public static boolean check(String signature , String timestamp , String nonce){
		 //1）将token、timestamp、nonce三个参数进行字典序排序 
			String[] str = new String[] {TOKEN , timestamp , nonce};
			Arrays.sort(str);
		 //2）将三个参数字符串拼接成一个字符串进行sha1加密 
			String s = str[0] + str[1] + str[2];
			String mysig = sha1(s);
			System.out.println(mysig);
			System.out.println(signature);
		 //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return mysig.equalsIgnoreCase(signature);
	}
	
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String , String> map = new HashMap();
		SAXReader reader = new SAXReader();
		try{
			//读取输入流,获取文档对象//
			Document document = reader.read(is);
			//根据文档对象获取根节点//
			Element root = document.getRootElement();
			//获取根节点下的所有子节点//
			List<Element> elements = root.elements();
			for(Element e : elements){
				map.put(e.getName(), e.getStringValue());
			}
		}
		catch(DocumentException e){
			e.printStackTrace();
		}
		return map;
	}
}
