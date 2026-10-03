package marketplace;

public class VehicleList {

    private Vehicle[] vehicles;
    private int size;

    public VehicleList() {
        vehicles = new Vehicle[10];
        size = 0;
    }

    public void add(Vehicle vehicle) {

        if (size == vehicles.length) {
            resize();
        }

        vehicles[size] = vehicle;
        size++;
    }

    private void resize() {

        Vehicle[] newVehicles = new Vehicle[vehicles.length * 2];

        for (int i = 0; i < vehicles.length; i++) {
            newVehicles[i] = vehicles[i];
        }

        vehicles = newVehicles;
    }

    public Vehicle get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }

        return vehicles[index];
    }
    public boolean remove(String listingId) {

        for (int i = 0; i < size; i++) {

            if (vehicles[i].getListingId().equals(listingId)) {

                // Shift remaining vehicles one position left
                for (int j = i; j < size - 1; j++) {
                    vehicles[j] = vehicles[j + 1];
                }

                vehicles[size - 1] = null;
                size--;

                return true;
            }
        }

        return false;
    }
    public int size() {
        return size;
    }

    public Vehicle[] toArray() {

        Vehicle[] result = new Vehicle[size];

        for (int i = 0; i < size; i++) {
            result[i] = vehicles[i];
        }

        return result;
    }
}