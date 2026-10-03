package marketplace;

public class MarketplaceManager {

    private VehicleHashTable vehicleTable;
    private UserHashTable userTable;
    private VehicleList vehicleList;

    public MarketplaceManager() {

        vehicleTable = new VehicleHashTable(100);
        userTable = new UserHashTable(100);
        vehicleList = new VehicleList();

        FileManager.loadUsers(userTable);
        FileManager.loadVehicles(vehicleTable, vehicleList);
    }

    // ---------------- USER MANAGEMENT ----------------

    public boolean addUser(User user) {
        return userTable.add(user);
    }

    public User findUser(String userId) {
        return userTable.find(userId);
    }

    public boolean removeUser(String userId) {
        return userTable.remove(userId);
    }

    public int getUserCount() {
        return userTable.size();
    }

    // ---------------- VEHICLE MANAGEMENT ----------------

    public boolean addVehicle(Vehicle vehicle) {

        if (vehicle == null) {
            return false;
        }

        // Seller must exist
        if (vehicle.getSellerId() == null
                || userTable.find(vehicle.getSellerId()) == null) {
            return false;
        }

        // Basic vehicle validation
        if (vehicle.getPrice() <= 0) {
            return false;
        }

        if (vehicle.getMileage() < 0) {
            return false;
        }

        if (vehicle.getYear() < 1886 || vehicle.getYear() > 2100) {
            return false;
        }

        // Add to hash table first
        boolean added = vehicleTable.add(vehicle);

        if (added) {
            vehicleList.add(vehicle);
            FileManager.saveVehicle(vehicle);
        }

        return added;
    }

    public Vehicle findVehicle(String listingId) {
        return vehicleTable.find(listingId);
    }

    public boolean removeVehicle(String listingId) {

        Vehicle vehicle = vehicleTable.find(listingId);

        if (vehicle == null) {
            return false;
        }

        boolean removedFromTable = vehicleTable.remove(listingId);

        if (removedFromTable) {
            vehicleList.remove(listingId);
            FileManager.saveAllVehicles(vehicleList);
            return true;
        }

        return false;
    }

    public int getVehicleCount() {
        return vehicleTable.size();
    }

    public VehicleList getVehicleList() {
        return vehicleList;
    }
    public Vehicle[] searchByMake(String make) {

        Vehicle[] allVehicles = vehicleList.toArray();

        int count = 0;

        // First count matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getMake().equalsIgnoreCase(make)
                    && vehicle.isAvailable()) {
                count++;
            }
        }

        // Create result array of exact required size
        Vehicle[] results = new Vehicle[count];

        int index = 0;

        // Store matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getMake().equalsIgnoreCase(make)
                    && vehicle.isAvailable()) {

                results[index] = vehicle;
                index++;
            }
        }

        return results;
    }
    public Vehicle[] searchByPriceRange(double minPrice, double maxPrice) {

        Vehicle[] allVehicles = vehicleList.toArray();

        int count = 0;

        // Count matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getPrice() >= minPrice
                    && vehicle.getPrice() <= maxPrice
                    && vehicle.isAvailable()) {

                count++;
            }
        }

        // Create result array
        Vehicle[] results = new Vehicle[count];

        int index = 0;

        // Store matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getPrice() >= minPrice
                    && vehicle.getPrice() <= maxPrice
                    && vehicle.isAvailable()) {

                results[index] = vehicle;
                index++;
            }
        }

        return results;
    }
    public Vehicle[] searchByModel(String model) {

        Vehicle[] allVehicles = vehicleList.toArray();

        int count = 0;

        // Count matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getModel().equalsIgnoreCase(model)
                    && vehicle.isAvailable()) {

                count++;
            }
        }

        // Create result array
        Vehicle[] results = new Vehicle[count];

        int index = 0;

        // Store matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getModel().equalsIgnoreCase(model)
                    && vehicle.isAvailable()) {

                results[index] = vehicle;
                index++;
            }
        }

        return results;
    }
    public Vehicle[] searchByYear(int year) {

        Vehicle[] allVehicles = vehicleList.toArray();

        int count = 0;

        // Count matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getYear() == year
                    && vehicle.isAvailable()) {

                count++;
            }
        }

        // Create result array
        Vehicle[] results = new Vehicle[count];

        int index = 0;

        // Store matching vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getYear() == year
                    && vehicle.isAvailable()) {

                results[index] = vehicle;
                index++;
            }
        }

        return results;
    }
    public Vehicle[] sortByPrice(Vehicle[] vehicles, boolean ascending) {

        if (vehicles == null) {
            return new Vehicle[0];
        }

        MergeSort.sortByPrice(vehicles, ascending);

        return vehicles;
    }
    public Vehicle[] sortByYear(Vehicle[] vehicles, boolean ascending) {

        if (vehicles == null) {
            return new Vehicle[0];
        }

        MergeSort.sortByYear(vehicles, ascending);

        return vehicles;
    }
    public Vehicle[] getVehiclesBySeller(String sellerId) {

        Vehicle[] allVehicles = vehicleList.toArray();

        int count = 0;

        // Count seller's vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getSellerId().equals(sellerId)) {
                count++;
            }
        }

        Vehicle[] results = new Vehicle[count];

        int index = 0;

        // Store seller's vehicles
        for (Vehicle vehicle : allVehicles) {

            if (vehicle.getSellerId().equals(sellerId)) {
                results[index] = vehicle;
                index++;
            }
        }

        return results;
    }
    public Vehicle[] searchVehicles(String make,
            String model,
            double minPrice,
            double maxPrice,
            int year) {

Vehicle[] allVehicles = vehicleList.toArray();

int count = 0;

// Count matching vehicles
for (Vehicle vehicle : allVehicles) {

boolean matches = true;

if (make != null && !make.isEmpty()
&& !vehicle.getMake().equalsIgnoreCase(make)) {
matches = false;
}

if (model != null && !model.isEmpty()
&& !vehicle.getModel().equalsIgnoreCase(model)) {
matches = false;
}

if (vehicle.getPrice() < minPrice
|| vehicle.getPrice() > maxPrice) {
matches = false;
}

if (year > 0 && vehicle.getYear() != year) {
matches = false;
}

if (matches && vehicle.isAvailable()) {
count++;
}
}

Vehicle[] results = new Vehicle[count];

int index = 0;

// Store matching vehicles
for (Vehicle vehicle : allVehicles) {

boolean matches = true;

if (make != null && !make.isEmpty()
&& !vehicle.getMake().equalsIgnoreCase(make)) {
matches = false;
}

if (model != null && !model.isEmpty()
&& !vehicle.getModel().equalsIgnoreCase(model)) {
matches = false;
}

if (vehicle.getPrice() < minPrice
|| vehicle.getPrice() > maxPrice) {
matches = false;
}

if (year > 0 && vehicle.getYear() != year) {
matches = false;
}

if (matches && vehicle.isAvailable()) {
results[index] = vehicle;
index++;
}
}

return results;
}
    public boolean updateVehicle(String listingId,
            String make,
            String model,
            int year,
            double price,
            int mileage,
            String fuelType,
            String transmission,
            String location) {

Vehicle vehicle = vehicleTable.find(listingId);

if (vehicle == null) {
return false;
}

// Validate updated information
if (price <= 0 || mileage < 0 || year < 1886 || year > 2100) {
return false;
}

vehicle.setMake(make);
vehicle.setModel(model);
vehicle.setYear(year);
vehicle.setPrice(price);
vehicle.setMileage(mileage);
vehicle.setFuelType(fuelType);
vehicle.setTransmission(transmission);
vehicle.setLocation(location);

FileManager.saveAllVehicles(vehicleList);

return true;
}
    public User getVehicleSeller(String listingId) {

        Vehicle vehicle = vehicleTable.find(listingId);

        if (vehicle == null) {
            return null;
        }

        return userTable.find(vehicle.getSellerId());
    }
    public User login(String userId, String password) {

        User user = userTable.find(userId);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}