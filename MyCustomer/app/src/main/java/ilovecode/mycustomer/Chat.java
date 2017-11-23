package ilovecode.mycustomer;

/**
 * Created by chuny on 11/22/2017.
 */
/*
private static final String DB_CREATE3 = "CREATE TABLE "+ TABLE3+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +//chat id
            ", " + COLUMN_ID1 + " INTEGER " +//NOTE ID
            ", " + COLUMN_COMMENT + " TEXT " +//COMMENT
            ", " + COLUMN_CREATOR + " TEXT " +//USER
            ", " + COLUMN_NOTE + " INTEGER " +//NOTE NAME
            ", " + COLUMN_DATE + " TEXT )";//DATE
            */
public class Chat {
    private int id;
    private int id1;
    private String comment;
    private String creator;
    private String notes;
    private String time;
    //private String img;

    //I have provided a constructor so that it is easier to provide
    //code such as new Customer(0,'RICHARD LOW','90883377');
    //The command can create a Customer type object and at the same time fill the object's
    //properties to describe one customer information.
    public Chat(int inId, int id1, String comment, String notes, String creator, String time){//, String img){
        this.id1=id1;
        this.id = inId;
        this.notes = notes;
        this.comment = comment;
        this.creator = creator;
        this.time = time;


    }

    public int getId() {
        return id;
    }
    public void setId(int inId) {this.id = inId;}

    public int getId1() {
        return id1;
    }
    public void setId1(int inId) {this.id1 = inId;}

    public String getComment() {
        return comment;
    }
    public void setComment(String desc) {
        this.comment = desc;
    }

    public String getCreator() {
        return creator;
    }
    public void setCreator(String inName) {
        this.creator = inName;
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




}
