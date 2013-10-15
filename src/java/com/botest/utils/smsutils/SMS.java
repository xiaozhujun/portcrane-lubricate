package com.botest.utils.smsutils;

public class SMS {	
	public boolean sendSms(String port,String phone,String msg){
		boolean f=true;
		VirtualObj v = new VirtualObj(port);	
		v.sendSMS(phone,msg);
		//v.readAllSMS();
       return f;
	}
	public static void main(String[] args) throws Exception{
		SMS s=new SMS();
        /*String s1="您的设备需要润滑了,null名称:变幅、起升减速箱,润滑周期一周,上一次润滑的时间为:201" +
                "3-09-05,离现在有12天.名称:行走减速箱,润滑周期一周,上一次润滑的时间为:2013-09-05" +
                "离现在有12天.名称:旋转行星减速箱,润滑周期一周,上一次润滑的时间为:2013-09-05,离现" +
                "在有12天.名称:起升、变幅及行走液压推杆油泵,润滑周期一周,上一次润滑的时间为:2013-" +
                "09-05,离现在有12天.名称:行走制动器杠杆铰点,润滑周期一周,上一次润滑的时间为:2013-" +
                "09-05,离现在有12天.名称:超负荷限制器杠杆,润滑周期为:一天,上一次润滑的时间为:2013" +
                "-09-05,离现在有12天.内容";*/
		 //s.SmsSend("COM4","13121829903",s1.substring(0,135));
       //s.SmsSend("COM4","13121829903",s1.substring(136,270));
	}

}
