// Name: Cathy Patton
// Date: 7/2/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Main menu selection  
// Outputs: Menu place-holders 


import java.util.List;
import java.util.Scanner;

public class Capstone_patton {

    private static Scanner scanner; // Initialize scanner 

    public static void main(String[] args) { // Method main runs program execution 
        scanner = new Scanner(System.in);  // single scanner for all input
        boolean keepRunning = true; // Loop variable to continue 

        List<Breed> breedList = ReadBreed.loadBreeds("Breed.txt"); // Load breed data once
        List<Dog> dogList = ReadDog.loadDogs("Residents.txt", breedList); // Load dog data once

        while (keepRunning) { // While loop to run main menu 
            System.out.println("\n~~~Welcome to Furry Faces Dog Shelter!~~~"); // Welcome user 
            System.out.println("Please choose an option:"); // Instructions for user 
            System.out.println("1. Take our dog match-making quiz"); 
            System.out.println("2. See a list of our available dogs");
            System.out.println("3. View contact information to make an appointment");
            System.out.println("4. Exit\n");

            System.out.print("Enter your choice (1-4): "); // Prompt for user selection 
            String input = scanner.nextLine(); // User input 

            switch (input) { // Switch to run menu options 
                case "1":
                    Quiz quiz = new Quiz(scanner, breedList, dogList); // Option 1, Take dog match quiz 
                    quiz.runQuiz(); // Call runQuiz method 
                    break;

                case "2":
                    showResidents(dogList, breedList); // Option 2, See dog list 
                    break;

                case "3":
                    System.out.println("\n:: Thank you for your interest in our Furry Faces!"); // Option 3, Display contact information
                    System.out.println(":: Please call 555-123-4567 to schedule an appointment to meet any of our dogs.");
                    System.out.println("\n:: If you are interested in volunteering, please email volunteer@ffds.org");
                    break;

                case "4":
                    System.out.println("\n:: Thank you for visiting our Furry Faces!"); // Option 4, Exit 
                    keepRunning = false;
                    break;

                default:
                    System.out.println("\n>> Invalid input. Please enter a number from 1 to 4."); // Default cause for invalid entry
                    break;
            } // End switch statement 
        } // End while loop 

        scanner.close(); // Close scanner 
    } // End function main 

    private static void showResidents(List<Dog> dogList, List<Breed> breedList) { // Function to show available dogs with drill-down
        boolean viewingDogs = true; // Initialize variable for sub-menu 

        while (viewingDogs) { // While loop to run sub-menu 
            System.out.println("\nAvailable Dogs:"); // Header 

            List<Dog> availableDogs = dogList.stream()
                    .filter(dog -> "Available".equalsIgnoreCase(dog.getStatus()))
                    .toList(); // Filter available dogs 

            if (availableDogs.isEmpty()) { // If dog list is empty 
                System.out.println(">> Sorry, there are currently no dogs available for adoption."); // Output 
                return;
            } // End if statement 

            for (int i = 0; i < availableDogs.size(); i++) { // For loop to display individual dogs 
                Dog dog = availableDogs.get(i); // Get individual dog in list 

                if (dog instanceof Puppy) { // Puppy override, age in months 
                    System.out.printf("%d. %s (%s) - %d months old\n",
                            i + 1, dog.getName(), dog.getBreedName(), (int) dog.getAge());
                } else { // Dog age in years 
                    System.out.printf("%d. %s (%s) - %.1f years old\n", 
                            i + 1, dog.getName(), dog.getBreedName(), dog.getAge());
                } // End if statement 
            } // End for loop 
            
            System.out.print("\nEnter the number of the dog to see more details, or 0 to return to main menu: "); // Instructions for user 

            try { // Try loop to view dog details 
                int choice = Integer.parseInt(scanner.nextLine()); // Input selection 

                if (choice == 0) { // If choice is zero ... 
                    viewingDogs = false;  // Exit to main menu
                } else if (choice < 1 || choice > availableDogs.size()) { // If choice is negative ... 
                    System.out.println(">> Invalid selection."); // Error message 
                } else { // If choice is >= 1 ... 
                    Dog selectedDog = availableDogs.get(choice - 1); // Select dog 
                    showDogDetails(selectedDog, breedList); // Show details 

                    System.out.print("\nEnter 0 to return to the available dog list: "); // Prompt to go back to dog menu 
                    while (!scanner.nextLine().equals("0")) { // While loop 
                        System.out.print("Please enter 0 to return to the available dog list: "); // Error message 
                    } // End nested while loop 
                } // End if statement 

            } catch (NumberFormatException e) { // Exception 
                System.out.println(">> Invalid input."); // Error message 
            } // End try statement 
        } // End while loop 
    } // End method showResidents 

    public static void showDogDetails(Dog dog, List<Breed> breedList) { // Method to display full dog/breed info
        System.out.println("\n--- Dog Details ---"); // Header 
        System.out.println("Name: " + dog.getName()); // Name 
        System.out.println("Breed: " + dog.getBreedName()); // Breed 
        if (dog.getAdoptionFee().equals("$125")) { // If this is a dog vs puppy (check adoption fee)
            System.out.println("Age: " + (int)dog.getAge() + " months"); // Age for puppy (months)
        } else { // If not a puppy, it is a dog 
            System.out.println("Age: " + dog.getAge() + " years"); // Age for dog (years)
        } // End if statement 
        System.out.println("Sex: " + dog.getSex()); // Sex
        System.out.println("Spayed/Neutered: " + (dog.isSpayedNeutered() ? "Yes" : "No")); // S/N 
        System.out.println("Status: " + dog.getStatus()); // Status 
        System.out.println("Adoption Fee: " + dog.getAdoptionFee()); // Adoption fee 
        System.out.println("Staff Comments: " + dog.getStaffComments()); // Staff comments 

        Breed breed = findBreedByName(breedList, dog.getBreedName()); // Show the rest of the breed details 
        if (breed != null) { // Find the breed 
            System.out.println("\n-- Breed Details --"); // Header 
            System.out.println("Size: " + breed.getSize()); // Size 
            System.out.println("Coat Length: " + breed.getCoatLength()); // Coat 
            System.out.println("Energy Level: " + breed.getEnergyLevel()); // Energy 
            System.out.println("Ease of Training: " + breed.getEaseOfTraining()); // Training 
            System.out.println("Temperament: " + breed.getTemperament());  // Temperment 
            System.out.println("Apartment Friendly: " + breed.getApartmentFriendly()); // Apartment 
            System.out.println("Needs Yard: " + breed.getNeedsYard()); // Yard 
            System.out.println("Good with Children: " + breed.getGoodWithChildren()); // Kids 
            System.out.println("Specialty: " + breed.getSpecialty()); // Specialty, if any 
            System.out.println("Adjectives: " + breed.getPrimaryAdjective() + ", " + // Three primary adjectives 
                    breed.getAdjective1() + ", " +
                    breed.getAdjective2() + ", " +
                    breed.getAdjective3());
        } else { // If breed isn't in database 
            System.out.println("\nBreed details not found."); // Error message 
        } // End if statement 

        System.out.println("-------------------\n"); // Display lower boarder to show end of details 
    } // End method showDogDetails 

    private static Breed findBreedByName(List<Breed> breedList, String breedName) { // Helper method to find a Breed by name
        for (Breed breed : breedList) { // For loop to find breed 
            if (breed.getName().equalsIgnoreCase(breedName)) { // if statement 
                return breed; 
            } // End if statement 
        } // End for loop 
        return null;
    } // End method findBreedByName 
}

