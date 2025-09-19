// Name: Cathy Patton
// Date: 7/28/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: Read data from user input  
// Outputs: None


public class User {
    private String userName; // Initialize variables
    private String quizBreedResult;
    private String email;
    private String phone;
    private String adoptedDog; // "Yes" or "No" (default "No", updated internally by shelter staff)
    private String dogName;    // If adopted (updated internally)

    public User(String userName, String quizBreedResult, String email, String phone) { // Constructor 
        this.userName = userName;
        this.quizBreedResult = quizBreedResult;
        this.email = email;
        this.phone = phone;
        this.adoptedDog = "No";   // default
        this.dogName = "";        // default
    } // End constructor method 

    // Getters
    public String getUserName() { return userName; }
    public String getQuizBreedResult() { return quizBreedResult; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAdoptedDog() { return adoptedDog; }
    public String getDogName() { return dogName; }
}
