package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remoteService.DoSomethingService;
import calculation.*;
import callback.*;
import server.commands.Command;
import server.commands.CommandCalc;

/**
 * Klasse die sich mit dem Server verbindet
 * Erhält einen Callback
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class Client {

	/*
	 * Methode die eine Verbindung mit dem Server sicherstellt
	 * 
	 * @since 2016-04-24
	 */
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
        	// Register Obj
            Registry registry = LocateRegistry.getRegistry(1234);
            
            // Erstellt Service Obj
            DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
            System.out.println("Service found");

            // Usereingabe
            int numb = Integer.parseInt(args[1]);

            // Generiert die Berechnung
            Calculation calculation = new CalcEuler(numb);
            
            //Generiert den Callback
            Callback callback = new CallbackCalc();
            Callback cuc = (Callback) UnicastRemoteObject.exportObject(callback, 0);
            
            // Command vorbereiten
            Command command = new CommandCalc(calculation, cuc);

            uRemoteObject.doSomething(command);

            System.exit(1);
        } catch (RemoteException re) {
            System.err.println("Service not found?" + " Check your RMI-Registry!");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Service exception:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}