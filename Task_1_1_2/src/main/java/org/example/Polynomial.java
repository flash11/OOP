package org.example;

import java.util.Arrays;
import java.util.Collection;

/**
 * [1,2,3] -> 3x^3 + 2x^2 + 1
 */
public class Polynomial {
    private double[] coefficients;

    public Polynomial() {
        coefficients = new double[]{1};
    }

    public Polynomial(double x) {
        coefficients = new double[]{x};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = normalize(coefficients);
    }

    public double[] copyCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    public Polynomial plus(Polynomial anotherPolynomial) {
        Polynomial largerPolynomial = this, smallerPolynomial = anotherPolynomial;
        if (this.coefficients.length < anotherPolynomial.coefficients.length) {
            largerPolynomial = anotherPolynomial;
            smallerPolynomial = this;
        }
        double[] newCoefficients = largerPolynomial.copyCoefficients();
        for (int i = 0; i < smallerPolynomial.coefficients.length; i++) {
            newCoefficients[i] += smallerPolynomial.coefficients[i];
        }
        return new Polynomial(newCoefficients);
    }

    public Polynomial differentiate(int times) {
        if (times < 0) {
            throw new IllegalArgumentException("negative differentiation degree");
        }
        if (times >= coefficients.length) {
            return new Polynomial(new double[]{0});
        }
        double[] newCoefficients = new double[coefficients.length - times];

        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] = coefficients[i + times] * getDifferentiateCoefficient(i + times, times);
        }
        return new Polynomial(newCoefficients);
    }

    public double evaluate(double arg) {
        double result = 0;
        double x = 1;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * x;
            x *= arg;
        }
        return result;
    }

    public Polynomial times(Polynomial anotherPolynomial) {
        double[] anotherCoefficients = anotherPolynomial.coefficients;
        int newLength = this.coefficients.length + anotherPolynomial.coefficients.length - 1;
        double[] newCoefficients = new double[newLength];

        for (int i = 0; i < anotherCoefficients.length; i++) {
            for (int j = 0; j < coefficients.length; j++) {
                newCoefficients[i + j] += coefficients[j] * anotherCoefficients[i];
            }
        }
        return new Polynomial(newCoefficients);

    }

    public Polynomial minus(Polynomial anotherPolynomial) {
        return this.plus(anotherPolynomial.multiply(-1));
    }

    public Polynomial multiply(double scalar) {
        double[] newCoefficients = this.copyCoefficients();
        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] *= scalar;
        }
        return new Polynomial(newCoefficients);
    }


    private static double[] normalize(double[] coefficients) {
        int maximalDegree = coefficients.length - 1;
        for (int i = coefficients.length - 1; i > 0 && coefficients[maximalDegree] == 0.; i--) {
            if (coefficients[i] == 0) {
                maximalDegree = i - 1;
                break;
            }
        }
        return Arrays.copyOf(coefficients, maximalDegree + 1);
    }


    private static double getDifferentiateCoefficient(int powerDegree, int times) {
        double res = 1;
        for (int i = 0; i < times; i++) {
            res *= powerDegree;
            powerDegree--;
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Polynomial) {
            return Arrays.equals(this.coefficients, ((Polynomial) obj).coefficients);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int last = coefficients.length - 1;
        stringBuilder.append(getMemberRepresentation(coefficients[last], last));
        last--;
        for (; last >= 0; last--) {
            if (coefficients[last] == 0) {
                continue;
            }
            stringBuilder.append(" + ");
            stringBuilder.append(getMemberRepresentation(coefficients[last], last));
        }
        return stringBuilder.toString();
    }

    private static String getMemberRepresentation(double coef, int powerDegree) {
        if (powerDegree == 0) {
            return Double.toString(coef);
        }
        String coefPart = Double.toString(coef);
        String powerDegreePart = "^%s".formatted(powerDegree);
        if (coef == 1.) {
            coefPart = "";
        }

        if (powerDegree == 1) {
            powerDegreePart = "";
        }
        return "%sx%s".formatted(coefPart, powerDegreePart);
    }

    public static Polynomial of(double... args) {
        return new Polynomial(args);
    }
}
