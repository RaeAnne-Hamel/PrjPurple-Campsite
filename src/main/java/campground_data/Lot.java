package campground_data;


import java.util.ArrayList;

public class Lot {


    int nLotID;
    LotType obType;
    ArrayList<Reservation> obReservationList;
    int StaticLotID = 0;
    String sRemovalReason;
    boolean bAvailability;

    public Lot(int nLotID,
               LotType obType,
               ArrayList<Reservation> obReservationList,
               int StaticLotID,
               String sRemovalReason,
               boolean bAvailability)
    {
        this.nLotID = nLotID;
        this.obType = obType;
        this.obReservationList = obReservationList;
        this.StaticLotID = StaticLotID;
        this.sRemovalReason = sRemovalReason;
        this.bAvailability = bAvailability;
    }

    public Lot(LotType obType)
    {
        this.nLotID = nLotID;
        this.obType = obType;
        this.obReservationList = obReservationList;
        this.StaticLotID = StaticLotID;
        this.sRemovalReason = sRemovalReason;
        this.bAvailability = bAvailability;

    }
    public Lot(int nLotID)
    {
        this.nLotID = nLotID;
        this.obType = LotType.ServicedIndividual;
        this.obReservationList = obReservationList;
        this.StaticLotID = StaticLotID;
        this.sRemovalReason = sRemovalReason;
        this.bAvailability = bAvailability;

    }


    public Lot(LotType obType, boolean bAvailability) {
        this.nLotID = StaticLotID++;
        this.obType = LotType.NonServicedIndividual;
        this.obReservationList = new ArrayList<Reservation>();
        this.sRemovalReason = "";
        this.bAvailability = true;
    }

    public Lot()
    {
        this.nLotID = nLotID;
        this.obType = LotType.ServicedIndividual;
        this.obReservationList = new ArrayList<Reservation>();
        this.StaticLotID = 0;
        this.sRemovalReason = "";
        this.bAvailability = true;
    }

    public String getRemovalReason()
    {
        return sRemovalReason;
    }

    public void setRemovalOverride(String sReason)
    {
        sRemovalReason = sReason;
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

    public void setReservations(ArrayList<Reservation> obRes)
    {
        this.obReservationList = obRes;
    }

    public boolean getAvailability()
    {
        return bAvailability;
    }

    public void setAvailability(boolean b)
    {
        bAvailability = b;
    }


    public LotType getLotType()
    {
        return this.obType;
    }

    public void setLotType(LotType type)
    {
        this.obType = type;
    }


    public int getLotID()
    {
        return nLotID;
    }

    @Override
    public String toString(){
        return ("Lot ID " + this.getLotID() + " Lot Type: " + this.getLotType() + " Removal Reason: " + this.sRemovalReason + " Availability: " + this.getAvailability());
    }
}

