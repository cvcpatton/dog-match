// Name: Cathy Patton
// Date: 7/28/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from user input  
// Outputs: Display recap of quiz results to user 


import java.util.List;

public class QuizResults extends User {
    private List<String> answers;  // Q01–Q12
    private String matchedDogName; // If matched

    public QuizResults(String userName, String quizBreedResult, String email, String phone,
                      List<String> answers, String matchedDogName) { // Constructor 
        super(userName, quizBreedResult, email, phone);
        this.answers = answers;
        this.matchedDogName = matchedDogName != null ? matchedDogName : "None";
    } // End constructor method 

    // Getters 
    public List<String> getAnswers() {
        return answers;
    }

    public String getMatchedDogName() {
        return matchedDogName;
    }
}
