import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

// A demonstration of the Heap Sort algorithm
public class HeapSort {
	
	//Track number of comparisons and swaps made
	static long compCount = 0, swapCount = 0; 

	public static void main(String args[]){

		
		for(int size = 10; size <= 1000000; size *=10) {

			//Initialize a new array
			int[] arr  = new int [size];
			
			
			//Read file into the new array 
			int arrayIndex = 0;
			
			try {
				BufferedReader inFile = new BufferedReader(new FileReader("src\\sortme"
						+ size + ".txt"));
				String line  = inFile.readLine();
				
				while(line!=null) {
					arr[arrayIndex] = Integer.parseInt(line);
					arrayIndex++;
					line = inFile.readLine();
				}
				inFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//Time sort and output findings
			System.out.println("-----------------------");
			System.out.println("Sorting sortme"  + size
					+ ".txt");

			// Function call
			Instant start = Instant.now();
			heapSort(arr);
			Instant end = Instant.now();
			double elapsed = Duration.between(start, end).toNanos();


			System.out.println("Time to sort(in ms): " + elapsed/1000000);
			System.out.println("-----------------------\n\n");
			
			//Reset compCount, swapCount
			compCount = 0;
			swapCount = 0;
		}

	}//End main
	

	/* The main function that implements heapSort()
	 * arr[] --> Array to be sorted
	 */
	public static void heapSort(int arr[]){
		
		int N = arr.length;

		// Build heap
		for (int i = (N/2) - 1; i >= 0; i--) {
			//Array to heap
			heapify(arr, N /*10*/ , i /*4*/);
		}

		//Sorts the reduced heap
		for (int i = N - 1; i > 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			// Call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
		
		//Output the number of comparisons and swaps
		System.out.println("Number of Comparisons: " + compCount);
		System.out.println("Number of Swaps: " + swapCount);
		
	}//End heapSort function
	
	
	/* This function creates a max heap
	 * the provided array.
	 * 
	 * N --> length of given array
	 * i --> (N/2) - 1
	 */
	static void heapify(int arr[], int N, int i){
		int largest = i; // root node i
		int left = 2 * i + 1; // left = 2*i + 1
		int right = 2 * i + 2; // right = 2*i + 2

		//	Find the index of the largest number
		// If left child is larger than root
		if (left < N && arr[left] > arr[largest]) {
			largest = left;
		}
		compCount++;
		// If right child is larger than largest so far
		if (right < N && arr[right] > arr[largest]) {
			largest = right;
		}
		compCount++;

		//When the index of the largest has been found
		// If largest is not root
		if (largest != i) {
			swapCount++;
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			// Heapify the affected sub-tree
			heapify(arr, N, largest);
		}

	}//End heapify function

}
