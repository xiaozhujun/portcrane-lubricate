package com.botest.utils.smsutils;

import java.util.ArrayList;
import java.util.List;


public class VirtualObj {
	private static Port port;
	private static char symbol1 = 13;
	private static String strReturn = "", atCommand = "";
	private boolean flag = false;

	public VirtualObj(String comName) {
		port = new Port();
		System.out.println("正在连接" + comName + "通讯端口...");
		if (port.open(comName)) {
			System.out.println(comName + "通讯端口已经连接!");
			setMessageMode(1);
		} else {
			System.out.println(comName + "通讯端口连接失败!");
		}
	}

	/**
	 * 发送短信
	 * 
	 * @param phoneCode
	 *            目标号码
	 * @param msg
	 *            短信内容
	 * */
	public boolean sendSMS(String phoneCode, String msg) {
		if (flag == false) {
			System.out.println("***************************************");
			System.out.println("COM通讯端口未正常打开!");
			System.out.println("***************************************");
			return false;
		}
		//空格
		char symbol2 = 34;

		//ctrl~z  发送指令
		char symbol3 = 26;

		try {
			atCommand = "AT+CSMP=17,169,0,08" + String.valueOf(symbol1);
			strReturn = port.sendAT(atCommand);
			System.out.println(strReturn);
			if (strReturn.indexOf("OK", 0) != -1) {
				atCommand = "AT+CMGS=" + phoneCode + String.valueOf(symbol1);
				strReturn = port.sendAT(atCommand);
				atCommand = StringUtil.encodeHex(msg.trim())
						+ String.valueOf(symbol3) + String.valueOf(symbol1);
				strReturn = port.sendAT(atCommand);
                System.out.print(strReturn+"oooooooooooo");
				if (strReturn.indexOf("OK") != -1 && strReturn.indexOf("+CMGS")!= -1) {
					System.out.println("短信发送成功...");
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("***************************************");
			System.out.println("短信发送失败...");
			System.out.println("***************************************");
			return false;
		}finally {
            port.close();
        }
		System.out.println("***************************************");
		System.out.println("短信发送失败...");
		System.out.println("***************************************");
		return false;
	}

	// 设置消息模式 0-pdu 1-text(默认1 文本方式 )
	public boolean setMessageMode(int op) {
		try {
			atCommand = "AT+CMGF=" + String.valueOf(op)+ String.valueOf(symbol1);
			strReturn = port.sendAT(atCommand);
			if (strReturn.indexOf("OK", 0) != -1) {
				flag = true;
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取短信
	 * @param 短信存储的位置
	 * @return Message对象
	 * */
	public Message readSMS(String index) {
		Message mes = null;
		if (flag == false) {
			System.out.println("***************************************");
			System.out.println("System Message:  COM通讯端口未正常打开!");
			System.out.println("***************************************");
			return mes;
		}
		try {
			atCommand = "AT+CMGR=" + index;
			strReturn = port.sendAT(atCommand);
			if (strReturn.indexOf("OK") != -1) {
				mes = StringUtil.analyseSMS(strReturn, index);
				System.out.println("短信位置:" + mes.getAddID());
				System.out.println("短信状态:" + mes.getState());
				System.out.println("对方号码:" + mes.getPhone());
				System.out.println("短信内容:" + mes.getMessage());
				System.out.println("发送时间:" + mes.getTime());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mes;
	}

	// 短信存储状况
	// +CPMS:”SM”,11,25, ”SM”,11,25, ”SM”,11,25
	// SIM 卡可保存25 条短消息，现有短消息11 条
	public String getReportSMS() {
		if (flag == false) {
			System.out.println("***************************************");
			System.out.println("System Message:  COM通讯端口未正常打开!");
			System.out.println("***************************************");
			return "";
		}
		try {
			atCommand = "AT+CPMS?";
			strReturn = port.sendAT(atCommand);
			System.out.println(strReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 删除短信
	 * 
	 * @param 短信存储的位置
	 * */
	public boolean deleteSMS(String index) {
		if (flag == false) {
			System.out.println("System Message:  COM通讯端口未正常打开!");
			return false;
		}
		try {
			System.out.println("System Operate:  正在删除存储位置为" + index	+ "的短信......");
			atCommand = "AT+CMGD=" + index;
			strReturn = port.sendAT(atCommand);
			if (strReturn.indexOf("OK") != -1) {
				System.out.println("System Message:  成功删除存储位置为" + index	+ "的短信......");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}	

	/**
	 * 删除短信
	 * 
	 * @param 短信存储的位置
	 * */
	public List readAllSMS() {
		if (flag == false) {
			System.out.println("***************************************");
			System.out.println("System Message:  COM通讯端口未正常打开!");
			System.out.println("***************************************");
			return null;
		}
		List listMes = new ArrayList();
		try {
			atCommand = "AT+CMGL=\"ALL\"";
			strReturn = port.sendAT(atCommand);
			listMes = StringUtil.analyseArraySMS(strReturn);
			if (listMes != null && listMes.size() > 0) {

				System.out.println("***************************************");
				System.out.println("System Message:  读取到" + listMes.size()
						+ "条回复信息......");
				System.out.println("***************************************");
				Message mes = null;
				for (int i = 0; i < listMes.size(); i++) {
					mes = (Message) listMes.get(i);
					System.out.println("短信位置:" + mes.getAddID());
					System.out.println("短信状态:" + mes.getState());
					System.out.println("对方号码:" + mes.getPhone());
					System.out.println("短信内容:" + mes.getMessage());
					System.out.println("接收时间:" + mes.getTime());
				}
				System.out.println("***************************************");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listMes;
	}

	public static Port getPort() {
		return port;
	}

	public static void setPort(Port port) {
		VirtualObj.port = port;
	}
}
