// Name: Cathy Patton
// Date: 7/15/25 
// Program: CSC1061X60 Final Project: Dog Shelter Match-making Service 
// Program Description: Users take a quiz to see which dog breed would be ideal for their circumstances, 
//      then any resident dogs of that breed are displayed to the user 
// Inputs: None  
// Outputs: Getters for all fields


public class Breed { // Breed class goes with Breed.txt database 

    private final String name; // Initialize variables for all fields in database 
    private final String size;
    private final String coatLength;
    private final String energyLevel;
    private final String easeOfTraining;
    private final String temperament;
    private final String apartmentFriendly;
    private final String needsYard;
    private final String goodWithChildren;
    private final String specialty;
    private final String primaryAdjective;
    private final String adjective1;
    private final String adjective2;
    private final String adjective3;

    public Breed(String name, String size, String coatLength, String energyLevel, String easeOfTraining, // Constructor 
                 String temperament, String apartmentFriendly, String needsYard, String goodWithChildren,
                 String specialty, String primaryAdjective, String adjective1, String adjective2, String adjective3) {
        this.name = name;
        this.size = size;
        this.coatLength = coatLength;
        this.energyLevel = energyLevel;
        this.easeOfTraining = easeOfTraining;
        this.temperament = temperament;
        this.apartmentFriendly = apartmentFriendly;
        this.needsYard = needsYard;
        this.goodWithChildren = goodWithChildren;
        this.specialty = specialty;
        this.primaryAdjective = primaryAdjective;
        this.adjective1 = adjective1;
        this.adjective2 = adjective2;
        this.adjective3 = adjective3;
    } // End constructor method 

    public String getName() { // Getter methods for all fields 
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getCoatLength() {
        return coatLength;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public String getEaseOfTraining() {
        return easeOfTraining;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getApartmentFriendly() {
        return apartmentFriendly;
    }

    public String getNeedsYard() {
        return needsYard;
    }

    public String getGoodWithChildren() {
        return goodWithChildren;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPrimaryAdjective() {
        return primaryAdjective;
    }

    public String getAdjective1() {
        return adjective1;
    }

    public String getAdjective2() {
        return adjective2;
    }

    public String getAdjective3() {
        return adjective3;
    }

    @Override
    public String toString() { // String display function for Breed 
        return "Breed: " + name +
                "\n  Size: " + size +
                "\n  Coat Length: " + coatLength +
                "\n  Energy Level: " + energyLevel +
                "\n  Ease of Training: " + easeOfTraining +
                "\n  Temperament: " + temperament +
                "\n  Apartment Friendly: " + apartmentFriendly +
                "\n  Needs Yard: " + needsYard +
                "\n  Good with Children: " + goodWithChildren +
                "\n  Specialty: " + specialty +
                "\n  Primary Adjective: " + primaryAdjective +
                "\n  Adjectives: " + adjective1 + ", " + adjective2 + ", " + adjective3;
    } // End method toString 
}
