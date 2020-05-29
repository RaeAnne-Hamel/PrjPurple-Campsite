package campground_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersistentDataManager {

    /**
     * Input the file name, the Load type and the data associated with the object
     * @param <E>
     * @param sFile
     * @param eType
     * @param objects
     * @return
     */
    public static <E> ArrayList<E> load(String sFile, LoadType eType)
    {
        /* Hold on to the locally saved files */
        ArrayList<E> aLoaded = new ArrayList<E>();

        File obFile = new File(sFile);
        if(!obFile.exists()||!obFile.isFile())
        {
            System.out.println("Error");
        }

        try(Scanner obIn = new Scanner(obFile))
        {

            //Want you to read each line of the file and break each line into componenet parts
            while (obIn.hasNext())
            {
                String[] sFields  = parseCSVLine(obIn.nextLine());
                Object record = loadRecord(eType, sFields);

                /* Append to ArrayList */
                aLoaded.add((E) record);
            }
        }
        catch (IOException exp)
        {
            System.out.printf("%s\n", exp);
        }

        return aLoaded;
    }

    /**
     *
     * @param eType
     * @param argument
     *
     * Customer:
     * Lot:
     * Manager:
     * Reservation:
     * Transaction:
     *
     * @return
     */
    public static Object loadRecord(LoadType eType, Object... arg)
    {
        Object record = null;

        /* Create the objects based on the eType */
        switch(eType)
        {
            case Customer:
                record = new Customer();
                ((Customer) record).save();
                break;
            case Lot:
                //record = new Lot();
                break;
            case Manager:
                //record = new Manager();
                break;
            case Reservation:
                //record = new Reservation();
                break;
            case Transaction:
                //record = new Transaction();
                break;
            default:
                System.out.print("Error Loading Files!");
        }

        return record;
    }

    /**
     * Helper method for reading a string from a csv where the
     * contents could be enclosed with in strings and may contain a ","
     * @param sLine
     * @return
     */
    public static String[] parseCSVLine(String sLine)
    {
        String sPattern = ",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))";

        String[]  sFields = sLine.split(sPattern);

        for (int i = 0; i < sFields.length; i++) {
            /* Get rid of residual double quotes */
            sFields[i] = sFields[i].replace("\"", "");
        }

        return sFields;
    }


}
