import java.io.*;
import java.util.*;

public class Colorare {
    static final int MOD = 1000000007; // The modulo value

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("colorare.in"));
        PrintWriter output = new PrintWriter(new File("colorare.out"));

        int K = input.nextInt(); // Number of groups
        int[] X = new int[K]; // Array to store the number of zones
        char[] T = new char[K]; // Array to store the type of zones (H or V)

        for (int i = 0; i < K; i++) {
            X[i] = input.nextInt();
            T[i] = input.next().charAt(0);
        }

        long result = calculateColorings(K, X, T);

        output.println(result);
        output.close();
        input.close();
    }

    static long calculateColorings(int K, int[] X, char[] T) {
        long horizontal = 1; // Start with one horizontal segment
        long vertical = 1; // Start with one vertical segment

        // Initialize color counts for the first zone
        if (T[0] == 'H') {
            horizontal = (2 * pow(3, X[0], MOD)) % MOD; // Horizontal can be extended by 2 colors
            vertical = (2 * pow(3, X[0] - 1, MOD)) % MOD; // Vertical can follow horizontal
        } else {
            horizontal = (2 * pow(3, X[0] - 1, MOD)) % MOD; // Horizontal can follow vertical
            vertical = (2 * pow(3, X[0], MOD)) % MOD; // Vertical can be extended by 2 colors
        }

        for (int i = 1; i < K; i++) {
            long newHorizontal, newVertical;

            if (T[i] == 'H') {
                newHorizontal = (2 * horizontal) % MOD; // Extend horizontal by 2 colors
                newVertical = (2 * vertical) % MOD; // Horizontal can follow vertical
                horizontal = (newHorizontal * pow(3, X[i] - 1, MOD)) % MOD; // Apply the power for the length of the segment
                vertical = (newVertical * pow(3, X[i] - 1, MOD)) % MOD;
            } else {
                newHorizontal = (2 * vertical) % MOD; // Vertical can follow horizontal
                newVertical = (2 * horizontal + vertical) % MOD; // Extend vertical by 2 colors
                horizontal = (newHorizontal * pow(3, X[i] - 1, MOD)) % MOD;
                vertical = (newVertical * pow(3, X[i], MOD)) % MOD; // Apply the power for the length of the segment
            }
        }

        long result = (horizontal + vertical) % MOD;
        return result;
    }

    static long pow(long base, int exponent, int MOD) {
        long result = 1;
        base %= MOD;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }
        return result;
    }
}
