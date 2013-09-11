package db;
import dbmodel.MyDataSource;
import java.sql.*;
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
         String sql="insert into sms.sms_send(title,message_body,receiverphone,period,status)values(?,?,?,?,?)";
         try{
             statement=connection.prepareStatement(sql);
             statement.setString(1,title);
             statement.setString(2,body);
             statement.setString(3,receiver);
             statement.setString(4,period);
             statement.setString(5,"未发送");
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
                 if(l.getDays()<7&&l.getDays()>7*3/4){
                     //插入数据，将信息内容置为未发送
                     insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime(),l.getPhone(),l.getRefuelcycle());
                 }else{
                    System.out.println("不发送");
                 }
            }
            if(l.getRefuelcycle().equals("三个月")){
               System.out.print("进入三个月");
               if(l.getDays()<90&&l.getDays()>90*3/4){
                   //插入数据，将信息内容置为未发送
                   insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime(),l.getPhone(),l.getRefuelcycle());
               }else{
                   System.out.println("不发送");
               }
           }
            if(l.getRefuelcycle().equals("一天")){
               System.out.print("进入一天");
               if(l.getDays()>1){
                   //插入数据，将信息内容置为未发送
                   insetToSend("您的设备需要润滑了","名称:"+l.getName()+",润滑周期为:"+l.getRefuelcycle()+",上一次润滑的时间为:"+l.getLubricatetime(),l.getPhone(),l.getRefuelcycle());
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
     public static void main(String args[]){
         insertToDb d=new insertToDb();
           d.executeJob();
     }
}
