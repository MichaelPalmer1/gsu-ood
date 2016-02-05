/**
 * Michael Palmer
 * CSCI 5335 A
 * Assignment 3 - Decorator pattern
 */
public class Assignment3 {
    public static void main(String[] args) {
        // Create a basic sedan
        Car sedan = new Sedan();
        System.out.printf("Description: %s\n", sedan.getDescription());
        System.out.printf("Price: $%,.2f\n", sedan.price());
        System.out.println();

        // Add features
        sedan = new BackupCamera(sedan);
        sedan = new ParkAssist(sedan);

        // Output final description and price
        System.out.printf("Description: %s\n", sedan.getDescription());
        System.out.printf("Price: $%,.2f\n\n", sedan.price());

        // Separate sedan from truck
        System.out.println("----------------------------------------\n");

        // Create a truck
        Car truck = new Truck();
        System.out.printf("Description: %s\n", truck.getDescription());
        System.out.printf("Price: $%,.2f\n\n", truck.price());

        // Add features
        truck = new HeatedSeats(truck);

        // Output final description and price
        System.out.printf("Description: %s\n", truck.getDescription());
        System.out.printf("Price: $%,.2f\n\n", truck.price());

        // Separate truck from SUV
        System.out.println("----------------------------------------\n");

        // Create a SUV
        Car suv = new SUV();
        System.out.printf("Description: %s\n", suv.getDescription());
        System.out.printf("Price: $%,.2f\n\n", suv.price());

        // Add features
        suv = new HeatedSeats(suv);
        suv = new BackupCamera(suv);
        suv = new ParkAssist(suv);

        // Output final description and price
        System.out.printf("Description: %s\n", suv.getDescription());
        System.out.printf("Price: $%,.2f\n", suv.price());
    }
}

/**
 * Abstract class for a Car
 */
abstract class Car {
    public String description = "Unknown car";

    /**
     * Get the description of the car
     * @return Description of the car
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract function to calculate the car's price
     * @return Price of the car
     */
    public abstract double price();
}

/**
 * Sedan class
 */
class Sedan extends Car {
    /**
     * Sedan constructor
     * Sets the description to sedan
     */
    public Sedan() {
        description = "Sedan";
    }

    /**
     * Calculate the sedan price
     * @return Price of the sedan
     */
    public double price() {
        return 15000;
    }
}

/**
 * Truck class
 */
class Truck extends Car {
    /**
     * Truck constructor
     * Sets the description to truck
     */
    public Truck() {
        description = "Truck";
    }

    /**
     * Calculate the truck price
     * @return Price of the truck
     */
    public double price() {
        return 10000;
    }
}

/**
 * SUV class
 */
class SUV extends Car {
    /**
     * SUV constructor
     * Sets the description to SUV
     */
    public SUV() {
        description = "SUV";
    }

    /**
     * Calculate the SUV price
     * @return Price of the SUV
     */
    public double price() {
        return 18000;
    }
}

/**
 * Feature decorator
 * Provides a means to add different features to cars
 */
abstract class FeatureDecorator extends Car {
    /**
     * Abstract function to return the feature description
     * @return Feature description
     */
    public abstract String getDescription();
}

/**
 * Heated Seats class
 */
class HeatedSeats extends FeatureDecorator {
    Car car;

    /**
     * Heated Seats constructor
     * @param car Car object that this feature should be added to
     */
    public HeatedSeats(Car car) {
        this.car = car;
    }

    /**
     * Append this feature to the car's description
     * @return Car description
     */
    public String getDescription() {
        return car.getDescription() + " + Heated Seats";
    }

    /**
     * Calculate the price by adding to the existing price
     * @return Price of the car with this feature added
     */
    public double price() {
        return car.price() + 500;
    }
}

/**
 * Backup Camera class
 */
class BackupCamera extends FeatureDecorator {
    Car car;

    /**
     * Backup Camera constructor
     * @param car Car object that this feature should be added to
     */
    public BackupCamera(Car car) {
        this.car = car;
    }

    /**
     * Append this feature to the car's description
     * @return Car description
     */
    public String getDescription() {
        return car.getDescription() + " + Backup Camera";
    }

    /**
     * Calculate the price by adding to the existing price
     * @return Price of the car with this feature added
     */
    public double price() {
        return car.price() + 2000;
    }
}

class ParkAssist extends FeatureDecorator {
    Car car;

    /**
     * Park Assist constructor
     * @param car Car object that this feature should be added to
     */
    public ParkAssist(Car car) {
        this.car = car;
    }

    /**
     * Append this feature to the car's description
     * @return Car description
     */
    public String getDescription() {
        return car.getDescription() + " + Park Assist";
    }

    /**
     * Calculate the price by adding to the existing price
     * @return Price of the car with this feature added
     */
    public double price() {
        return car.price() + 5000;
    }
}