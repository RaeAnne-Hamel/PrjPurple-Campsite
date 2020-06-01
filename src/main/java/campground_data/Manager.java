package campground_data;

public class Manager extends Persistent{


    private int Permissions;

    int EmployeeID;

    private static int StaticEmplayeeID = 0;

    public void setPermissions(int nPermissions)
    {
        this.Permissions = nPermissions;
    }


    public int getPermissions()
    {
        return Permissions;
    }

    public void setID(int ID)
    {
        this.EmployeeID = ID;
    }


    /* ID, Permissions */
    @Override
    public void load(BookingsLedger bl, Object... arg) {
        setID(Integer.parseInt((String) arg[0]));
        setPermissions(Integer.parseInt((String) arg[1]));
        StaticEmplayeeID++;

    }

    @Override
    public String savable() {
        return String.format("%d,%s",
                this.EmployeeID,this.Permissions);
    }

    /*Empty until needed */
    @Override
    public void link(BookingsLedger bl, Object... arg) { }

}