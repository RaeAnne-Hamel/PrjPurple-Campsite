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

    public void setRemovalReason(String sReason)
    {

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
