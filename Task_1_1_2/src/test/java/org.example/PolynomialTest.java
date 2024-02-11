package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * PolynomialTest.
 */
public class PolynomialTest {

    /**
     * representationTest.
     */
    @Test
    public void representationTest() {

        Polynomial polynomial = new Polynomial(new double[]{2, 1});
        Assertions.assertEquals("x + 2.0", polynomial.toString());

        MultiPolynomial multPol = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});
        Assertions.assertEquals("1v0^2 + 2v0 + 3 + 6v1 + 5", multPol.toString());
    }

    /**
     * minusPlusTest.
     */
    @Test
    public void minusPlusTest() {
        Polynomial polynomial1 = new Polynomial(new double[]{2, 1, 4});
        Polynomial polynomial2 = new Polynomial(new double[]{2, 1});

        Polynomial plusBoth = new Polynomial(new double[] {4, 2, 4});
        Polynomial minusBoth = new Polynomial(new double[] {0, 0, 4});

        Assertions.assertEquals(plusBoth, polynomial1.plus(polynomial2));
        Assertions.assertEquals(minusBoth, polynomial1.minus(polynomial2));

        MultiPolynomial multPol1 = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});
        MultiPolynomial multPol2 = new MultiPolynomial(new double[][]{{3, 2, 1, 2}, {5, 3}, {3, 4}});

        MultiPolynomial miltiPlusBoth = new MultiPolynomial(new double[][] {{6, 4, 2, 2}, {10, 9}, {3, 4}});
        MultiPolynomial multiMinusBoth = new MultiPolynomial(new double[][] {{0, 0, 0, 2}, {0, 3}, {3, 4}});

        Assertions.assertEquals(miltiPlusBoth, multPol1.plus(multPol2));
        Assertions.assertEquals(multiMinusBoth, multPol1.minus(multPol2));

    }

    /**
     * evaluatingTest.
     */
    @Test
    public void evaluatingTest() {

        Polynomial polynomial = new Polynomial(new double[]{2, 1});
        Assertions.assertEquals(4, polynomial.calculate(2));

        MultiPolynomial multPol = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});
        Assertions.assertEquals(23, multPol.evaluate(new double[] {1, 2}));

    }

    /**
     * equalsTest.
     */
    @Test
    public void equalsTest() {

        Polynomial polynomial1 = new Polynomial(new double[]{2, 1, 4});
        Polynomial polynomial2 = new Polynomial(new double[]{2, 1});

        Polynomial polynomial3 = new Polynomial(new double[]{2, 1, 4});
        Polynomial polynomial4 = new Polynomial(new double[]{2, 1, 4});

        Assertions.assertTrue(polynomial1.equals(polynomial2));
        Assertions.assertFalse(polynomial3.equals(polynomial4));

        MultiPolynomial multPol1 = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});
        MultiPolynomial multPol2 = new MultiPolynomial(new double[][]{{3, 2, 1, 2}, {5, 3}, {3, 4}});

        MultiPolynomial multPol3 = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});
        MultiPolynomial multPol4 = new MultiPolynomial(new double[][]{{3, 2, 1}, {5, 6}});

        Assertions.assertFalse(multPol1.equals(multPol2));
        Assertions.assertTrue(multPol3.equals(multPol4));

    }

}
