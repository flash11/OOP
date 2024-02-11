package org.example;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * Multipolynomial.
 */
public class MultiPolynomial {
    /*
    Полином с несколькими переменными это сумма простых полиномов,
    каждому из которых соответствует своя переменная. 
    Имя переменной определяется её позицией в списке.
    Таким образом, если мы хотим,
    чтобы в полиноме были только слагаемые с переменной v2, 
    мы должны передать 2 пустых массива, а потом тот,
    в котором коэффициенты полинома переменной v2
     */
    ArrayList<Polynomial> polynomials;

    /**
     * Array of arrays.
     * objects Polynomial at object MultiPolynomials.
     * For input from user.
     *
     * @param coeffs array of simple arrays.
     */
    public MultiPolynomial(double[][] coeffs) {
        polynomials = new ArrayList<>();
        for (double[] coeff : coeffs) {
            polynomials.add(new Polynomial(coeff));
        }
        if (coeffs.length == 0) {
            polynomials.add(new Polynomial());
        }
    }

    /**
     * Constructor second.
     * for internal operations.
     *
     * @param polynomials list of simple arrays.
     */
    public MultiPolynomial(ArrayList<Polynomial> polynomials) {
        this.polynomials = new ArrayList<>();
        for (var polynomial : polynomials) {
            this.polynomials.add(new Polynomial(polynomial.copyCoefficients()));
        }
    }


    /**
     * Take all vars for each and calculate.
     *
     * @param vars array.
     * @return result.
     */
    public double evaluate(double[] vars) {
        int polynomialsCount = polynomials.size();
        if (vars.length != polynomialsCount) {
            throw new IllegalArgumentException("Count of variables' values isn't correct");
        }
        double result = 0;
        for (int i = 0; i < polynomialsCount; i++) {
            result += polynomials.get(i).calculate(vars[i]);
        }
        return result;
    }

    /*
    Принцип работы достаточно похож на плюс для простого полинома:
    - Выбираем, какой из мультиполиномов длинее 
      по количеству простых полиномов
    - Пока у нас имеются простые полиномы в обоих мультиполиномах, 
      проводим суммирование
    - Далее копируем ссылки от большего мультиполинома 
      (то есть суммируем с нулевыми полиномами)
    - Создаём на основании полученного нового списка 
      просуммированных полиномов новый мультиполином
     */

    /**
     * Similar with simple plus.
     *
     * @param anotherMultiPolynomial that we want to add.
     * @return result.
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

    /**
     * minus method.
     *
     * @param anotherMultiPolynomial MultiPolynomial.
     * @return result multipolynomial.
     */
    public MultiPolynomial minus(MultiPolynomial anotherMultiPolynomial) {
        return this.plus(anotherMultiPolynomial.multiply(-1));
    }



    /**
     * Multiply.
     *
     * @param scalar double.
     * @return new Multipolynomial.
     */
    public MultiPolynomial multiply(double scalar) {
        ArrayList<Polynomial> newPolynomials = new ArrayList<>();
        for (var polynomial : polynomials) {
            newPolynomials.add(polynomial.multiply(scalar));
        }
        return new MultiPolynomial(newPolynomials);
    }

    /**
     * Present whole polynomial.
     *
     * @return multipolynomial.
     */
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
    Сравниваем полиномы внутри мультиполинома и считаем их равными, если:
        1. Длина одинакова
        2. Все коэффициенты при степенях переменной выше нулевой, равны
        3. Коэффициенты при нулевой степени суммируем 
           для всех полиномов в двух мультиполиномах
           Если сумма коэффициентов при нулевых степенях равны, то заключаем, 
           что равны и мультиполиномы
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
            // Сумма обычных чисел.
            double zeroDegreesSum1 = 0, zeroDegreesSum2 = 0;

            // Проверяем полиномы, которые представлены в обоих мультиполиномах.
            for (int i = 0; i < minPolynomialsSize; i++) {
                if (minPolynomials.get(i).getCoefficients()
                    .length != maxPolynomials.get(i).getCoefficients().length) {
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
            // Если один из мультиполиномов содержит больше полиномов, чем другой,
            // их также необходимо просуммировать, 
            // при этом в этих полиномах может быть только коэффициент
            // при нулевой степени переменной
            for (int i = minPolynomialsSize; i < maxPolynomialsSize; i++) {
                if (maxPolynomials.get(i).getCoefficients().length > 1)
                    return false;
                zeroDegreesSum2 += maxPolynomials.get(i).getCoefficients()[0];
            }
            return Math.abs(zeroDegreesSum1 - zeroDegreesSum2) < SMALL_DOUBLE;
        }
        return false;
    }
}
