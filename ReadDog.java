// Name: Cathy Patton
// Date: 7/15/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from Breed.txt  
// Outputs: Share data with Breed.java 


import java.io.*;  
import java.util.*;   

public class ReadDog {   

    public static List<Dog> loadDogs(String filename, List<Breed> breedList) { // Method to load dog data from a CSV file
        List<Dog> dogList = new ArrayList<>(); // Create an array to store Dog (and Puppy) objects

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { // Open file for reading using BufferedReader
            String line = reader.readLine(); // Read and skip the header line

            while ((line = reader.readLine()) != null) { // Loop through the rest of the file line by line
                String[] parts = line.split(",", -1); // Split each line into parts using comma (keep empty fields)

                if (parts.length < 9) continue; // Skip the line if it doesn't have all 9 expected fields

                int number = Integer.parseInt(parts[0].trim()); // Parse dog ID number (for dog details menu)
                String breedName = parts[1].trim(); // Get breed name
                String name = parts[2].trim(); // Get dog name
                String ageStr = parts[3].trim(); // Raw age string (could be "6 months", etc.)
                String sex = parts[4].trim(); // Get sex (e.g., Male/Female)
                boolean spayedNeutered = parts[5].trim().equalsIgnoreCase("Yes"); // Convert "Yes"/"No" to boolean
                String status = parts[6].trim(); // Adoption status
                String adoptionFee = parts[7].trim(); // Adoption fee (or "Puppy" as a special flag)
                String staffComments = parts[8].trim(); // Comments from shelter staff

                double age; // Normalize puppy age format (convert months to years)
                if (ageStr.toLowerCase().contains("month")) { // If age is given in months
                    age = Double.parseDouble(ageStr.replace("months", "").trim()) / 12.0; // Convert to years
                } else {
                    age = Double.parseDouble(ageStr); // Else parse as a regular number
                } // End if statement 

                Breed breed = findBreedByName(breedList, breedName); // Look up breed details from provided list
                if (breed == null) { // If not found
                    System.err.println("Warning: Breed not found for " + breedName + " (skipping)"); // Warn and skip
                    continue; // Skip this dog record
                } // End if statement 

                Dog dog; // Determine whether to create a Puppy or Dog object
                if (adoptionFee.equalsIgnoreCase("Puppy")) { // If flagged as "Puppy"
                    dog = new Puppy(number, breedName, name, age, sex, spayedNeutered, status, adoptionFee, staffComments,
                            breed.getSize(), breed.getCoatLength(), breed.getEnergyLevel(), breed.getEaseOfTraining(),
                            breed.getTemperament(), breed.getApartmentFriendly(), breed.getNeedsYard(), breed.getGoodWithChildren(),
                            breed.getSpecialty(), breed.getPrimaryAdjective(), breed.getAdjective1(), breed.getAdjective2(), breed.getAdjective3());
                } else { // Otherwise, create a regular Dog
                    dog = new Dog(number, breedName, name, age, sex, spayedNeutered, status, adoptionFee, staffComments,
                            breed.getSize(), breed.getCoatLength(), breed.getEnergyLevel(), breed.getEaseOfTraining(),
                            breed.getTemperament(), breed.getApartmentFriendly(), breed.getNeedsYard(), breed.getGoodWithChildren(),
                            breed.getSpecialty(), breed.getPrimaryAdjective(), breed.getAdjective1(), breed.getAdjective2(), breed.getAdjective3());
                } // End if statement 

                dogList.add(dog); // Add the created Dog or Puppy object to the list
            } // End while loop 

        } catch (IOException e) { // If an I/O error occurs during file read
            System.err.println(">> Error reading dog data: " + e.getMessage()); // Print error message
        } // End try statement 

        return dogList; // Return the list of loaded Dog/Puppy objects
    } // End method loadDogs 

    private static Breed findBreedByName(List<Breed> breedList, String breedName) { // Helper method to find a Breed object by name (case-insensitive)
        for (Breed breed : breedList) { // Iterate through all breeds
            if (breed.getName().equalsIgnoreCase(breedName)) { // If breed name matches (ignoring case)
                return breed; // Return the matching breed
            } // End if statement 
        } // End for loop 
        return null; // Return null if no match found
    } // End method findBreedByName 
}

