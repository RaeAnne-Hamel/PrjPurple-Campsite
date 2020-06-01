package campground_data;

abstract class Persistent {
    public abstract void load(BookingsLedger bl, Object... arg);
    public abstract String savable();
    public abstract void link(BookingsLedger bl, Object... arg);
}
