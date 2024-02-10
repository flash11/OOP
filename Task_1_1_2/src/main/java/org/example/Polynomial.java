package org.example;

import java.util.Arrays;
import java.util.Collection;

/**
 * [1,2,3] translate 3x^2 + 2x + 1
 * [1, 2, 0, 3] translate 3x^3 + 2x + 1
 */

// степени это индексы коэффициентов, причем по нулевому индексу следует слагаемое x^0
public class Polynomial {

    public static void main(String[] args) {
        Polynomial p = new Polynomial(new double[]{1, -2, 3});
        System.out.println(p);
    }
    private double[] coefficients;

// два констуктора
    public Polynomial() {
        this.coefficients = new double[]{0};
    }


    public Polynomial(double[] coefficients) {
        this.coefficients = normalize(coefficients);
    }

    // возвращает скопированный массив нужной длины
    public double[] copyCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    // создаем новый сложенный полином

    public Polynomial plus(Polynomial anotherPolynomial) {
        Polynomial largerPolynomial = this;
        Polynomial smallerPolynomial = anotherPolynomial;

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


// подсчет в точке
    public double evaluate(double arg) {
        double result = 0;
        double x = 1; // отражает степени x
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * x;
            x *= arg;
        }
        return result;
    }


    public Polynomial minus(Polynomial anotherPolynomial) {

        return this.plus(anotherPolynomial.multiply(-1));
    }

    // нужно для минуса
    public Polynomial multiply(double scalar) {
        double[] newCoefficients = this.copyCoefficients();
        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] *= scalar;
        }
        return new Polynomial(newCoefficients);
    }

    // дает максимальную степень, избавляемся от старших нулей
    private static double[] normalize(double[] coefficients) {
        int maximalDegree = coefficients.length - 1;

        while (maximalDegree > 0 && coefficients[maximalDegree] == 0) {
            maximalDegree --;
        }
        return Arrays.copyOf(coefficients, maximalDegree + 1);
    }


    // сравнение двух полиномов

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

    // РАСПЕЧАТЫВАНИЕ ПОЛИНОМА
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
        String powerDegreePart = String.format("^%s", powerDegree);
        if (coef == 1.) {
            coefPart = "";
        }

        if (powerDegree == 1) {
            powerDegreePart = "";
        }
        return String.format("%sx%s", coefPart, powerDegreePart);
    }

}
