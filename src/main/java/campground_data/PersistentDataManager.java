package campground_data;

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersistentDataManager {

    /**
     * Input the file name, the Load type and the data associated with the object
     * @param <E>
     * @param sFile
     * @param eType
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

            /* read each line of the file and break each line into component parts */
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

    /* Load a single record, called many times when a file is loaded */
    public static Object loadRecord(LoadType eType, Object... arg)
    {
        Object record = null;

        /* Create the objects based on the eType */
        switch(eType)
        {
            case Customer:
                record = new Customer();
                ((Customer) record).load(arg);
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


    public static <E> void save(ArrayList<E> aList, String sFile){

        try (FileWriter fileWriter = new FileWriter("src/files/save.txt")) {
            /* Loop through savable objects */
            for(int i = 0; i < aList.size(); i++)
            {
                if (aList.get(i) instanceof Persistent)
                    fileWriter.write(((Persistent) aList.get(i)).savable()+"\n");
            }



        }catch (Exception x){};
    }

    public static void saveAll(BookingsLedger bl)
    {
        save(bl.aManager,"src/files/Managers.txt");
        save(bl.aCustomer,"src/files/Customers.txt");
        save(BookingsLedger.aReservation,"src/files/Reservations.txt");
        save(bl.getLotList(),"src/files/Lots.txt");
        save(bl.aTransaction, "src/files/Transactions.txt");
    }

    /* Check if a directory is a file. */
    public static File isFilePresent(String sFile)
    {
        File obFile = new File(sFile);
        if(!obFile.exists()||!obFile.isFile())
        {
            return null;
        }
        return obFile;
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
