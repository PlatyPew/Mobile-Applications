package ilovecode.mycustomer;

/**
 * Created by User on 11/10/2017.
 */

public class Customer {

    private int id;
    private String desc;
    private String name;
    private String notes;
    private String date;
    private String user;
    private String perm;
    //private String img;

    //I have provided a constructor so that it is easier to provide
    //code such as new Customer(0,'RICHARD LOW','90883377');
    //The command can create a Customer type object and at the same time fill the object's
    //properties to describe one customer information.
    public Customer(int inId, String inName, String notes, String date, String desc, String user,String perm){//, String img){
        this.name=inName;
        this.id = inId;
        this.notes = notes;
        this.date = date;
        this.desc = desc;
        this.user=user;
        this.perm=perm;
        //this.img = img;

    }

    public int getId() {
        return id;
    }
    public void setId(int inId) {this.id = inId;}

    //public String getImg() {return img;}
    //public void setImg(String inId) {this.img = img;}

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
    public void setName(String inName) {
        this.name = inName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return this.notes;
    }
    public void setNote(String inMobileContact) {
        this.notes = inMobileContact;
    }

    public String getUser() {
        return this.user;
    }
    public void setUser(String inMobileContact) {
        this.user = inMobileContact;
    }

    public String getPerm() {
        return this.perm;
    }
    public void setPerm(String inMobileContact) {this.perm = inMobileContact;}


}//End of Customer class


