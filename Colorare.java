import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;
public class Colorare {
	static final int Mod = 1000000007; // The modulo value

	public static void main(String[] args) throws FileNotFoundException {
		MyScanner input = new MyScanner(new FileReader("colorare.in"));
		PrintWriter output = new PrintWriter(new File("colorare.out"));

		// Read input
		int K = input.nextInt();
		int[] X = new int[K];
		char[] T = new char[K];

		for (int i = 0; i < K; i++) {
			X[i] = input.nextInt();
			T[i] = input.next().charAt(0);
		}

		long result = calculateColorings(K, X, T);

		output.println(result);
		output.close();
	}

	static long calculateColorings(int K, int[] X, char[] T) {
		long result = 1;

		// Initialize color counts if they are at the begining
		if (T[0] == 'H') {
			result = 6 * pow(3, X[0] - 1, Mod);
		} else if (T[0] == 'V') {
			result = 3 * pow(2, X[0] - 1, Mod);
		}

		// Calculate the number of colorings at the middle
		for (int i = 1; i < K; i++) {
			if (T[i] == 'H' && T[i - 1] == 'H') {
				result *= pow(3, X[i], Mod);
			} else if (T[i] == 'V' && T[i - 1] == 'V') {
				result *= pow(2, X[i], Mod);
			} else if (T[i] == 'H' && T[i - 1] == 'V') {
				result *= 2 * pow(3, X[i] - 1, Mod);
			} else {
				result *= pow(2, X[i] - 1, Mod);
			}
			result %= Mod;
		}
		return result;
	}

	// Calculate the power of a number with modulo
	static long pow(long base, int exponent, int Mod) {
		long result = 1;
		base %= Mod;
		while (exponent > 0) {
			if (exponent % 2 == 1) {
				result = (result * base) % Mod;
			}
			base = (base * base) % Mod;
			exponent /= 2;
		}
		return result;
	}
}

// Custom Scanner class
class MyScanner {
	private BufferedReader br;
	private StringTokenizer st;

	public MyScanner(Reader reader) {
		br = new BufferedReader(reader);
	}

	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}