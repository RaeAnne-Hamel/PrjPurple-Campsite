package campground_data;

abstract class Persistent {
    public abstract void load(Object... arg);
    public abstract String savable();
}
