// Name: Cathy Patton
// Date: 7/15/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from Breed.txt  
// Outputs: Share data with Breed.java 


import java.io.*; // For Exception and BufferedReader 
import java.util.*; 
import java.nio.file.*;     

public class ReadBreed {  

    public static List<Breed> loadBreeds(String filePath) { // Method to load breeds from a CSV file
        List<Breed> breeds = new ArrayList<>(); // Initialize an array to hold Breed objects

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) { // Open the file for reading using a BufferedReader
            String line; // Variable to hold each line of input
            boolean isFirstLine = true; // Flag to skip the first (header) line

            while ((line = reader.readLine()) != null) { // Read each line until end of file
                if (isFirstLine) { // If this is the first line (header)
                    isFirstLine = false; // Mark header as processed
                    continue; // Skip this iteration (don’t process header)
                } // End if statement 

                List<String> fields = parseCSVLine(line); // Parse line into fields, handling quoted commas

                if (fields.size() < 14) { // Check for expected number of fields
                    System.err.println("Skipping malformed line: " + line); // Print error if line is malformed
                    continue; // Skip this malformed line
                } // End if statement 

                Breed breed = new Breed( // Create new Breed object using fields from CSV
                        fields.get(0).trim(), // Name
                        fields.get(1).trim(), // Size
                        fields.get(2).trim(), // Coat 
                        fields.get(3).trim(), // Energy 
                        fields.get(4).trim(), // Training
                        fields.get(5).trim(), // Temperament
                        fields.get(6).trim(), // Apartment 
                        fields.get(7).trim(), // Yard
                        fields.get(8).trim(), // Kids
                        fields.get(9).trim(), // Specialty, if any
                        fields.get(10).trim(), // Primary adjective
                        fields.get(11).trim(), // Adjective 1
                        fields.get(12).trim(), // Adjective 2
                        fields.get(13).trim() // Adjective 3
                ); // End breed object 

                breeds.add(breed); // Add the new Breed object to the list
            } // End while loop 

        } catch (IOException e) { // Catch any I/O exceptions
            System.err.println(">> Error reading breed file: " + e.getMessage()); // Error message
        } // End try statement 

        return breeds; // Return the list of Breed objects
    } // End method loadBreeds 

    private static List<String> parseCSVLine(String line) { // Helper method to parse a line of CSV
        List<String> tokens = new ArrayList<>(); // Initialize array to store extracted tokens
        StringBuilder current = new StringBuilder(); // StringBuilder for building current token
        boolean inQuotes = false; // Track if inside a quoted field

        for (char c : line.toCharArray()) { // For loop over each character in the line
            if (c == '"') { // If character is a quote
                inQuotes = !inQuotes; // Toggle quoted state
            } else if (c == ',' && !inQuotes) { // If comma outside quotes
                tokens.add(current.toString()); // Add completed token to list
                current.setLength(0); // Reset the StringBuilder
            } else {
                current.append(c); // Append character to current token
            } // End if statement 
        } // End for loop 
        tokens.add(current.toString()); // Add final token after loop ends
        return tokens; // Return list of parsed tokens
    } // End method parseCSVLine 
}
