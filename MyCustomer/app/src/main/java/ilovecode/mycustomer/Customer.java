package ilovecode.mycustomer;

/**
 * Created by User on 11/10/2017.
 */

public class Customer {

    private int id;
    private String name;
    private String mobileContact;
    //I have provided a constructor so that it is easier to provide
    //code such as new Customer(0,'RICHARD LOW','90883377');
    //The command can create a Customer type object and at the same time fill the object's
    //properties to describe one customer information.
    public Customer(int inId, String inName, String inMobileContact){
        this.name=inName;
        this.id = inId;
        this.mobileContact = inMobileContact;
    }

    public int getId() {
        return id;
    }
    public void setId(int inId) {
        this.id = inId;
    }


    public String getName() {
        return name;
    }
    public void setName(String inName) {
        this.name = inName;
    }

    public String getMobileContact() {
        return this.mobileContact;
    }
    public void setMobileContact(String inMobileContact) {
        this.mobileContact = inMobileContact;
    }


}//End of Customer class


