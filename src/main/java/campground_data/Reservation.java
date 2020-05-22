package campground_data;


import java.awt.print.Book;
import java.util.Date;

public class Reservation<Static> {
    Lot obLot;
    Customer[] obCustomerList;
    Date obStartDate, obEndDate;
    String Status = "Active";
    public static int StaticReservationID = 0;
    int ReservationID, nCustomerCount;
    public double price;


    public Reservation(Customer[] customers, int nPeople, Date startDate, Date endDate, Lot obLot)
    {
            this.obLot = obLot;
            this.obCustomerList = customers;
            this.nCustomerCount = nPeople;
            this.obStartDate = startDate;
            this.obEndDate = endDate;
            int StaticReservationID = 0;
            double price;
            String Status = "Active";
    }


            /**
             * this method will check to make sure that the proper number of people have been set
             *
             * @param number
             * @return
             */
            public String setCustomerNumber(int number) {
                if (obLot.getLotType() == LotType.Cabin) {
                    return checkRegularType(number);
                } else if (obLot.getLotType() == LotType.ServicedIndividual) {
                    return checkRegularType(number);
                } else if (obLot.getLotType() == LotType.NonServicedIndividual) {
                    return checkRegularType(number);
                } else if (obLot.getLotType() == LotType.ServicedGroup) {
                    return checkGroupType(number);
                } else if (obLot.getLotType() == LotType.NonServicedGroup) {
                    return checkGroupType(number);
                } else if (obLot.getLotType() == LotType.DeluxeCabin) {
                    return checkGroupType(number);
                } else {
                    return "There is already the maximum number of people booked";
                }

            }


            private String checkRegularType(int number) {
                if (number <= 4) {
                    this.nCustomerCount = number;
                    return "";
                } else {
                    return "There is already the maximum number of people booked";
                }

            }

            private String checkGroupType(int number) {
                if (number <= 8) {
                    this.nCustomerCount = number;
                    return "";
                } else {
                    return "There is already the maximum number of people booked";
                }
            }


            public int getID() {
                return this.obLot.getLotID();
            }


            private double getPrice() {
                //for  now the price will always be 0
                //they can maualy input a price if they need.
                return 0.0;
            }

            /**
             * allows the person to be able to
             * set a price for the reservation.
             *
             * @param newPrice
             */
            private void setPrice(double newPrice) {
                this.price = newPrice;
            }

            /**
             * @param
             * @return
             */
            public boolean setSiteType(LotType NewLot) {
                Lot TempLot = new Lot(NewLot);
                Boolean check = checkOverlap(TempLot.getLotID(), this.obStartDate, this.obEndDate);
                if (!check) {
                    this.obLot = TempLot;
                    return true;
                } else {
                    return false;
                }
            }

            public Lot getLot() {
                return this.obLot;
            }


            public String changeDate(Date obStart, Date obEnd) {
                //if there is overlap between the sites
                if (!checkOverlap(this.obLot.getLotID(), obStart, obEnd)) {
                    this.obStartDate = obStart;
                    this.obEndDate = obEnd;
                    return "";
                } else {
                    return "These dates will not work for this reservation";
                }

            }


            private boolean checkOverlap(int nLotID, Date obStartDate, Date obEndDate) {
                boolean returnMe = BookingsLedger.checkOverlap(nLotID, obStartDate, obEndDate);
                return returnMe;
            }

            public int getReservationID() {
                /*Set the ID for the specific reservation*/
                ReservationID = StaticReservationID++;
                return this.ReservationID;

            }
        }
