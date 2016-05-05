package client;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;

/**
 * Hier handelt sich es um eine Wrapper Klasse für ein CORBA Obj
 *
 * @author Zeljko Colakovic
 * @version 04-05-2016
 */
public class Client {
    public Client calculate = null;

    /**
     * Konstruktor
     *
     * @param args - Kommondozeilen Parameter
     * @since 04-05-2016
     */
    public Client(String[] args) {
        connectToRemote(args);
    }

    /**
     * Wrapper Methode welche die locale referenz der remote Methode aufruft
     *
     * @param a - Integer Eingabe
     * @param b - Integer Eingabe
     * @return - Integer Ergebnis
     * @since 04-05-2016
     */
    public int add(int a, int b) {
        return calculate.add(a, b);
    }

    /**
     * Wrapper Methode welche die locale referenz der remote Methode aufruft
     *
     * @param a - Integer Eingabe
     * @param b - Integer Eingabe
     * @return - Integer Ergebnis
     * @since 04-05-2016
     */
    public int sub(int a, int b) {
        return calculate.sub(a, b);
    }

    /**
     * Wrapper Methode welche die locale referenz der remote Methode aufruft
     *
     * @param a - Integer Eingabe
     * @param b - Integer Eingabe
     * @return - Integer Ergebnis
     * @since 04-05-2016
     */
    public int mult(int a, int b) {
        return calculate.mult(a, b);
    }

    /**
     * Wrapper Methode welche die locale referenz der remote Methode aufruft
     *
     * @param a - Integer Eingabe
     * @param b - Integer Eingabe
     * @return - Integer Ergebnis
     * @since 04-05-2016
     */
    public int div(int a, int b) {
        return calculate.div(a, b);
    }


    /**
     * Method welches die Verbingun erstellt
     *
     * @param args - CLI Argumente
     * @since 04-05-2016
     */
    public void connectToRemote(String[] args) {
        try {

			// Erstellen und intialisieren des ORB
            ORB orb = ORB.init(args, null);

			// Erhalten des RootContext des angegebenen Namingservices
            Object o = orb.resolve_initial_references("NameService");

			// Verwenden von NamingContextExt
            NamingContextExt rootContext = NamingContextExtHelper.narrow(o);

			// Angeben des Pfades zum Echo Objekt
            NameComponent[] name = new NameComponent[2];
            name[0] = new NameComponent("test","my_context");
            name[1] = new NameComponent("Berechne", "Object");

			// Aufloesen der Objektreferenzen
            calculate = CalculateHelper.narrow(rootContext.resolve(name));

        }	catch (Exception e)	{
            System.err.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
            e.printStackTrace();
        }

    }

}