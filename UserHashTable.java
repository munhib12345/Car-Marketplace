package marketplace;

public class UserHashTable {

    private static class Node {
        User user;
        Node next;

        Node(User user) {
            this.user = user;
        }
    }

    private Node[] table;
    private int size;

    public UserHashTable(int capacity) {
        table = new Node[capacity];
        size = 0;
    }

    private int hash(String userId) {
        return Math.floorMod(userId.hashCode(), table.length);
    }

    public boolean add(User user) {

        int index = hash(user.getUserId());

        Node current = table[index];

        while (current != null) {

            if (current.user.getUserId().equals(user.getUserId())) {
                return false;
            }

            current = current.next;
        }

        Node newNode = new Node(user);
        newNode.next = table[index];
        table[index] = newNode;

        size++;

        return true;
    }

    public User find(String userId) {

        int index = hash(userId);

        Node current = table[index];

        while (current != null) {

            if (current.user.getUserId().equals(userId)) {
                return current.user;
            }

            current = current.next;
        }

        return null;
    }

    public boolean remove(String userId) {

        int index = hash(userId);

        Node current = table[index];
        Node previous = null;

        while (current != null) {

            if (current.user.getUserId().equals(userId)) {

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