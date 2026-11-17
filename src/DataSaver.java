import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        ArrayList<String> records = new ArrayList<String>();

        int idNumber = 1;
        boolean addAnother = true;

        // Collect user records
        while (addAnother)
        {
            System.out.println("\nEnter New Record");
            System.out.println("-------------------------");

            String firstName =
                    SafeInput.getNonZeroLenString(in, "Enter First Name");

            String lastName =
                    SafeInput.getNonZeroLenString(in, "Enter Last Name");

            String id = String.format("%06d", idNumber);

            String email =
                    SafeInput.getNonZeroLenString(in, "Enter Email");

            int birthYear =
                    SafeInput.getRangedInt(in, "Enter Year of Birth", 1000, 9999);

            // Create CSV record
            String record = firstName + ", " + lastName + ", "
                    + id + ", " + email + ", " + birthYear;

            // Add record to ArrayList
            records.add(record);

            System.out.println("\nRecord Saved:");
            System.out.println(record);

            idNumber++;

            // Ask user if they want another record
            addAnother =
                    SafeInput.getYNconfirm(in, "Would you like to enter another record?");
        }

        // Ask for output filename
        String fileName =
                SafeInput.getNonZeroLenString(in, "Enter CSV filename");

        if (!fileName.toLowerCase().endsWith(".csv"))
        {
            fileName += ".csv";
        }

        // Write records to CSV file
        try
        {
            FileWriter writer = new FileWriter("src/" + fileName);

            for (String record : records)
            {
                writer.write(record + System.lineSeparator());
            }

            writer.close();

            System.out.println("\nFile successfully saved!");
            System.out.println("File Location: src/" + fileName);
            System.out.println("Total Records: " + records.size());
        }
        catch (IOException e)
        {
            System.out.println("Error writing file: " + e.getMessage());
        }

        in.close();
    }
}
