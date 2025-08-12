package assn01;

import java.util.Scanner;

public class JavaStarter {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("\nEnter an integer from 1 to 6:");
        int n = s.nextInt();


        if (n == 1) {
            System.out.println("Hello, World\n");
        } else if (n == 2) {
            System.out.println("\nEnter a double:");
            double a = s.nextDouble();
            System.out.println("Enter another double:");
            double b = s.nextDouble();
            System.out.println("Enter another integer:");
            int m = s.nextInt();
            System.out.println("\na = " + a + ", b = " + b + ", m = " + m);
        } else if (n == 3) {
            System.out.println("\nEnter a double:");
            double a = s.nextDouble();
            System.out.println("Enter another double:");
            double b = s.nextDouble();
            System.out.println("Enter another integer:");
            int m = s.nextInt();
            System.out.println(quadFun(a, b, m));
        } else if (n == 4) {
            System.out.println("\nEnter a string:");
            String s1 = s.next();
            System.out.println(stringFun1(s1));
        } else if (n == 5) {
            System.out.println("\nEnter a string:");
            String s1 = s.next();
            System.out.println("Enter an integer:");
            int i1 = s.nextInt();
            System.out.println("Enter another integer:");
            int i2 = s.nextInt();
            System.out.println("Enter another integer:");
            int i3 = s.nextInt();
            System.out.println("Enter another integer:");
            int i4 = s.nextInt();
            System.out.println(stringFun2(s1, i1, i2, i3, i4));
        } else if (n == 6) {
            System.out.println("\nEnter an integer:");
            int m = s.nextInt();
            double[] arr = new double[m];

            for (int i = 0; i < m; i++) {
                System.out.println("Enter a real number:");
                double num = s.nextDouble();
                arr[i] = num;
            }

            double sum = 0;
            for (int i = 0; i < m; i++) {
                sum += arr[i];
            }
            double average = sum/m;
            float averageFloat = (float) average;
            System.out.printf("Average = %.2f\n", averageFloat);

            for (int i = m-1; i >= 0; i--) {
                System.out.println("arr[" + i + "]=" + arr[i]);
            }
        }
    }
    public static double quadFun(double a, double b, int m) {
        double result = (a * Math.pow(m, 2)) + b;
        return Math.round(result * 10.0) / 10.0;
    }
    public static String stringFun1(String s1) {
        return s1.toUpperCase();
    }
    public static String stringFun2(String s1, int i1, int i2, int i3, int i4) {
        String sA = s1.substring(i1, i2);
        String sB = s1.substring(i3, i4);

        return sA + sB;
    }
}