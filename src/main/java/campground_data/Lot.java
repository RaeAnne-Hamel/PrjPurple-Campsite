package campground_data;

import java.util.ArrayList;

public class Lot extends Persistent{


    int nLotID;
    LotType obType;
    ArrayList<Reservation> obReservationList;
    public static int StaticLotID = 0;
    String sRemovalReason;
    boolean bAvailability;
    int obReservationListLength;
    double price = 0;

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

    /* LotID, LotType, Removal Reason, bAvailability, Reservationcount */
    @Override
    public void load(BookingsLedger bl, Object... arg) {
        nLotID = Integer.parseInt((String)arg[0]);
        setLotType(LotType.valueOf((String)arg[1]));
        setRemovalReason((String)arg[2]);
        setAvailability(Boolean.parseBoolean((String)arg[3]));
        obReservationListLength = Integer.parseInt((String)arg[4]);
    }

    @Override
    public String savable() {
        String out = String.format("%d,%s,%s,%b,%d",
                getLotID(),getLotType(),getRemovalReason(),getAvailability(),obReservationListLength);
        System.out.println(out);
        return out;
    }

    /* Empty until needed */
    @Override
    public void link(BookingsLedger bl, Object... arg) {

        /* searches for Reservations with the ID's in their save data */
        ArrayList<Reservation> aReservations = new ArrayList<>();
        for(int i = 0; i < obReservationListLength; i++)
        {
            int searchForID = Integer.parseInt((String)arg[5+i]);
            for (int j = 0; j < bl.getAllReservations().size(); j++)
            {
                if (bl.getAllReservations().get(i).getReservationID() == searchForID)
                    obReservationList.add(bl.getAllReservations().get(i));
            }
        }
    }
}

