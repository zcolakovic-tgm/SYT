package callback;

import java.math.BigDecimal;

/**
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class CallbackCalc implements Callback<BigDecimal>{
	
	/*
	 * Einfache Methode für eine Ausgabe des erechneten Ergebnisses
	 * 
     * @since 2016-04-26
	 */
    @Override
    public void execute(BigDecimal output) {
        System.out.println(output);
    }
}