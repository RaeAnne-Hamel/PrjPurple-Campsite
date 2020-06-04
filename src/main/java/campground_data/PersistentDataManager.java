package campground_data;

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersistentDataManager {

    /* Manages the saving and loading of the system */


    /**
     * loads a single file,
     * @param sFile - File name.
     * @param eType - references the type of file that will be loaded
     * @param bl - references the booking ledger that the file will be loaded into.
     * @param <E> - Generic, as to work with all savable objects.
     * @return
     */
    public static <E> ArrayList<E> load(String sFile, LoadType eType, BookingsLedger bl)
    {
        /* Hold on to the locally saved files */
        ArrayList<E> aLoaded = new ArrayList<E>();

        /* Check if a file exists */
        File obFile = new File(sFile);
        if(!obFile.exists()||!obFile.isFile())
        {
            System.out.println("Error");
        }

        /* Begins loading the file */
        try(Scanner obIn = new Scanner(obFile))
        {
            /* read each line of the file and break each line into component parts */
            while (obIn.hasNext())
            {
                /* Loads a single record with the loadRecord method */
                String[] sFields  = parseCSVLine(obIn.nextLine());
                Object record = loadRecord(eType, bl, sFields);

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
    public static Object loadRecord(LoadType eType, BookingsLedger bl, Object... arg)
    {
        Object record = null;

        /* Create the objects based on the eType */
        switch(eType)
        {
            case Customer:
                //record = new Customer();
                if (record instanceof Persistent)
                    ((Persistent) record).load(bl, arg);
                break;
            case Lot:
                record = new Lot();
                if (record instanceof Persistent)
                    ((Persistent) record).load(bl, arg);
                break;
            case Manager:
                record = new Manager();
                if (record instanceof Persistent)
                    ((Persistent) record).load(bl, arg);
                break;
            case Reservation:
                record = new Reservation();
                if (record instanceof Persistent)
                    ((Persistent) record).load(bl, arg);
                break;
            case Transaction:
                record = new Transaction();
                if (record instanceof Persistent)
                    ((Persistent) record).load(bl, arg);
                break;
            default:
                /* Send error if invalid Enum occurs */
                System.out.print("Error Loading Files!");
        }

        return record;
    }


    public static <E> void save(ArrayList<E> aList, String sFile){

        try (FileWriter fileWriter = new FileWriter(sFile)) {
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
        System.out.println(bl.getManagerList().size());
        save(bl.getManagerList(),"src/files/Managers.txt");

        System.out.println(bl.getCustomerList().size());
        save(bl.getCustomerList(),"src/files/Customers.txt");

        System.out.println(bl.getAllReservations().size());
        save(bl.getAllReservations(),"src/files/Reservations.txt");

        System.out.println(bl.getLotList().size());
        save(bl.getLotList(),"src/files/Lots.txt");

        System.out.println(bl.getTransactionList().size());
        save(bl.getTransactionList(), "src/files/Transactions.txt");
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
