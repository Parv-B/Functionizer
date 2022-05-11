package com.example.demo;

import java.text.DecimalFormat;

public class Polynomial {
    long id;
    double[] arr;

    private int sigDigits = 10;

    public Polynomial(double[] arr) {
        this.arr = arr;
        id = 0;
    }

    public Polynomial add(Polynomial a) {
        double[] arr1 = this.arr;
        double[] arr2 = a.arr;
        double[] arr3 = new double[arr1.length > arr2.length ? arr1.length : arr2.length];
        for (int i = 0; i < arr3.length; i++) {
            if (i < arr1.length)
                arr3[i] = arr1[i];
            if (i < arr2.length)
                arr3[i] = arr2[i] + arr3[i];
        }
        return new Polynomial(arr3);
    }

    public Polynomial subtract(Polynomial a) {
        double[] arr1 = this.arr;
        double[] arr2 = a.arr;
        double[] arr3 = new double[arr1.length > arr2.length ? arr1.length : arr2.length];
        for (int i = 0; i < arr3.length; i++) {
            if (i < arr1.length)
                arr3[i] = arr1[i];
            if (i < arr2.length)
                arr3[i] = arr2[i] - arr3[i];
        }
        return new Polynomial(arr3);
    }

    public Polynomial multiply(Polynomial B) {
        int m = this.arr.length;
        int n = B.arr.length;
        double[] C = new double[(int) (m + n - 1)];
        for (int i = 0; i < m + n - 1; i++) {
            C[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i + j] = C[i + j] + this.arr[i] * B.arr[j];
            }
        }
        return new Polynomial(C);
    }

    public Polynomial divideByConst(double a) {
        Polynomial B = new Polynomial(this.arr);
        for (int i = 0; i < arr.length; i++) {
            B.arr[i] = arr[i] / a;
        }
        return B;
    }

    // //divide
    // public double[] divideBy (double[] denominator) {
    // double[] quotient = new double[(this.arr.length-1)/(denominator.length-1)];
    // for (int i = 0; i < quotient.length; i++) {
    // quotient[i]=this.arr[i]/denominator[i];
    // }
    // return quotient;
    // }

    // //factorize the polynomial
    // public double[][] factorize () {
    // return null;
    // }

    // to string with x
    public String toString() {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                str = str + arr[i];
            } else {
                if (arr[i] > 0) {
                    str = str + " + " + arr[i] + "x^" + i;
                } else {
                    str = str + " - " + Math.abs(arr[i]) + "x^" + i;
                }
            }
        }
        return str;
    }

    // find value for specific x
    public double findValue(double x) {
        double value = 0;
        for (int i = 0; i < arr.length; i++) {
            value += arr[i] * Math.pow(x, i);
        }
        return value;
    }

    // using recursion
    public Polynomial raisedToPower(int a) {
        if (a == 0) {
            return new Polynomial(new double[] { 1 });
        } else {
            Polynomial temp = this.raisedToPower(a - 1);
            return this.multiply(temp);
        }
    }

    public Polynomial approximate() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(sigDigits);
        double[] arr = new double[this.arr.length];
        Polynomial approx = new Polynomial(arr);

        for (int i = 0; i < arr.length; i++) {
            approx.arr[i] = Double.parseDouble(df.format(this.arr[i]));
        }
        return approx;
    }

    public long getId() {
        return id;
    }

    public double[] getArr() {
        return arr;
    }
}
