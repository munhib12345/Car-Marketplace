package marketplace;

public class VehicleHashTable {

    private static class Node {
        Vehicle vehicle;
        Node next;

        Node(Vehicle vehicle) {
            this.vehicle = vehicle;
        }
    }

    private Node[] table;
    private int size;

    public VehicleHashTable(int capacity) {
        table = new Node[capacity];
        size = 0;
    }

    private int hash(String listingId) {
        return Math.abs(listingId.hashCode()) % table.length;
    }

    public boolean add(Vehicle vehicle) {

        int index = hash(vehicle.getListingId());

        Node current = table[index];

        while (current != null) {

            if (current.vehicle.getListingId().equals(vehicle.getListingId())) {
                return false;
            }

            current = current.next;
        }

        Node newNode = new Node(vehicle);
        newNode.next = table[index];
        table[index] = newNode;

        size++;

        return true;
    }

    public Vehicle find(String listingId) {

        int index = hash(listingId);

        Node current = table[index];

        while (current != null) {

            if (current.vehicle.getListingId().equals(listingId)) {
                return current.vehicle;
            }

            current = current.next;
        }

        return null;
    }

    public boolean remove(String listingId) {

        int index = hash(listingId);

        Node current = table[index];
        Node previous = null;

        while (current != null) {

            if (current.vehicle.getListingId().equals(listingId)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }
}