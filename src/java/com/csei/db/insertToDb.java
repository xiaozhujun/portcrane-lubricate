package com.csei.db;

import com.sun.jna.StringArray;
import model.MyDataSource;
import org.apache.commons.beanutils.ResultSetDynaClass;
import java.sql.*;
/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 13-9-7
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class insertToDb {
    private MyDataSource ds=new MyDataSource();
    Connection  connection=ds.getConnection();
    PreparedStatement statement = null;
    ResultSet rs=null;
   



}
