import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;
public class Compresie {
	public static void main(String[] args) throws FileNotFoundException {
		MyScanner input = new MyScanner(new FileReader("compresie.in"));
		PrintWriter output = new PrintWriter(new File("compresie.out"));

		// Read input
		int N = input.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = input.nextInt();
		}

		int M = input.nextInt();
		int[] B = new int[M];
		for (int i = 0; i < M; i++) {
			B[i] = input.nextInt();
		}

		int result = findMaxLength(A, B);
		output.println(result);
		output.close();
	}

	// Find the maximum number of segments that can be matched
	private static int findMaxLength(int[] A, int[] B) {
		int i = 0, j = 0;
		int match = 0;

		while (i < A.length && j < B.length) {
			long sumA = A[i];
			long sumB = B[j];

			// Increment pointers to build sums until they match
			while (i < A.length && j < B.length && sumA != sumB) {
				if (sumA < sumB) {
					i++;
					sumA += A[i];
				} else if (sumB < sumA) {
					j++;
					sumB += B[j];
				} else {
					break;
				}
			}

			// Check if we have a match
			if (sumA == sumB) {
				match += 1;  // Increment matched segment count
				i++;  // Move both pointers forward
				j++;
			} else {
				return -1;
			}
		}

		// If one or both of the arrays still have elements left unmatched, we return -1
		if (i < A.length || j < B.length) {
			return -1;
		}

		return match;
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
