package calculation;

import java.math.BigDecimal;

/**
 * Interface 
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public interface Calculation {

    /*
     * Startet den Prozess zum Berechnen  
     * 
     * @since 2016-04-26
     */
    void calculate();

    /*
     * Gibt das verarbeiterte Ergebnis zurück
     * 
     * @since 2016-04-26
     */
    BigDecimal getResult();

}