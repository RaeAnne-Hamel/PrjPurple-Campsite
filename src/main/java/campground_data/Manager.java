package campground_data;

public class Manager {

    int EmployeeID;
    int Permissions;

    public void setPermissions(int nPermissions)
    {
        this.Permissions = nPermissions;
    }

    public int getPermissions()
    {
        return Permissions;
    }

}
