package campground_data;

/* Methods that will be implemented in all persistent data */
abstract class Persistent {
    /* Loads data, takes a booking ledger to loaid into as well as an array of arguments that are assigned as the object attributes*/
    public abstract void load(BookingsLedger bl, Object... arg);

    /* A method that handles the saving of the objects */
    public abstract String savable();

    /* This method links the objects together that rely on one another */
    public abstract void link(BookingsLedger bl, Object... arg);
}
