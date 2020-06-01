package campground_data;

public class Manager extends Persistent{


    private int Permissions;

    int EmployeeID;

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
    public void load(Object... arg) {
        setID(Integer.parseInt((String) arg[0]));
        setPermissions(Integer.parseInt((String) arg[1]));
    }

    @Override
    public String savable() {
        return String.format("%d,%s",
                this.EmployeeID,this.Permissions);
    }

}