package org.example;

import org.example.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PolynomialTest {

    @Test
    public void representationTest() {

        Polynomial polynomial = new Polynomial(new double[]{2., 1.});
        Assertions.assertEquals("x + 2.0", polynomial.toString());
    }

    @Test
    public void minusPlusTest() {
        Polynomial polynomial1 = Polynomial.of(1.0, 2.0);
        Polynomial polynomial2 = Polynomial.of(1.0, -2.0);
        Assertions.assertEquals(Polynomial.of(2., 0), polynomial1.plus(polynomial2));
        Assertions.assertEquals(Polynomial.of(2.), polynomial1.plus(polynomial2));
        Assertions.assertEquals(Polynomial.of(0., 4), polynomial1.minus(polynomial2));
    }

    @Test
    public void differentiateTest() {
        Polynomial constant = Polynomial.of(5);
        Polynomial linear = Polynomial.of(5, 2);
        Polynomial square = Polynomial.of(5, 2, 2);
        Polynomial zero = Polynomial.of(0);

        Assertions.assertEquals(zero, constant.differentiate(1));
        Assertions.assertEquals(zero, linear.differentiate(2));
        Assertions.assertEquals(zero, square.differentiate(3));

        Assertions.assertEquals(new Polynomial(2), linear.differentiate(1));
        Assertions.assertEquals(2, linear.differentiate(1).copyCoefficients()[0]);
        Assertions.assertEquals(Polynomial.of(2, 4), square.differentiate(1));
        Assertions.assertEquals(Polynomial.of(4), square.differentiate(2));
    }

    @Test
    public void testTimes() {
        Polynomial p1 = Polynomial.of(1., 2.);
        Polynomial p2 = Polynomial.of(-1., 2.);
        Assertions.assertEquals(p1.times(p2), p2.times(p1));
        Assertions.assertEquals(Polynomial.of(-1, 0, 4), p1.times(p2));
    }

    @Test
    public void evaluating(){
        Polynomial p1 = Polynomial.of(5);
        Polynomial p2 = Polynomial.of(2, -1, 1);
        Assertions.assertEquals(5, p1.evaluate(0));
        Assertions.assertEquals(5, p1.evaluate(1));

        Assertions.assertEquals(2, p2.evaluate(1));
        Assertions.assertEquals(4, p2.evaluate(2));
        Assertions.assertEquals(2, p2.evaluate(0));
        Assertions.assertEquals(4, p2.evaluate(-1));
    }

}
