import java.io.*;
import java.util.*;

public class Servere {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("servere.in"));
        PrintWriter output = new PrintWriter(new File("servere.out"));

        int N = input.nextInt();
        double[] P = new double[N];
        double[] C = new double[N];

        for (int i = 0; i < N; i++) {
            P[i] = input.nextDouble();
        }

        for (int i = 0; i < N; i++) {
            C[i] = input.nextDouble();
        }

        double[] sortedC = Arrays.copyOf(C, C.length);
        Arrays.sort(sortedC);

        double left = sortedC[0];
        double right = sortedC[N - 1];
        double bestMinimumOutput = Double.NEGATIVE_INFINITY;
        int iterations = 100; // Fixed number of iterations for the binary search

        for(int i = 0; i < iterations; i++) {
            double mid = (left + right) / 2;
            double minOutputAtMid = findMinOutput(P, C, mid);
            double minOutputAtMidPlusEpsilon = findMinOutput(P, C, mid + 1e-4);

            if (minOutputAtMidPlusEpsilon > minOutputAtMid) {
                left = mid;
            } else {
                right = mid;
            }
            bestMinimumOutput = minOutputAtMid;
        }

        output.printf("%.1f\n", bestMinimumOutput);
        output.close();
        input.close();
    }

    private static double findMinOutput(double[] P, double[] C, double x) {
        double minOutput = Double.MAX_VALUE;
        for (int i = 0; i < C.length; i++) {
            double output = P[i] - Math.abs(x - C[i]);
            minOutput = Math.min(minOutput, output);
        }
        return minOutput;
    }
}