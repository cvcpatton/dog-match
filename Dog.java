// Name: Cathy Patton
// Date: 7/15/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: None
// Outputs: Getters for all fields


public class Dog extends Breed { // Dog extends the breed class with some extra variables 
    private int number; // Initialize Dog variables from fields in Residents.txt 
    private String name;
    private double age; // in years (vs Puppy in months)
    private String sex;
    private boolean spayedNeutered;
    private String status;
    private String adoptionFee;
    private String staffComments;

    public Dog( // Constructor 
        int number, String breedName, String name, double age, String sex, boolean spayedNeutered,
        String status, String adoptionFee, String staffComments,
        // Breed-inherited attributes:
        String size, String coatLength, String energyLevel, String easeOfTraining,
        String temperament, String apartmentFriendly, String needsYard, String goodWithChildren,
        String specialty, String primaryAdjective, String adjective1, String adjective2, String adjective3
    ) { 
        super(
            breedName, size, coatLength, energyLevel, easeOfTraining,
            temperament, apartmentFriendly, needsYard, goodWithChildren,
            specialty, primaryAdjective, adjective1, adjective2, adjective3
        ); // End variables 

        this.number = number;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.spayedNeutered = spayedNeutered;
        this.status = status;
        this.adoptionFee = adoptionFee;
        this.staffComments = staffComments;
    } // End constructor method 

    public String getBreedName() { // Getter methods for all fields 
        return super.getName();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public boolean isSpayedNeutered() {
        return spayedNeutered;
    }

    public String getStatus() {
        return status;
    }

    public String getAdoptionFee() {
        return "$55";
    }

    public String getStaffComments() {
        return staffComments;
    }

    @Override
    public String toString() { // String display function for Dog 
        return name + " (" + getName() + ") - Age: " + age + " years - Status: " + status;
    } // End method toString 
}
