package calculation;

import java.io.Serializable;
import java.math.*;

/**
 * Klasse zur berechnung der Euler-Konstante
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class CalcEuler implements Calculation, Serializable {

    private int digits;
    private BigDecimal euler;
    
    /*
     * Konstruktor
     * 
     * @since 2016-04-26
     */
    public CalcEuler(int digits) {
        this.digits = digits;
    }

    /*
     * Methode zur Berechnung vom Euler
     * 
     * @since 2016-04-26
     */
    @Override
    public void calculate() {
        BigDecimal eu = BigDecimal.ONE;
        BigDecimal fact = BigDecimal.ONE;

        for(int i=1;i<digits;i++) {
            fact = fact.multiply(new BigDecimal(i));

            eu = eu.add(BigDecimal.ONE.divide(fact, new MathContext(digits, RoundingMode.HALF_UP)));
        }
        this.euler = eu;
    }

    /*
     * Überschreibt die Methode getResult() und gibt nun den Euler aus
     * 
     * @since 2016-04-26
     */
    @Override
    public BigDecimal getResult() {
        return euler;
    }
}