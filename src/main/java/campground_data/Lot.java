package campground_data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lot {

    int nLotID;
    LotType obType;
    ArrayList<Reservation> obReservationList;
    int StaticLotID;
    String sRemovalReason;
    boolean bAvailability;

    public Lot(int nLotID,
               LotType obType,
               ArrayList<Reservation> obReservationList,
               int StaticLotID,
               String sRemovalReason,
               boolean bAvailability)
    {

    }

    public String getRemovalReason()
    {
        return sRemovalReason;
    }

    /*
    Checks that the String input is within the acceptable length of between 1 and 255 characters,
    and then sets the reason for the lot being removed to the string.
     */
    public void setRemovalReason(String sReason)
    {
        switch (sReason.length())
        {
            case 0:
                System.out.printf("The reason input is too short. Please input at least 1 character\n");
                break;

            case 256:
                System.out.printf("The reason input is too long. The reason cannot be more than 256 characters\n");
                break;

            default:
                sRemovalReason = sReason;
        }
    }

    public boolean getAvailability()
    {
        return bAvailability;
    }

    public void setAvailability(boolean b)
    {
        bAvailability = b;
    }



    public int getLotID()
    {
        return nLotID;
    }

}
