import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Servere {
	public static void main(String[] args) throws FileNotFoundException {
		MyScanner input = new MyScanner(new FileReader("servere.in"));

		// Read input
		int N = input.nextInt();
		double[] P = new double[N];
		double[] C = new double[N];

		for (int i = 0; i < N; i++) {
			P[i] = input.nextDouble();
		}

		for (int i = 0; i < N; i++) {
			C[i] = input.nextDouble();
		}

		// make a copy of C and sort it
		double[] sortedC = Arrays.copyOf(C, C.length);
		Arrays.sort(sortedC);

		double bestMinimum = bestMinimum(P, C, sortedC, N);
		PrintWriter output = new PrintWriter("servere.out");


		output.printf("%.1f\n", bestMinimum);
		output.close();
	}

	// Find the best minimum output using binary search
	static double bestMinimum(double[] P, double[] C, double[] sortedC, int N) {
		double left = sortedC[0];
		double right = sortedC[N - 1];
		double bestMinimum = Double.NEGATIVE_INFINITY;
		int iterations = 100; // Fixed number of iterations for the binary search

		for (int i = 0; i < iterations; i++) {
			double mid = (left + right) / 2;
			double minOutputAtMid = findMinOutput(P, C, mid);
			//  is used to avoid floating point errors
			double minOutputAtMidPrecise = findMinOutput(P, C, mid + 1e-4);

			if (minOutputAtMidPrecise > minOutputAtMid) {
				left = mid;
			} else {
				right = mid;
			}
			bestMinimum = minOutputAtMid;
		}
		return bestMinimum;
	}

	// Find the minimum output for a given value of x
	private static double findMinOutput(double[] P, double[] C, double x) {
		double minOutput = Double.MAX_VALUE;
		for (int i = 0; i < C.length; i++) {
			// formula to calculate the output
			double output = P[i] - Math.abs(x - C[i]);
			minOutput = Math.min(minOutput, output);
		}
		return minOutput;
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
