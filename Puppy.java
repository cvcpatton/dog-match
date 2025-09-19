// Name: Cathy Patton
// Date: 7/17/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: None
// Outputs: Getters for all fields


public class Puppy extends Dog { // Puppy extends the dog class with two overrides 

    public Puppy( // Constructor 
        int number, String breedName, String name, double age, String sex, boolean spayedNeutered,
        String status, String adoptionFee, String staffComments,
        String size, String coatLength, String energyLevel, String easeOfTraining,
        String temperament, String apartmentFriendly, String needsYard, String goodWithChildren,
        String specialty, String primaryAdjective, String adjective1, String adjective2, String adjective3
    ) { 
        super(number, breedName, name, age, sex, spayedNeutered, status, adoptionFee, staffComments,
              size, coatLength, energyLevel, easeOfTraining,
              temperament, apartmentFriendly, needsYard, goodWithChildren,
              specialty, primaryAdjective, adjective1, adjective2, adjective3); // End variables 
    } // End constructor method 

    @Override
    public double getAge() { // Override to return age in months
        return super.getAge() * 12;
    }

    @Override
    public String getAdoptionFee() { // Override to increase adoption fee for puppies (Dog fee is $55)
        return "$125";
    }

    @Override
    public String toString() { // String display function for Puppy  
        return getName() + " (" + getBreedName() + ") - Age: " + getAge() + " months - Status: " + getStatus();
    }
}
