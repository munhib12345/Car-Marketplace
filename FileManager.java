package marketplace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	public static void saveUser(User user) {

	    try (BufferedWriter writer =
	                 new BufferedWriter(new FileWriter("users.txt", true))) {

	        if (user instanceof Dealership) {

	            Dealership dealership = (Dealership) user;

	            writer.write(
	                user.getUserId() + "|" +
	                user.getName() + "|" +
	                user.getEmail() + "|" +
	                user.getPassword() + "|" +
	                user.getPhone() + "|" +
	                "DEALERSHIP" + "|" +
	                dealership.getDealershipName() + "|" +
	                dealership.getBusinessAddress()
	            );

	        } else {

	            writer.write(
	                user.getUserId() + "|" +
	                user.getName() + "|" +
	                user.getEmail() + "|" +
	                user.getPassword() + "|" +
	                user.getPhone() + "|" +
	                "CUSTOMER"
	            );
	        }

	        writer.newLine();

	    } catch (IOException e) {
	        System.out.println("Error saving user: " + e.getMessage());
	    }
	}
	public static void loadUsers(UserHashTable userTable) {

	    try (BufferedReader reader =
	                 new BufferedReader(new FileReader("users.txt"))) {

	        String line;

	        while ((line = reader.readLine()) != null) {

	            if (line.trim().isEmpty()) {
	                continue;
	            }

	            String[] data = line.split("\\|");

	            String userId = data[0];
	            String name = data[1];
	            String email = data[2];
	            String password = data[3];
	            String phone = data[4];
	            String userType = data[5];

	            User user;

	            if (userType.equals("DEALERSHIP")) {

	                String dealershipName = data[6];
	                String businessAddress = data[7];

	                user = new Dealership(
	                    userId,
	                    name,
	                    email,
	                    password,
	                    phone,
	                    dealershipName,
	                    businessAddress
	                );

	            } else {

	                user = new Customer(
	                    userId,
	                    name,
	                    email,
	                    password,
	                    phone
	                );
	            }

	            userTable.add(user);
	        }

	    } catch (IOException e) {

	        // File may not exist on the first run
	        System.out.println("No existing user data found.");
	    }
	}
	public static void saveVehicle(Vehicle vehicle) {

	    try (BufferedWriter writer =
	                 new BufferedWriter(new FileWriter("vehicles.txt", true))) {

	        writer.write(
	            vehicle.getListingId() + "|" +
	            vehicle.getVin() + "|" +
	            vehicle.getMake() + "|" +
	            vehicle.getModel() + "|" +
	            vehicle.getYear() + "|" +
	            vehicle.getPrice() + "|" +
	            vehicle.getMileage() + "|" +
	            vehicle.getFuelType() + "|" +
	            vehicle.getTransmission() + "|" +
	            vehicle.getLocation() + "|" +
	            vehicle.getSellerId() + "|" +
	            vehicle.isAvailable()
	        );

	        writer.newLine();

	    } catch (IOException e) {
	        System.out.println("Error saving vehicle: " + e.getMessage());
	    }
	}
	public static void loadVehicles(VehicleHashTable vehicleTable,
            VehicleList vehicleList) {

try (BufferedReader reader =
new BufferedReader(new FileReader("vehicles.txt"))) {

String line;

while ((line = reader.readLine()) != null) {

if (line.trim().isEmpty()) {
continue;
}

String[] data = line.split("\\|");

String listingId = data[0];
String vin = data[1];
String make = data[2];
String model = data[3];
int year = Integer.parseInt(data[4]);
double price = Double.parseDouble(data[5]);
int mileage = Integer.parseInt(data[6]);
String fuelType = data[7];
String transmission = data[8];
String location = data[9];
String sellerId = data[10];
boolean available = Boolean.parseBoolean(data[11]);

Vehicle vehicle = new Vehicle(
listingId,
vin,
make,
model,
year,
price,
mileage,
fuelType,
transmission,
location,
sellerId
);

vehicle.setAvailable(available);

if (vehicleTable.add(vehicle)) {
vehicleList.add(vehicle);
}
}

} catch (IOException e) {

// File may not exist on the first run
System.out.println("No existing vehicle data found.");
}
}
	public static void saveAllVehicles(VehicleList vehicleList) {

	    try (BufferedWriter writer =
	                 new BufferedWriter(new FileWriter("vehicles.txt"))) {

	        Vehicle[] vehicles = vehicleList.toArray();

	        for (Vehicle vehicle : vehicles) {

	            writer.write(
	                vehicle.getListingId() + "|" +
	                vehicle.getVin() + "|" +
	                vehicle.getMake() + "|" +
	                vehicle.getModel() + "|" +
	                vehicle.getYear() + "|" +
	                vehicle.getPrice() + "|" +
	                vehicle.getMileage() + "|" +
	                vehicle.getFuelType() + "|" +
	                vehicle.getTransmission() + "|" +
	                vehicle.getLocation() + "|" +
	                vehicle.getSellerId() + "|" +
	                vehicle.isAvailable()
	            );

	            writer.newLine();
	        }

	    } catch (IOException e) {
	        System.out.println("Error saving vehicles: " + e.getMessage());
	    }
	}
}