// Name: Cathy Patton
// Date: 7/10/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: User inputs quiz answers  
// Outputs: Recap of quiz answers


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    private Scanner scanner; // Initialize scanner 
    private List<String> userAnswers = new ArrayList<>(); // Initialize list to store quiz answers
    private List<Breed> breedList; // Shared breed list from Main
    private List<Dog> dogList; // Shared dog list from Main

    public Quiz(Scanner scanner, List<Breed> breedList, List<Dog> dogList) { // Constructor
        this.scanner = scanner; // Use same scanner from Main
        this.breedList = breedList; // Initialize shared breed list
        this.dogList = dogList; // Initialize shared dog list
    } // End constructor

    public void runQuiz() { // Method to run quiz logic
        userAnswers.clear(); // Clear any previous answers

        // Welcome and information for user 
        System.out.println("\n:: There is no commitment to taking our quiz, but your answers will give you an idea of the");
        System.out.println("best breed for you and, if we have any dogs of that breed available right now, we will show");
        System.out.println("you his/her personal information. If you like what you see, you can make an appointment to meet");
        System.out.println("him/her or any of our other dogs in residence!\n");

        System.out.println(":: All information collected by Furry Faces Dog Shelter will only be used for this quiz.");
        System.out.println(":: No information will be shared or sold to third parties.");
        System.out.println(":: Contact information is optional and will be kept confidential.");
        System.out.print("\n:: Would you like to continue with the quiz? (yes or no): "); // Prompt
        String proceed = scanner.nextLine().trim().toLowerCase(); // User input

        if (!(proceed.equals("yes") || proceed.equals("y"))) { // If not yes or y
            System.out.println("Returning to main menu..."); // Exit
            return;
        } // End if statement 

        System.out.print("\nUser name: "); // Prompt for name
        String userName = scanner.nextLine(); // Read input

        // Quiz Questions (01-12)
        userAnswers.add(askQuestion("What size dog would you prefer?",
                "Small (under 25 pounds)", "Medium (25-60 pounds)", "Large (over 60 pounds)")); // Q1

        userAnswers.add(askQuestion("How much grooming are you willing to do for your pet?",
                "Short hair/little grooming", "Medium hair/moderate grooming", "Long hair/dedicated grooming")); // Q2

        userAnswers.add(askQuestion("How much energy would you like your dog to have?",
                "Low energy/I’d prefer a lap dog", "Medium energy/I want to go for regular walks", "High energy/I want to jog or run with my dog")); // Q3

        userAnswers.add(askQuestion("Consider training your dog. What level are you comfortable with?",
                "Easy to train", "Moderate to train/I’ll take my dog to obedience class", "Difficult to train/I have patience to work with a stubborn dog")); // Q4

        userAnswers.add(askQuestion("What type of temperament would you like your dog to have?",
                "Gentle/friendly/easy-going", "Curious/intelligent", "Confident/independent", "Alert/protective", "Driven/intense")); // Q5

        userAnswers.add(askQuestion("Do you live in an apartment?",
                "Yes, I live in an apartment", "No, I don't live in an apartment")); // Q6

        userAnswers.add(askQuestion("Do you have a yard for your dog?",
                "Yes, I have a yard for a dog", "No, I don't have a yard for a dog")); // Q7

        userAnswers.add(askQuestion("Are you looking for a dog who is good with children?",
                "Yes, I want a dog who is good with children", "No, I don't need a dog who is good with children")); // Q8

        userAnswers.add(askQuestion("Are you looking for a breed with other specific traits?",
                "Guard dog", "Hunting dog", "No special skills in particular")); // Q9

        userAnswers.add(askQuestion("Which of these adjectives is desirable for you in a dog?",
                "Lively", "Calm")); // Q10

        userAnswers.add(askQuestion("Choose a second adjective that is desirable for you in a dog:",
                "Affectionate", "Elegant")); // Q11

        userAnswers.add(askQuestion("Choose a third adjective that is desirable for you in a dog:",
                "Brave", "Friendly")); // Q12

        // Newsletter opt-in
        System.out.println("\nWould you like to receive our shelter’s monthly e-newsletter?");
        System.out.println("  1. Yes, add my email to the list");
        System.out.println("  2. No, thank you");

        int newsletterChoice = -1; // Newsletter selection
        while (newsletterChoice < 1 || newsletterChoice > 2) {
            System.out.print("Your choice (1-2): "); // Instructions 
            String input = scanner.nextLine(); // User input 
            try {
                newsletterChoice = Integer.parseInt(input); // Parse choice
            } catch (NumberFormatException e) {
                System.out.println(">> Invalid input. Please enter 1 or 2."); // Invalid number
            } // End try statement 
        } // End while loop 

        String email = ""; // Default email variable
        String phone = ""; // Default phone variable 

        if (newsletterChoice == 1) { // If they opt-in for the newsletter 
            while (true) {
                System.out.print("Please enter your email address: "); // Prompt
                email = scanner.nextLine().trim(); // User input 
                if (isValidEmail(email)) { // Validate input 
                    System.out.println(":: Thanks! We'll add " + email + " to our newsletter list."); // Thank you 
                    break;
                } else { // Invalid 
                    System.out.println(">> Invalid email address. Please try again."); // Error message 
                } // End nested if statement 
            } // End while loop 
        } else { // Decline opt-in 
            System.out.println(":: No problem! You can always sign up later."); // Thanks anyway 
        } // End if statement 

        System.out.println("\nTo recap, here are your quiz answers:"); // Recap answers
        for (int i = 0; i < userAnswers.size(); i++) { // For loop to iterate through answers 
            System.out.printf("Q%02d: %s\n", i + 1, userAnswers.get(i)); // Display answer to user 
        } // End for loop 

        Breed bestMatch = matchBreed(userAnswers); // Determine best match breed

        if (bestMatch != null) { // If there is a match
            System.out.println("\nBased on the majority of your answers, a good breed for you is:"); // Header 
            System.out.println(bestMatch); // Display match 
        } else { // No match 
            System.out.println("\n>> Sorry, we couldn’t find a perfect match for your preferences."); // Sorry message 
            return;
        } // End if statement 

        List<Dog> dogList = ReadDog.loadDogs("Residents.txt", breedList); // Load dogs from Residents file 
        
        String[] dogMatch = showMatchingDogs(dogList, bestMatch, breedList, scanner); // Initialize variable for method return 
        String matchedDogName = dogMatch[0]; // Method return variable for outside use 
        phone = dogMatch[1]; // Method return variable for outside use 

        User user = new User(userName, bestMatch.getName(), email, phone); // Initialize user object
        WriteUser.saveUser(user); // Save to file

        QuizResults result = new QuizResults( // Initialize quiz result object 
                userName,
                bestMatch.getName(),
                email,
                phone,
                userAnswers,
                matchedDogName
        );

        WriteQuizResult.saveQuizResult(result); // Save quiz results
    } // End runQuiz

    private String askQuestion(String question, String... options) { // Ask one quiz question
        System.out.println("\n" + question); // Display question 
        for (int i = 0; i < options.length; i++) { // For loop to iterate through response options 
            System.out.printf("  %d. %s\n", i + 1, options[i]); // Display options 
        } // End for loop 

        int choice = -1; // Initialize choice variable 
        while (choice < 1 || choice > options.length) { // Validate choice 
            System.out.print("Your choice (1-" + options.length + "): "); // Display choice 
            String input = scanner.nextLine(); // User input 
            try {
                choice = Integer.parseInt(input); // Parse input 
            } catch (NumberFormatException e) { // Exception 
                System.out.println(">> Invalid input. Please enter a number between 1 and " + options.length + "."); // Error message 
            } // End try statement 
        } // End while loop 

        return options[choice - 1]; // Return selected answer
    } // End method askQuestion 

    private boolean isValidEmail(String email) { // Basic email validation
        int atIndex = email.indexOf("@"); // Check for @
        int dotIndex = email.lastIndexOf("."); // Check for .
        return atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1;
    } // End method isValidEmail 

    private Breed matchBreed(List<String> userAnswers) { // Method matchBreed scores breeds based on quiz results 
        String sizeAnswer = userAnswers.get(0); // Q1: Size
        String coatAnswer = userAnswers.get(1); // Q2: Coat Length
        String energyAnswer = userAnswers.get(2); // Q3: Energy Level
        String trainingAnswer = userAnswers.get(3); // Q4: Ease of Training

        // Q5: Temperament – parse into keywords
        String temperamentAnswer = userAnswers.get(4).toLowerCase();
        List<String> temperamentKeywords = Arrays.asList(temperamentAnswer.split("[ /,-]+"));

        String apartmentAnswer = userAnswers.get(5); // Q6: Apartment Friendly
        String yardAnswer = userAnswers.get(6); // Q7: Needs Yard
        String childrenAnswer = userAnswers.get(7); // Q8: Good with Children
        String specialtyAnswer = userAnswers.get(8); // Q9: Specialty
        String adj1 = userAnswers.get(9); // Q10: Adjective 1 
        String adj2 = userAnswers.get(10); // Q11: Adjective 2 
        String adj3 = userAnswers.get(11); // Q12: Adjective 3 

        Breed bestMatch = null; // Initialize variable 
        int highestScore = -1; // Initialize variable 

        for (Breed breed : breedList) { // For loop to score breeds 
            int score = 0; // Initialize score to zero 
    
            if (sizeAnswer.equalsIgnoreCase(breed.getSize())) score++;
            if (coatAnswer.equalsIgnoreCase(breed.getCoatLength())) score++;
            if (energyAnswer.equalsIgnoreCase(breed.getEnergyLevel())) score++;
            if (trainingAnswer.equalsIgnoreCase(breed.getEaseOfTraining())) score++;
            if (apartmentAnswer.equalsIgnoreCase(breed.getApartmentFriendly())) score++;
            if (yardAnswer.equalsIgnoreCase(breed.getNeedsYard())) score++;
            if (childrenAnswer.equalsIgnoreCase(breed.getGoodWithChildren())) score++;
            if (specialtyAnswer.equalsIgnoreCase(breed.getSpecialty())) score++;
            if (adj1.equalsIgnoreCase(breed.getAdjective1())) score++;
            if (adj2.equalsIgnoreCase(breed.getAdjective2())) score++;
            if (adj3.equalsIgnoreCase(breed.getAdjective3())) score++;

            String breedTemperament = breed.getTemperament().toLowerCase(); // Neutralize case 
            for (String keyword : temperamentKeywords) { // For loop to check temperament 
                if (breedTemperament.contains(keyword)) { // If keyword is represented
                    score++; // Increment score 
                    break; // only count one temperament keyword match
                } // End if statement 
            } // End nested for loop 

            if (score > highestScore) { // If to update highest score 
                highestScore = score;
                bestMatch = breed; // Initialize best breed variable 
            } // End if statement 
        } // End for loop 

        return bestMatch;
    } // End method matchBreed

    public static String[] showMatchingDogs(List<Dog> dogList, Breed bestMatch, List<Breed> breedList, Scanner scanner) { // Check for Dog match 
        String matchedDogName = ""; // Store the first matched dog's name
        String phone = ""; // Store phone number if user enters it
        String matchBreed = bestMatch.getName(); // Get matched breed
        boolean found = false;

        System.out.println("\nDogs currently available in our shelter that match this breed (" + matchBreed + "):\n");

        for (Dog dog : dogList) { // For loop to iterate through dog list 
            if (dog.getBreedName().equalsIgnoreCase(matchBreed) && dog.getStatus().equalsIgnoreCase("Available")) {
                System.out.println(dog); // Display dog 
                Capstone_patton.showDogDetails(dog, breedList); // Display dog details 
                System.out.println("\n:: If you would like to make an appointment to meet " + dog.getName() + ", please call 555-123-4567 to speak to our staff!");
                matchedDogName = dog.getName(); // Save matched name
                found = true;
                break; // Only first match
            } // End if statement 
        } // End for loop 

        if (!found) { // If no dog match 
            System.out.println(":: Sorry, we don't currently have any available dogs of this breed in the shelter.");
            System.out.print(":: Would you like to be added to a contact list if this breed becomes available? (yes/no): "); // Prompt 
            String response = scanner.nextLine().trim().toLowerCase(); // User input 

            if (response.equals("yes") || response.equals("y")) { // If they want to enter a phone number 
                boolean valid = false;
                while (!valid) { 
                    System.out.print("Please enter your phone number (10 digits, can include spaces or dashes): "); // Prompt 
                    phone = scanner.nextLine().trim(); // User input 
                    String digitsOnly = phone.replace(" ", "").replace("-", ""); // Clean up input 
                    if (digitsOnly.length() == 10 && isAllDigits(digitsOnly)) { // Validate 
                        valid = true;
                        System.out.println(":: Thank you, you will be added to our contact list and notified if we receive a " 
                                   + bestMatch.getName() + " dog!"); // Thank you 
                    } else { // Invalid 
                        System.out.println(">> Invalid phone number. Please enter exactly 10 digits."); // Error message 
                    } // End 2nd nested if statement 
                } // End while loop 
            } // End nested if statement 
        } // End if statement 

        return new String[]{matchedDogName, phone}; // Return dog name and user phone for use in other methods 
    } // End method showMatchingDogs

    private static boolean isAllDigits(String str) { // Check all digits
        for (int i = 0; i < str.length(); i++) { // For loop to iterate through digits 
            if (!Character.isDigit(str.charAt(i))) return false; // If not a digit 
        } // End for loop 
        return true;
    } // End method isAllDigits 
}

