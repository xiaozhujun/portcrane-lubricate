package dbmodel;

import com.sun.jna.StringArray;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 13-9-7
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
public class lubricate {
    private String name;

    private String  number;

    private String  type;

    private String location;

    private String cleancycle;

    private String lube;

    private String refuelcycle;

    private String phone;

    private int days;

    private Date lubricatetime;

    private String title;

    private String msgcontent;

    private int id;

    private int remindtime;

    private String difftime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCleancycle() {
        return cleancycle;
    }

    public void setCleancycle(String cleancycle) {
        this.cleancycle = cleancycle;
    }

    public String getLube() {
        return lube;
    }

    public void setLube(String lube) {
        this.lube = lube;
    }

    public String getRefuelcycle() {
        return refuelcycle;
    }

    public void setRefuelcycle(String refuelcycle) {
        this.refuelcycle = refuelcycle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getLubricatetime() {
        return lubricatetime;
    }

    public void setLubricatetime(Date lubricatetime) {
        this.lubricatetime = lubricatetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemindtime() {
        return remindtime;
    }

    public void setRemindtime(int remindtime) {
        this.remindtime = remindtime;
    }

    public String getDifftime() {
        return difftime;
    }

    public void setDifftime(String difftime) {
        this.difftime = difftime;
    }
}
