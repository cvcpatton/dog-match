// Name: Cathy Patton
// Date: 7/28/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from user input  
// Outputs: Write data to User.txt 


import java.io.FileWriter;
import java.io.IOException;

public class WriteUser {
    private static final String FILE_NAME = "User.txt";

    public static void saveUser(User user) { // Constructor 
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String line = String.join(",",
                    user.getUserName(),
                    user.getQuizBreedResult(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getAdoptedDog(),
                    user.getDogName()
            );
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println(">> Error writing to User.txt: " + e.getMessage());
        }
    } // End constructor 
}
