package com.example.demo;

public class Functionizer {
    public static Polynomial functionize(double[][] combined) {
        System.out.println("I was called");
        Polynomial result = new Polynomial(new double[] { 0 });
        for (int i = 0; i < combined.length; i++) {
            Polynomial numr = new Polynomial(new double[] { 1 });
            for (int j = 0; j < combined.length; j++) {
                if (i != j) {
                    Polynomial temp = new Polynomial(new double[] { -combined[j][0], 1 });
                    System.out.println(temp);
                    numr = numr.multiply(temp);
                }
            }
            numr = numr.multiply(new Polynomial(new double[] { combined[i][1] }));
            double denm = 1;
            for (int j = 0; j < combined.length; j++) {
                if (i != j) {
                    denm *= combined[i][0] - combined[j][0];
                    System.out.println(denm);
                }
            }
            result = result.add(numr.divideByConst(denm));
        }
        return result;
    }
}
