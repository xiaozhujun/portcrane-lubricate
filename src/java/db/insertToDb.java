package db;
import com.botest.utils.smsutils.SMS;
import com.db.SmsSend;
import dbmodel.MyDataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;
import dbmodel.lubricate;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 13-9-7
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class insertToDb {
    private static MyDataSource ds=new MyDataSource();
    static Connection  connection=ds.getConnection();
    static PreparedStatement statement = null;
    static ResultSet rs=null;
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
        String sql="insert into sms.sms_send(title,message_body,receiverphone,period,status,createtime,version)values(?,?,?,?,?,?,?)";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,body);
            statement.setString(3,receiver);
            statement.setString(4,period);
            statement.setString(5,"未发送");
            statement.setDate(6,new java.sql.Date(new java.util.Date().getTime()));
            statement.setString(7,"0");
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
                if(l.getDays()<7){                         //l.getDays()是求出当前日期距离润滑时间的间隔，90-l.getDays()求出距离润滑周期的时间
                l.setRemindtime(7-l.getDays());
                System.out.print(l.getRemindtime()+"提醒时间");
                if(l.getRemindtime()>=1&&l.getRemindtime()<=l.getDifftime()){         //当这个距离时间在1到3天之间时开始短信提醒
                    //插入数据，将信息内容置为未发送
                    //在这时应当判断一下是否是已发送过的内容
                    insetToSend("您的设备需要润滑了",""+l.getName()+",润滑周期:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离上一次润滑有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                    updateFlag(l.getItemid());
                }else{
                    System.out.println("不发送");
                }
                }else{
                    insetToSend("您的设备需要润滑了",""+l.getName()+",润滑周期:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离上一次润滑有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                    updateFlag(l.getItemid());
                }
            }
            if(l.getRefuelcycle().equals("三个月")){
                System.out.print("进入三个月");
                if(l.getDays()<90){
                    l.setRemindtime(90-l.getDays());      //l.getDays()是求出当前日期距离润滑时间的间隔，90-l.getDays()求出距离润滑周期的时间
                if(l.getRemindtime()>=1&&l.getRemindtime()<=l.getDifftime()){         //当这个距离时间在1到8天之间时开始短信提醒
                    //插入数据，将信息内容置为未发送
                    insetToSend("您的设备需要润滑了",""+l.getName()+",润滑周期:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离上一次润滑有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                    updateFlag(l.getItemid());
                }else{
                    System.out.println("不发送");
                }
                }else{
                    insetToSend("您的设备需要润滑了",""+l.getName()+",润滑周期:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离上一次润滑有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                    updateFlag(l.getItemid());
                }
            }
            if(l.getRefuelcycle().equals("一天")){
                System.out.print("进入一天");
                if(l.getDays()>=1){
                    //插入数据，将信息内容置为未发送
                    insetToSend("您的设备需要润滑了",""+l.getName()+",润滑周期为:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime()+",离上一次的润滑有"+l.getDays()+"天",l.getPhone(),l.getRefuelcycle());
                    updateFlag(l.getItemid());
                }else{
                    System.out.println("不发" +
                            "送");
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
        String  sql="select d.numbers,itr.dnumber_id from inspectpartition.inspect_item_rec itr,inspectpartition.device d where itr.dnumber_id=d.id and itr.ivalue_id=2  group by itr.dnumber_id";
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
        String sql="select datediff(now(),lr.lubricatetime),l.name,l.refuelcycle,d.phone,lr.lubricatetime,l.remindday,l.id from lubricate_record lr,lubricate l,devicelubricate d where l.number=lr.devnum and lr.devnum=d.device_num and l.flag=?";
        List<lubricate> list=new ArrayList<lubricate>();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,"1");
            rs=statement.executeQuery();
            while(rs.next()){
                lubricate l=new lubricate();
                l.setDays(rs.getInt(1));
                l.setName(rs.getString(2));
                l.setRefuelcycle(rs.getString(3));
                l.setPhone(rs.getString(4));
                l.setLubricatetime(rs.getDate(5));
                l.setDifftime(rs.getInt(6));
                l.setItemid(rs.getInt(7));
                list.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    public List<lubricate> getTitleAndPhone(){                       //通过status和周期得标题和电话
        String sql="select s.title,s.receiverphone from sms.sms_send s where status=? and period=?  group by s.receiverphone";
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
        System.out.print("发短信");
        List<lubricate> tlist=getTitleAndPhone();
        List<lubricate> list=getMsg();
        Iterator it=list.iterator();
        Iterator it1=tlist.iterator();
        String title="";
        String msgcontent="具体的需要润滑的项为:";
        String phone="";
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
        System.out.print(title+"标题");
        System.out.print(msgcontent+"内容");
        System.out.print(phone+"电话");
        if(msgcontent!=""&&phone!=""){
            System.out.print("***********");
            sendcontent+=","+msgcontent;
            SMS s=new SMS();
            String com= SmsSend.getCom();
            System.out.print(sendcontent.length()+"长度");
            for(int i=0;i<=sendcontent.length();i+=69){                //一条短信长度为69
                System.out.println(i+"之前");
                if((i+69)<sendcontent.length()){
                    System.out.print("------------");
                    System.out.print(com);
                    System.out.print("%%%%%%%%%%%%");
                    f=s.sendSms(com, phone, sendcontent.substring(i,i+69));
                    System.out.println(sendcontent.substring(i,i+69));
                }else{
                    f=s.sendSms(com, phone, sendcontent.substring(i,sendcontent.length()));

                }
            }
            if(f){
                updateSms(id);
                //每次短信发送后，将设备的润滑时间找出来
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
            String sql="insert into sms.sms_sent(title,createtime,message_body,receiverphone,version)values(?,?,?,?,?)";
            try{
                statement=connection.prepareStatement(sql);
                statement.setString(1,title);
                statement.setDate(2,new java.sql.Date(new java.util.Date().getTime()));
                statement.setString(3,msgbody);
                statement.setString(4,phone);
                statement.setString(5,"0");
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    public void updateFlag(int id){                       //当短信发送时，根据ID号将lubricate中的flag置为"0"
        String sql="update lubricate set flag=? where id=?";
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,"0");
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateF(Long id){                       //当短信发送时，根据ID号将lubricate中的flag置为"0"
        String sql="update lubricate set flag=? where id=?";
        System.out.print("%%%%%%%%%%%%%%%%%%%%%%%%%进来了");
        int id1=id.intValue();
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,"1");
            statement.setInt(2,id1);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateF1(int id){                       //当短信发送时，根据ID号将lubricate中的flag置为"0"
        String sql="update lubricate set flag=? where id=?";
        System.out.print("updateF1");
        System.out.print(id+"**************");
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,"1");
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static String getdevid(Long id){             //根据点检记录ID来查找devnum
        String sql="select devnum from lubricate_record where id=?";
        String devid=null;
        int id1=id.intValue();
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id1);
            rs=statement.executeQuery();
            while(rs.next()){
              devid=rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return devid;
    }
     public static List<Integer> getItemId(String devnum){     //根据设备编号和flag=0来查找相应的润滑项
        String sql="select id from lubricate where number=? and flag=?";
         List<Integer> list=new ArrayList<Integer>();
         try{
               statement=connection.prepareStatement(sql);
               statement.setString(1,devnum);
               statement.setString(2,"0");
               rs=statement.executeQuery();
               while(rs.next()){
                   int l=rs.getInt(1);
                   list.add(l);
               }

         }catch (SQLException e){
             e.printStackTrace();
         }
           return list;
     }
    public static void main(String args[]){
        insertToDb d=new insertToDb();
          d.executeJob();
        d.sendSms();
    }
}
