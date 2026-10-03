package marketplace;

public class MergeSort {

	public static void sortByPrice(Vehicle[] vehicles, boolean ascending) {

	    if (vehicles == null || vehicles.length < 2) {
	        return;
	    }

	    mergeSortByPrice(vehicles, 0, vehicles.length - 1, ascending);
	}

	private static void mergeSortByPrice(Vehicle[] vehicles,
	                                     int left,
	                                     int right,
	                                     boolean ascending) {

	    if (left >= right) {
	        return;
	    }

	    int middle = left + (right - left) / 2;

	    mergeSortByPrice(vehicles, left, middle, ascending);
	    mergeSortByPrice(vehicles, middle + 1, right, ascending);

	    mergeByPrice(vehicles, left, middle, right, ascending);
	}

	private static void mergeByPrice(Vehicle[] vehicles,
	                                 int left,
	                                 int middle,
	                                 int right,
	                                 boolean ascending) {

	    Vehicle[] temp = new Vehicle[right - left + 1];

	    int i = left;
	    int j = middle + 1;
	    int k = 0;

	    while (i <= middle && j <= right) {

	        boolean takeLeft;

	        if (ascending) {
	            takeLeft = vehicles[i].getPrice() <= vehicles[j].getPrice();
	        } else {
	            takeLeft = vehicles[i].getPrice() >= vehicles[j].getPrice();
	        }

	        if (takeLeft) {
	            temp[k] = vehicles[i];
	            i++;
	        } else {
	            temp[k] = vehicles[j];
	            j++;
	        }

	        k++;
	    }

	    while (i <= middle) {
	        temp[k] = vehicles[i];
	        i++;
	        k++;
	    }

	    while (j <= right) {
	        temp[k] = vehicles[j];
	        j++;
	        k++;
	    }

	    for (int x = 0; x < temp.length; x++) {
	        vehicles[left + x] = temp[x];
	    }
	}
	public static void sortByYear(Vehicle[] vehicles, boolean ascending) {

	    if (vehicles == null || vehicles.length < 2) {
	        return;
	    }

	    mergeSortByYear(vehicles, 0, vehicles.length - 1, ascending);
	}

	private static void mergeSortByYear(Vehicle[] vehicles,
	                                    int left,
	                                    int right,
	                                    boolean ascending) {

	    if (left >= right) {
	        return;
	    }

	    int middle = left + (right - left) / 2;

	    mergeSortByYear(vehicles, left, middle, ascending);
	    mergeSortByYear(vehicles, middle + 1, right, ascending);

	    mergeByYear(vehicles, left, middle, right, ascending);
	}

	private static void mergeByYear(Vehicle[] vehicles,
	                                int left,
	                                int middle,
	                                int right,
	                                boolean ascending) {

	    Vehicle[] temp = new Vehicle[right - left + 1];

	    int i = left;
	    int j = middle + 1;
	    int k = 0;

	    while (i <= middle && j <= right) {

	        boolean takeLeft;

	        if (ascending) {
	            takeLeft = vehicles[i].getYear() <= vehicles[j].getYear();
	        } else {
	            takeLeft = vehicles[i].getYear() >= vehicles[j].getYear();
	        }

	        if (takeLeft) {
	            temp[k] = vehicles[i];
	            i++;
	        } else {
	            temp[k] = vehicles[j];
	            j++;
	        }

	        k++;
	    }

	    while (i <= middle) {
	        temp[k] = vehicles[i];
	        i++;
	        k++;
	    }

	    while (j <= right) {
	        temp[k] = vehicles[j];
	        j++;
	        k++;
	    }

	    for (int x = 0; x < temp.length; x++) {
	        vehicles[left + x] = temp[x];
	    }
	}
}