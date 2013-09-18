package com.botest.utils.smsutils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 使用VirtualObj 的readSMS(String index)的方法时，使用该方法解析MODEM返回的字符串
	 * 根据MODEM返回的字符串，解析成一个Message对象
	 * */
	public static Message analyseSMS(String str,String index) {
		Message mes = new Message();
		String mesContent;

		String[] s = str.split("\"");

		int len = s.length;
		mes.setAddID(index);
		mesContent = s[len - 1];
		if(mesContent.indexOf("OK")!=-1){
			mesContent = mesContent.substring(0, mesContent.indexOf("OK"));
		}
		mesContent = mesContent.trim();
		mes.setMessage(mesContent);
		// 短信有中文时使用
		// mes.setMessage(Unicode2GBK(analyseStr(mesContent)));
		mes.setTime(s[len - 2]);
		if (s[1].equals("REC READ")) {
			mes.setState("已读");
		} else {
			mes.setState("未读");
		}

		mes.setPhone(s[3]);

		return mes;
	}
	/**
	 * 使用VirtualObj 的readAllSMS(String index)方法时，通过该方法解析MODEM返回来的字符串
	 * 根据MODEM返回的字符串，解析成一个Message的集合对象
	 * */
	public static List analyseArraySMS(String str) {
		List mesList = new ArrayList();
		Message mes;
		String[] messages;
		String temp;
		String[] t;
		if(str.indexOf("CMGL: ")==-1)
			return null;
		str = str.substring(0,str.indexOf("OK")).trim();
		messages = str.split("\n");
		if(messages.length<2)
			return null;
		for(int i=1;i<messages.length;i++){
			mes = new Message();
			messages[i] = messages[i].substring(messages[i].indexOf("CMGL: ")+6);
			t = messages[i].split(",");
			if(t.length>5){
			mes.setAddID(t[0].trim());
			temp = t[1].substring(t[1].indexOf('"')+1,t[1].lastIndexOf('"')).trim();
			if(temp.equals("REC READ")){
				mes.setState("已读");
			}else{
				mes.setState("未读");
			}
			mes.setPhone(t[2].substring(t[2].indexOf('"')+1,t[2].lastIndexOf('"')).trim());
			mes.setTime(t[4].substring(t[4].indexOf('"')+1)+" "+t[5].substring(0,t[5].indexOf('"')));
			i++;
			mes.setMessage(messages[i].trim());
			mesList.add(mes);
			}
		}
		return mesList;
	}

	//将PDU编码的十六进制字符串 如“4F60597DFF01” 转换成unicode "\u4F60\u597D\uFF01"
	public static String analyseStr(String str) {
		StringBuffer sb = new StringBuffer();
		if (!(str.length() % 4 == 0))
			return str;
		for (int i = 0; i < str.length(); i++) {
			if (i == 0 || i % 4 == 0) {
				sb.append("\\u");
			}
			sb.append(str.charAt(i));
		}
		return Unicode2GBK(sb.toString());
	}

	//将unicode编码 "\u4F60\u597D\uFF01" 转换成中文 "你好！"
	public static String Unicode2GBK(String dataStr) {
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		while (index < dataStr.length()) {
			if (!"\\u".equals(dataStr.substring(index, index + 2))) {
				buffer.append(dataStr.charAt(index));
				index++;
				continue;
			}
			String charStr = "";
			charStr = dataStr.substring(index + 2, index + 6);
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(letter);
			index += 6;
		}
		return buffer.toString();
	}

	public static String GBK2Unicode(String str) {

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {

			char chr1 = (char) str.charAt(i);

			if (!isNeedConvert(chr1)) {

				result.append(chr1);

				continue;

			}

			result.append("\\u" + Integer.toHexString((int) chr1));

		}

		return result.toString();

	}

	public static boolean isNeedConvert(char para) {

		return ((para & (0x00FF)) != para);

	}

	/**
	 *  使用VirtualObj 的 sendSMS(String phoneCode, String msg)方法发送短信时,调用
	 *  该方法将其短信内容转换成十六进制
	 * */
	public static final String encodeHex(String msg) {
		byte[] bytes = null;
		try {
			bytes = msg.getBytes("GBK");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer buff = new StringBuffer(bytes.length * 4);
		String b;
		char a;
		int n = 0;
		int m = 0;
		for (int i = 0; i < bytes.length; i++) {
			b = Integer.toHexString(bytes[i]);
			if (bytes[i] > 0) {
				buff.append("00");
				buff.append(b);
				n = n + 1;
			} else {
				a = msg.charAt((i - n) / 2 + n);
				m = a;
				b = Integer.toHexString(m);
				buff.append(b.substring(0, 4));

				i = i + 1;
			}
		}
		return buff.toString();
	}
}
