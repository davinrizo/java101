import java.util.Scanner;

public class VehicleInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Car");
            System.out.println("2. Add Motorcycle");
            System.out.println("3. Add Truck");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline

            switch (choice) {
                case 1:
                    // Add a Car
                    System.out.print("Enter Brand: ");
                    String carBrand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String carModel = scanner.nextLine();
                    System.out.print("Enter Year of Manufacture: ");
                    int carYear = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Number of Doors: ");
                    int doors = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Fuel Type (petrol, diesel, electric): ");
                    String fuelType = scanner.nextLine();

                    Car car = new Car(carBrand, carModel, carYear);
                    car.setNumberOfDoors(doors);
                    car.setFuelType(fuelType);

                    System.out.println(car);
                    break;

                case 2:
                    // Add a Motorcycle
                    System.out.print("Enter Brand: ");
                    String motorcycleBrand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String motorcycleModel = scanner.nextLine();
                    System.out.print("Enter Year of Manufacture: ");
                    int motorcycleYear = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Number of Wheels: ");
                    int wheels = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Type (sport, cruiser, off-road): ");
                    String type = scanner.nextLine();

                    Motorcycle motorcycle = new Motorcycle(motorcycleBrand, motorcycleModel, motorcycleYear);
                    motorcycle.setNumberOfWheels(wheels);
                    motorcycle.setType(type);

                    System.out.println(motorcycle);
                    break;

                case 3:
                    // Add a Truck
                    System.out.print("Enter Brand: ");
                    String truckBrand = scanner.nextLine();
                    System.out.print("Enter Model: ");
                    String truckModel = scanner.nextLine();
                    System.out.print("Enter Year of Manufacture: ");
                    int truckYear = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Cargo Capacity (in tons): ");
                    double cargoCapacity = scanner.nextDouble();
                    scanner.nextLine();  // Consume the leftover newline
                    System.out.print("Enter Transmission Type (manual, automatic): ");
                    String transmissionType = scanner.nextLine();

                    Truck truck = new Truck(truckBrand, truckModel, truckYear);
                    truck.setCargoCapacity(cargoCapacity);
                    truck.setTransmissionType(transmissionType);

                    System.out.println(truck);
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
                    break;
            }
        }
    }
}

//Vehicle interface defined with bodyless methods.
public interface Vehicle {
    String getBrand();
    String getModel();
    int getYearOfManufacture();
}

//CarVehicle interface defined with bodyless methods.
public interface CarVehicle {
    void setNumberOfDoors(int doors);
    int getNumberOfDoors();
    void setFuelType(String fuelType);
    String getFuelType();
}

//MotorVehicle interface defined with bodyless methods.
public interface MotorVehicle {
    void setNumberOfWheels(int wheels);
    int getNumberOfWheels();
    void setType(String type);
    String getType();
}

//TruckVehicle interface defined with bodyless methods.
public interface TruckVehicle {
    void setCargoCapacity(double capacity);
    double getCargoCapacity();
    void setTransmissionType(String transmissionType);
    String getTransmissionType();
}

// Car class that implements Vehicle and CarVehicle interfaces.
public class Car implements Vehicle, CarVehicle {
    private String brand;
    private String model;
    private int yearOfManufacture;
    private int numberOfDoors;
    private String fuelType;

  //  constructor for Car class.
    public Car(String brand, String model, int yearOfManufacture) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    //Overriding getBrand method from Vehicle interface.
    @Override
    public String getBrand() {
        return brand;
    }

    //Overriding getModel method from Vehicle interface.
    @Override
    public String getModel() {
        return model;
    }

    //Overiding getYearOfManufacture method from Vehicle interface.
    @Override
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    //  Overriding setNumberOfDoors method from CarVehicle interface.
    //  With input that accepts an integer called doors.
    @Override
    public void setNumberOfDoors(int doors) {
        this.numberOfDoors = doors;
    }

    //Overriding getNumberOfDoors method from CarVehicle interface.
    @Override
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

  //  Overriding setFuleType method from CarVehicle interface.
  //  With input that accepts string type called fuelTypes, Input:
  //  Diesel, Petrol, Electric
    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    //Overriding getFuelType method from CarVehicle interface.
    @Override
    public String getFuelType() {
        return fuelType;
    }

    //Overriding toString method from Object class/superclass.
    @Override
    public String toString() {
        return "Car [Brand= " + brand + ", Model= " + model + ", Year= " + yearOfManufacture 
            + ", Doors= " + numberOfDoors + ", Fuel Type= " + fuelType + "]";
    }
}

//  MotorCycle class defined with implementing Vehicle and MotorVehicle interfaces.
//  Pretty much the same as Car class structure.
//  if methods below seem hard to understand, check Car class for documentation.
public class Motorcycle implements Vehicle, MotorVehicle {
    private String brand;
    private String model;
    private int yearOfManufacture;
    private int numberOfWheels;
    private String type;

    public Motorcycle(String brand, String model, int yearOfManufacture) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    //  Overriding setNumberOfWheels from MotorVehicle interface.
    //  input accepts integer type called wheels.
    @Override
    public void setNumberOfWheels(int wheels) {
        this.numberOfWheels = wheels;
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Motorcycle [Brand= " + brand + ", Model= " + model + ", Year= " + yearOfManufacture 
            + ", Wheels= " + numberOfWheels + ", Type= " + type + "]";
    }
}


//  Truck class defined with implementing Vehicle and MotorVehicle interfaces.
//  Pretty much the same as Car class structure.
//  if methods below seem hard to understand, check Car class for documentation.
public class Truck implements Vehicle, TruckVehicle {
    private String brand;
    private String model;
    private int yearOfManufacture;
    private double cargoCapacity;
    private String transmissionType;


    public Truck(String brand, String model, int yearOfManufacture) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    //  Overriding setCargoCapacity from TruckVehicle interface.
    //  input accepts double type caled capacity.
    @Override
    public void setCargoCapacity(double capacity) {
        this.cargoCapacity = capacity;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public String toString() {
        return "Truck [Brand= " + brand + ", Model= " + model + ", Year= " + yearOfManufacture 
            + ", Cargo Capacity= " + cargoCapacity + " tons, Transmission Type= " + transmissionType + "]";
    }
}









