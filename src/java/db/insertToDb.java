package db;
import com.botest.utils.smsutils.SMS;
import com.sun.imageio.plugins.common.LZWStringTable;
import dbmodel.MyDataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import dbmodel.lubricate;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 13-9-7
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class insertToDb {
    private  MyDataSource ds=new MyDataSource();
    Connection  connection=ds.getConnection();
    PreparedStatement statement = null;
    ResultSet rs=null;
    public List<lubricate> getLubricate(String  condition){
        String sql="select d.phone,l.* from lubricate l,devicelubricate d where l.number=d.device_num and l.refuelcycle=? group by l.name";
        List<lubricate> list=new ArrayList<lubricate>();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,condition);
            rs=statement.executeQuery();
            while(rs.next()){
                lubricate l=new lubricate();
                l.setPhone(rs.getString(1));
                l.setCleancycle(rs.getString(4));
                l.setLocation(rs.getString(5));
                l.setLube(rs.getString(6));
                l.setName(rs.getString(7));
                l.setNumber(rs.getString(8));
                l.setRefuelcycle(rs.getString(9));
                l.setType(rs.getString(10));
                list.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    // select d.phone,l.* from lubricate l,devicelubricate d where l.number=d.device_num and l.number=? group by l.name
    public List<lubricate> getLubricateByNum(String  number){
        String sql="select d.phone,l.* from lubricate l,devicelubricate d where l.number=d.device_num and l.number=? group by l.name";
        List<lubricate> list=new ArrayList<lubricate>();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,number);
            rs=statement.executeQuery();
            while(rs.next()){
                lubricate l=new lubricate();
                l.setPhone(rs.getString(1));
                l.setCleancycle(rs.getString(4));
                l.setLocation(rs.getString(5));
                l.setLube(rs.getString(6));
                l.setName(rs.getString(7));
                l.setNumber(rs.getString(8));
                l.setRefuelcycle(rs.getString(9));
                l.setType(rs.getString(10));
                list.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public  void insetToSend(String title,String body,String receiver,String period){
        String sql="insert into sms.sms_send(title,message_body,receiverphone,period,status,createtime)values(?,?,?,?,?,?)";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,body);
            statement.setString(3,receiver);
            statement.setString(4,period);
            statement.setString(5,"未发送");
            statement.setDate(6,new java.sql.Date(new java.util.Date().getTime()));
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<String> getCondition(){
        String sql="select Refuelcycle from lubricate group by Refuelcycle";
        List<String> list=new ArrayList<String>();
        try{
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                String condition=rs.getString(1);
                list.add(condition);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void executeJob(){         //执行自动润滑的任务
        List<lubricate> ll=getlubricateDays();
        Iterator t=ll.iterator();
       /* while(t.hasNext()){
        String condition=(String)t.next();
        List<lubricate> list=getLubricate(condition);
        Iterator it=list.iterator();
        while(it.hasNext()){
            lubricate l=(lubricate)it.next();
            insetToSend("您的设备需要润滑了","名称:"+l.getName()+",位置:"+l.getLocation()+",类型:"+l.getType()+",润滑剂:"+l.getLube()+",润滑周期"+l.getRefuelcycle()+",清洗周期:"+l.getCleancycle(),l.getPhone(),l.getRefuelcycle());
        }
        }*/
        while(t.hasNext()){
            lubricate l=(lubricate)t.next();
            //System.out.print(l.getDays()+"------"+l.getName()+"-------"+l.getRefuelcycle());
            if(l.getRefuelcycle().equals("一周")){
                if(l.getDays()>7*3/4){
                    //插入数据，将信息内容置为未发送
                    insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离现在有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                }else{
                    System.out.println("不发送");
                }
            }
            if(l.getRefuelcycle().equals("三个月")){
                System.out.print("进入三个月");
                if(l.getDays()>90*3/4){
                    //插入数据，将信息内容置为未发送
                    insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离现在有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                }else{
                    System.out.println("不发送");
                }
            }
            if(l.getRefuelcycle().equals("一天")){
                System.out.print("进入一天");
                if(l.getDays()>1){
                    //插入数据，将信息内容置为未发送
                    insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期为:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离现在有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                }else{
                    System.out.println("不发送");
                }
            }
            if(l.getRefuelcycle().equals("按需")){
                System.out.println("进入按需");
            }
        }

    }
    public void executeJobByHand(){           //实现手动检测
        List<String> l3=getInspectResult();
        Iterator t1=l3.iterator();
        while(t1.hasNext()){
            String num=(String)t1.next();
            List<lubricate> l2=getLubricateByNum(num);
            Iterator t2=l2.iterator();
            while(t2.hasNext()){
                lubricate l1=(lubricate)t2.next();
                insetToSend("您的设备需要润滑了","名称:"+l1.getName()+",位置:"+l1.getLocation()+",类型:"+l1.getType()+",润滑剂:"+l1.getLube()+",润滑周期:"+l1.getRefuelcycle()+",清洗周期:"+l1.getCleancycle(),l1.getPhone(),l1.getRefuelcycle());
            }

        }
    }
    public List<String> getInspectResult(){         //查出所有异常的
        String  sql="select d.numbers,itr.dnumber_id from inspect3.inspect_item_record itr,inspect3.device d where itr.dnumber_id=d.id and itr.ivalue_id=2  group by itr.dnumber_id";
        String dnumber=null;
        List<String> list=new ArrayList<String>();
        try{
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                dnumber=rs.getString(1);
                list.add(dnumber);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<lubricate> getlubricateDays(){       //有devnum关联求出当前日期与润滑时间的相隔的天数
        String sql="select datediff(now(),lr.lubricatetime),l.name,l.refuelcycle,d.phone,lr.lubricatetime from lubricate_record lr,lubricate l,devicelubricate d where l.number=lr.devnum and lr.devnum=d.device_num";
        List<lubricate> list=new ArrayList<lubricate>();
        try{
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                lubricate l=new lubricate();
                l.setDays(rs.getInt(1));
                l.setName(rs.getString(2));
                l.setRefuelcycle(rs.getString(3));
                l.setPhone(rs.getString(4));
                l.setLubricatetime(rs.getDate(5));
                list.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<lubricate> getTitleAndPhone(){                       //通过status和周期得标题和电话
        String sql="select s.title,s.receiverphone from sms.sms_send s where status=? and period=? group by s.receiverphone";
        List<lubricate> list=new ArrayList<lubricate>();
        try{
            List<String> l1=getPeriod();
            Iterator it=l1.iterator();
            while(it.hasNext()){
                String p=(String)it.next();
                statement=connection.prepareStatement(sql);
                statement.setString(1,"未发送");
                statement.setString(2,p);

                rs=statement.executeQuery();
                while(rs.next()){
                    lubricate l=new lubricate();
                    l.setTitle(rs.getString(1));
                    l.setPhone(rs.getString(2));
                    list.add(l);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<lubricate> getMsg(){                                        //通过状态和周期得到id和具体的润滑项
        String sql="select s.id,s.message_body from sms.sms_send s where status=? and period=?";
        List<lubricate> list=new ArrayList<lubricate>();
        try{

            List<String> l1=getPeriod();
            Iterator it=l1.iterator();
            while(it.hasNext()){
                String p=(String)it.next();
                statement=connection.prepareStatement(sql);
                statement.setString(1,"未发送");
                statement.setString(2,p);

                rs=statement.executeQuery();
                while(rs.next()){
                    lubricate l=new lubricate();
                    l.setId(rs.getInt(1));
                    l.setMsgcontent(rs.getString(2));
                    list.add(l);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public void updateSms(int id){                                      //若发送成功，则将status置为已发送
        String sql="update sms.sms_send set status=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,"已发送");
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void sendSms(){                                            //发送短信
        List<lubricate> tlist=getTitleAndPhone();
        List<lubricate> list=getMsg();
        Iterator it=list.iterator();
        Iterator it1=tlist.iterator();
        String title=null;
        String msgcontent="具体的需要润滑的项为:";
        String phone=null;
        int id=0;
        String sendcontent="您好!您的设备需要润滑了";
        lubricate l=null;
        boolean f=false;
        while(it1.hasNext()){
            l=(lubricate)it1.next();
            phone=l.getPhone();

            while(it.hasNext()){
                l=(lubricate)it.next();
                msgcontent+=l.getMsgcontent()+".";
                id=l.getId();
            }
        }
        if(title!=""&&msgcontent!=""&&phone!=""){
            sendcontent+=","+msgcontent;
            SMS s=new SMS();
            System.out.print(sendcontent.length()+"长度");
            for(int i=0;i<=sendcontent.length();i+=69){                //一条短信长度为69
                System.out.println(i+"之前");
                if((i+69)<sendcontent.length()){
                    f=s.sendSms("COM4", phone, sendcontent.substring(i,i+69));
                    System.out.println(sendcontent.substring(i,i+69));
                }else{
                    f=s.sendSms("COM4", phone, sendcontent.substring(i,sendcontent.length()));

                }
            }
            if(f){
                updateSms(id);
                insertToSent("您好!您的设备需要润滑了",msgcontent,phone);
            }else{
                System.out.print("短信发送不成功");
            }
        }
    }
    public List<String> getPeriod(){                              //得到要发短信的周期
        String sql="select s.period from sms.sms_send s group by s.period";
        List<String> list=new ArrayList<String>();
        try{
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                String s=rs.getString(1);
                list.add(s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
        public void insertToSent(String title,String msgbody,String phone){
            String sql="insert into sms.sms_sent(title,createtime,message_body,receiverphone)values(?,?,?,?)";
            try{
                statement=connection.prepareStatement(sql);
                statement.setString(1,title);
                statement.setDate(2,new java.sql.Date(new java.util.Date().getTime()));
                statement.setString(3,msgbody);
                statement.setString(4,phone);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    public static void main(String args[]){
        insertToDb d=new insertToDb();
          /* d.executeJob();*/
        d.sendSms();
    }
}
