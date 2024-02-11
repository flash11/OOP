package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiPolynomial {
    /*
    Основная идея в том, что рассматриваем полином с несколькими переменными как сумму простых полиномов,
    каждому из которых соответствует своя переменная. Имя переменной определяется её позицией в списке. Таким образом, если мы хотим,
    чтобы в полиноме были только слагаемые с переменной v2, мы должны передать 2 пустых массива, а потом тот, в котором коэффициенты полинома переменной v2
     */
    ArrayList<Polynomial> polynomials;

    public MultiPolynomial(double[][] coeffs) {
        polynomials = new ArrayList<>();
        for (double[] coeff : coeffs) {
            polynomials.add(new Polynomial(coeff));
        }
        if (coeffs.length == 0) {
            polynomials.add(new Polynomial());
        }
    }

    public MultiPolynomial(ArrayList<Polynomial> polynomials) {
        this.polynomials = new ArrayList<>();
        for (var polynomial : polynomials) {
            this.polynomials.add(new Polynomial(polynomial.copyCoefficients()));
        }
    }

    /*
    Получаем массив значений переменных и суммируем результаты evaluate для каждого простого полинома
     */
    public double evaluate(double[] vars) {
        int polynomialsCount = polynomials.size();
        if (vars.length != polynomialsCount) {
            throw new IllegalArgumentException("Count of variables' values isn't correct");
        }
        double result = 0;
        for (int i = 0; i < polynomialsCount; i++) {
            result += polynomials.get(i).evaluate(vars[i]);
        }
        return result;
    }

    /*
    Принцип работы достаточно похож на плюс для простого полинома:
    - Выбираем, какой из мультиполиномов длинне по количеству простых полиномов
    - Пока у нас имеются простые полиномы в обоих мультиполиномах, проводим суммирование
    - Далее копируем ссылки от большего мультиполинома (то есть суммируем с нулевыми полиномами)
    - Создаём на основании полученного нового списка просуммированных полиномов новый мультиполином
     */
    public MultiPolynomial plus(MultiPolynomial anotherMultiPolynomial) {
        MultiPolynomial largerMultiPolynomial = this;
        MultiPolynomial smallerMultiPolynomial = anotherMultiPolynomial;
        if (this.polynomials.size() < anotherMultiPolynomial.polynomials.size()) {
            largerMultiPolynomial = anotherMultiPolynomial;
            smallerMultiPolynomial = this;
        }
        ArrayList<Polynomial> newPolynomials = new ArrayList<>();
        int largestMultiPolynomialsSize = largerMultiPolynomial.polynomials.size();
        int smallerMultiPolynomialsSize = smallerMultiPolynomial.polynomials.size();
        for (int i = 0; i < largestMultiPolynomialsSize; i++) {
            if (i < smallerMultiPolynomialsSize) {
                newPolynomials.add(
                        largerMultiPolynomial.polynomials.get(i).plus(
                                smallerMultiPolynomial.polynomials.get(i)
                        )
                );
            }
            else {
                newPolynomials.add(
                        largerMultiPolynomial.polynomials.get(i)
                );
            }
        }
        return new MultiPolynomial(newPolynomials);
    }

    // И тут всё работает также,
    public MultiPolynomial minus(MultiPolynomial anotherMultiPolynomial) {
        return this.plus(anotherMultiPolynomial.multiply(-1));
    }

    // Ну... Тут мы просто домножаем на скаляр все полиномы мультиполинома
    public MultiPolynomial multiply(double scalar) {
        ArrayList<Polynomial> newPolynomials = new ArrayList<>();
        for (var polynomial : polynomials) {
            newPolynomials.add(polynomial.multiply(scalar));
        }
        return new MultiPolynomial(newPolynomials);
    }

    @Override
    public String toString() {
        int polynomialsCount = polynomials.size();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < polynomialsCount; ++i) {
            builder.append(polynomials.get(i).getStringWithSpecialLetter(
                    String.format("v%d", i)
            ));
            if (i < polynomialsCount - 1) {
                builder.append(" + ");
            }
        }
        return builder.toString();
    }

    private static final double SMALL_DOUBLE = 0.000000000001;
    /*
    Сравнение оказалось несколько сложнее, чем предполагалось изначально:
    - Сравниваем полиномы внутри мультиполинома и считаем их подходящими, если:
        1. Длина одинакова
        2. Все коэффициенты при степенях переменной выше нулевой, равны
        3. Коэффициенты при нулевой степени суммируем для всех полиномов в двух мультиполиномах
    - Если сумма коэффициентов при нулевых степенях равны, то заключаем, что равны и мультиполиномы
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MultiPolynomial) {
            int minPolynomialsSize = polynomials.size();
            int maxPolynomialsSize = ((MultiPolynomial) obj).polynomials.size();
            ArrayList<Polynomial> minPolynomials = polynomials;
            ArrayList<Polynomial> maxPolynomials = ((MultiPolynomial) obj).polynomials;
            if (minPolynomialsSize > maxPolynomialsSize) {
                minPolynomialsSize = ((MultiPolynomial) obj).polynomials.size();
                maxPolynomialsSize = polynomials.size();
                minPolynomials = ((MultiPolynomial) obj).polynomials;
                maxPolynomials = polynomials;
            }
            double zeroDegreesSum1 = 0, zeroDegreesSum2 = 0;
            // Проверяем полиномы, которые представлены в обоих мультиполиномах
            for (int i = 0; i < minPolynomialsSize; i++) {
                if (minPolynomials.get(i).getCoefficients().length != maxPolynomials.get(i).getCoefficients().length) {
                    return false;
                }
                // Можем проверять только одну длину, т.к. равенство длин уже проверили
                if (minPolynomials.get(i).getCoefficients().length > 1 &&
                    !Arrays.equals(
                        minPolynomials.get(i).getCoefficients(),
                        1, minPolynomials.get(i).getCoefficients().length,
                        maxPolynomials.get(i).getCoefficients(),
                        1, maxPolynomials.get(i).getCoefficients().length
                    )
                ) {
                    return false;
                }
                zeroDegreesSum1 += minPolynomials.get(i).getCoefficients()[0];
                zeroDegreesSum2 += maxPolynomials.get(i).getCoefficients()[0];
            }
            // Если один из мультиполиномов содержит больше полиномов, чем другой, их также необходимо просуммировать, при этом в этих полиномах может быть только коэффициент при нулевой степени переменной
            for (int i = minPolynomialsSize; i < maxPolynomialsSize; i++) {
                if (maxPolynomials.get(i).getCoefficients().length > 1)
                    return false;
                zeroDegreesSum2 += maxPolynomials.get(i).getCoefficients()[0];
            }
            return Math.abs(zeroDegreesSum1 - zeroDegreesSum2) < SMALL_DOUBLE;
        }
        return false;
    }

    public static void main(String[] args) {
        MultiPolynomial p1 = new MultiPolynomial(new double[][]{
                new double[]{4},
                new double[]{6, 8, 10},
                new double[]{0},
                new double[]{0, 8, 10},
                new double[]{2},
        });
        MultiPolynomial p2 = new MultiPolynomial(new double[][]{
                new double[]{1},
                new double[]{2, 8, 10},
                new double[]{3},
                new double[]{6, 8, 10},
        });
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.equals(p2));
        System.out.println(p2.evaluate(new double[]{5, 1, 10, 2}));
    }
}

/*
[
    poly0, poly1, poly2, ..., polyN
]
poly1 = a_n * var1^n + a_{n-1} * var1^{n-1} + ... + a_1 * var1 + a_0
poly2 = b_n * var2^n + b_{n-1} * var2^{n-1} + ... + b_1 * var2 + b_0

mp0 = [
    poly0, poly1, poly2
]
mp1 = [
    poly0, poly1, poly2, poly3
]
 */