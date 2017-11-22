package ilovecode.mycustomer;

/**
 * Created by chuny on 11/22/2017.
 */

public class Log {
    private int id;
    private String from;
    private String to;
    private String notes;
    private String action;
    private String time;
    //private String img;

    //I have provided a constructor so that it is easier to provide
    //code such as new Customer(0,'RICHARD LOW','90883377');
    //The command can create a Customer type object and at the same time fill the object's
    //properties to describe one customer information.
    public Log(int inId, String to, String from, String notes, String action, String time){//, String img){
        this.to=to;
        this.id = inId;
        this.notes = notes;
        this.from = from;
        this.action = action;
        this.time = time;


    }

    public int getId() {
        return id;
    }
    public void setId(int inId) {this.id = inId;}

    public String getTo() {
        return to;
    }
    public void setTo(String desc) {
        this.to = desc;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String inName) {
        this.from = inName;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String date) {
        this.time = date;
    }

    public String getNote() {
        return this.notes;
    }
    public void setNote(String inMobileContact) {
        this.notes = inMobileContact;
    }

    public String getAction() {
        return this.action;
    }
    public void setAction(String inMobileContact) {
        this.action = inMobileContact;
    }


}
