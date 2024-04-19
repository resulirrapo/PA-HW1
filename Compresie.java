import java.io.*;
import java.util.*;

public class Compresie {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("compresie.in"));
        PrintWriter output = new PrintWriter(new File("compresie.out"));

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
        input.close();
    }

    private static int findMaxLength(int[] A, int[] B) {
        int i = 0, j = 0;
        int matchedLength = 0;

        while (i < A.length && j < B.length) {
            long sumA = A[i];
            long sumB = B[j];

            // Increment pointers to build sums until they match
            while (i < A.length && j < B.length && sumA != sumB) {
                if (sumA < sumB && i + 1 < A.length) {
                    i++;
                    sumA += A[i];
                } else if (sumB < sumA && j + 1 < B.length) {
                    j++;
                    sumB += B[j];
                } else {
                    break;  // Break if sums can't be matched
                }
            }

            // Check if we have a match
            if (sumA == sumB) {
                matchedLength += 1;  // Increment matched segment count
                i++;  // Move both pointers forward
                j++;
            } else {
                return -1;  // No possible way to compress and match
            }
        }

        // If one or both of the arrays still have elements left unmatched, return -1
        if (i < A.length || j < B.length) {
            return -1;
        }

        return matchedLength;
    }
}
