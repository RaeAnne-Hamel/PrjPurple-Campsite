package campground_data;

import org.json.simple.JSONObject;
import java.io.Serializable;
import javax.validation.constraints.*;


/***
 * Java Bean standard is a class that is Serializable, with all private attributes,  and a NO PARAMETER Constructor
 */
public class Guest  implements Serializable {

    public Guest() {
    }

/*
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("guestID",this.guestID);
        jsonObject.put("firstName",this.firstName);
        return jsonObject.toString();
    }

    @PositiveOrZero(message = "Guest ID must be postive number or zero")
    private int guestID;
    public int getGuestID() {return guestID;}
    public void setGuestID(int guestID) {this.guestID = guestID;}

    @NotEmpty(message = "First name can not be empty" )
    @Size(max = 25, message="First name can have at most 25 characters")
    private String firstName;
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

 */
}
