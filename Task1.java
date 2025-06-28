import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandlerUtility {

    // Method to write content to a file (overwrites if file exists)
    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println(" Error writing to file: " + e.getMessage());
        }
    }

    // Method to read content from a file
    public static String readFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            System.out.println("File read successfully.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    // Method to append content to a file
    public static void appendToFile(String filename, String contentToAppend) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(contentToAppend);
            System.out.println(" Content appended successfully.");
        } catch (IOException e) {
            System.err.println(" Error appending to file: " + e.getMessage());
        }
    }

    // Method to replace a specific word or phrase in the file
    public static void modifyFile(String filename, String target, String replacement) {
        try {
            Path path = Paths.get(filename);
            String content = new String(Files.readAllBytes(path));
            content = content.replaceAll(target, replacement);
            Files.write(path, content.getBytes());
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println(" Error modifying file: " + e.getMessage());
        }
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        String fileName = "example.txt";

        // 1. Write to file
        writeFile(fileName, "Hello, this is the first line.\nThis is the second line.\n");

        // 2. Read from file
        System.out.println(" Reading File:");
        System.out.println(readFile(fileName));

        // 3. Append to file
        appendToFile(fileName, "This line was appended.\n");

        // 4. Modify file (replace a word)
        modifyFile(fileName, "first", "updated");

        // 5. Read modified file
        System.out.println(" Reading Modified File:");
        System.out.println(readFile(fileName));
    }
}
