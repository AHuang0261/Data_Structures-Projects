package hw3;

public class PrimeCalculator {
	ArrayQueue<Integer> numbers;
	ArrayQueue<Integer> primes;

	public PrimeCalculator() {
		numbers = new ArrayQueue<Integer>();
		primes = new ArrayQueue<Integer>();
	}

	public void primesTo(int n) throws IllegalArgumentException {
		if (n < 2)
			throw new IllegalArgumentException("Error: Input must be a number greater than or equal to 2.");
		System.out.println("printing primes up to " + n + ": ");
		//filling up the number queue with all ints up to n
		for (int i = 2; i <= n; i++) {
			numbers.enqueue(i);
		}
		//Adding to primes and emptying numbers
		while (!numbers.isEmpty()) {
			int p = numbers.dequeue();
			primes.enqueue(p);
			int preModificationSize = numbers.size();
			for (int i = 0; i < preModificationSize; i++) {
				int temp = numbers.dequeue();
				if (temp % p != 0)
					numbers.enqueue(temp);
			}
		}
		//printing
		String out = "";
		while (!primes.isEmpty()) {
			out += primes.dequeue() + ", ";
		}
		out = out.substring(0, out.length() - 2) + ".";
		System.out.println(out + "\n");
	}

	public static void main(String[] args) {
		PrimeCalculator a = new PrimeCalculator();
		try {
			a.primesTo(20);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			a.primesTo(2);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			a.primesTo(0);
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}
}
