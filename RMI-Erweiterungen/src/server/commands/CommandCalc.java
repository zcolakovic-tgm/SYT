package server.commands;

import java.io.Serializable;
import java.rmi.RemoteException;

import calculation.Calculation;
import callback.Callback;

/**
 * Klasse (Command) der eine Berechnung aufruft und den entsprechenden Callback liefert
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class CommandCalc implements Command, Serializable {

    private static final long serialVersionUID = 3202369269194172790L;
    private Calculation calculation;
    private Callback callback;

    /*
     * Konstruktor
     * 
     * @since 2016-04-26
     */
    public CommandCalc (Calculation calculation, Callback callback) {
        this.calculation = calculation;
        this.callback = callback;
    }

    /*
     * Ruft den Callback auf 
     * 
     * @since 2016-04-26
     */
    @Override
    public void execute() {
        System.out.println("CalculationCommand called!");
        calculation.calculate();
        try {
            callback.execute(calculation.getResult());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}