// Name: Cathy Patton
// Date: 7/28/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from user input  
// Outputs: Write data to QuizResults.txt 


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteQuizResult {
    private static final String FILE_NAME = "QuizResults.txt";

    public static void saveQuizResult(QuizResults result) { // Constructor 
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(result.getUserName()).append(","); // User-name from parent 

            List<String> answers = result.getAnswers(); // Initialize array for quiz answers 
            for (String answer : answers) { // for loop to iterate through array elements 
                sb.append(answer).append(","); // Append answer with a comma
            }

            sb.append(result.getQuizBreedResult()).append(","); // Breed-quiz-result
            sb.append(result.getMatchedDogName()); // Dog match (if any)

            writer.write(sb.toString() + "\n"); // Newline 
        } catch (IOException e) {
            System.out.println(">> Error writing to QuizResult.txt: " + e.getMessage());
        }
    } // End constructor method 
}
