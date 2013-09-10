package db;

import dbmodel.MyDataSource;
import java.sql.*;
import java.util.*;
import dbmodel.lubricate;


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
    public void executeJob(){

        List<String> ll=getCondition();
        Iterator t=ll.iterator();
        while(t.hasNext()){
        String condition=(String)t.next();
        List<lubricate> list=getLubricate(condition);
        Iterator it=list.iterator();
        while(it.hasNext()){
            lubricate l=(lubricate)it.next();
            insetToSend("您的设备需要润滑了","名称:"+l.getName()+",位置:"+l.getLocation()+",类型:"+l.getType()+",润滑剂:"+l.getLube()+",润滑周期"+l.getRefuelcycle()+",清洗周期:"+l.getCleancycle(),l.getPhone(),l.getRefuelcycle());
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

}
