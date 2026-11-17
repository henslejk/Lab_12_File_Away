import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new java.io.File("src"));

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            java.io.File selectedFile = chooser.getSelectedFile();

            int lineCount = 0;
            int wordCount = 0;
            int characterCount = 0;

            try
            {
                BufferedReader reader = new BufferedReader(
                        new FileReader(selectedFile));

                String line;

                System.out.println("\nFile Contents:");
                System.out.println("--------------------");

                while ((line = reader.readLine()) != null)
                {
                    System.out.println(line);

                    lineCount++;

                    String trimmedLine = line.trim();

                    if (!trimmedLine.isEmpty())
                    {
                        wordCount += trimmedLine.split("\\s+").length;
                    }

                    characterCount += line.length();
                }

                reader.close();

                System.out.println("\nFile Summary:");
                System.out.println("--------------------");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + characterCount);
            }
            catch (IOException e)
            {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("No file selected.");
        }
    }
}

