package org.example;

import java.util.Arrays;

/*
 * [1,2,3] translate 3x^2 + 2x + 1
 * [1, 2, 0, 3] translate 3x^3 + 2x + 1
 */

// Cтепени это индексы коэффициентов, причем по нулевому индексу следует слагаемое x^0=1

/**
 * Polynomial class.
 */
public class Polynomial {

    private double[] coefficients;


    // два констуктора
    public Polynomial() {
        this.coefficients = new double[]{0};
    }


    public Polynomial(double[] coefficients) {
        this.coefficients = normalize(coefficients);
    }

    /**
     * getter.
     *
     * @return coeff.
     */
    public double[] getCoefficients() {
        return coefficients;
    }


    /**
     * copy coefficients(array).
     *
     * @return copied version.
     */
    public double[] copyCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    /**
     * Get rid of maximal degree with zero coef.
     *
     * @param coefficients polynomial.
     * @return polynomial without last zeroes.
     */
    private static double[] normalize(double[] coefficients) {
        int maximalDegree = coefficients.length - 1;

        while (maximalDegree > 0 && coefficients[maximalDegree] == 0) {
            maximalDegree--;
        }
        return Arrays.copyOf(coefficients, maximalDegree + 1);
    }

    /**
     * Create new polynomial with addition other.
     * Using copyCoefficients.
     *
     * @param anotherPolynomial input.
     * @return new polynomial.
     */
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


    /**
     * Calculate concrete X.
     * Multiply x every time self.
     *
     * @param arg argument.
     * @return result double type.
     */
    public double calculate(double arg) {
        double result = 0;
        double x = 1; // отражает степени x
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * x;
            x *= arg;
        }
        return result;
    }


    /**
     * Minus method.
     *
     * @param anotherPolynomial input.
     * @return negative polynomial.
     */
    public Polynomial minus(Polynomial anotherPolynomial) {

        return this.plus(anotherPolynomial.multiply(-1));
    }

    /**
     * Minus helper.
     *
     * @param scalar usualy -1.
     * @return multiplied polynomial.
     */
    public Polynomial multiply(double scalar) {
        double[] newCoefficients = this.copyCoefficients();
        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] *= scalar;
        }
        return new Polynomial(newCoefficients);
    }


    /**
     * Comparing two polynomials.
     *
     * @param obj Object.
     * @return equals or not.
     */
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


    /**
     * method for one variable.
     *
     * @return full string.
     */
    @Override
    public String toString() {
        return getStringWithSpecialLetter("x");
    }


    /**
     * formatting string of coefficients.
     *
     * @param varName any but x in this case.
     * @return polynomial.
     */
    public String getStringWithSpecialLetter(String varName) {
        StringBuilder stringBuilder = new StringBuilder();
        int last = coefficients.length - 1;
        stringBuilder.append(getMemberRepresentation(varName, coefficients[last], last));
        last--;
        for (; last >= 0; last--) {
            if (coefficients[last] == 0) {
                continue;
            }
            stringBuilder.append(" + ");
            stringBuilder.append(getMemberRepresentation(varName, coefficients[last], last));
        }
        return stringBuilder.toString();
    }

    /**
     * each unit.
     *
     * @param varName x or any.
     * @param coef in array.
     * @param powerDegree index of array.
     * @return unit of polynomial.
     */
    private static String getMemberRepresentation(String varName, double coef, int powerDegree) {
        if (powerDegree == 0) {
            return Double.toString(coef);
        }
        String coefPart = Double.toString(coef);
        String powerDegreePart = String.format("^%s", powerDegree);
        if (coef == 1) {
            coefPart = "";
        }

        if (powerDegree == 1) {
            powerDegreePart = "";
        }
        return String.format("%s%s%s", coefPart, varName, powerDegreePart);

    }

}
