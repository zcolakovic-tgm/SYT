package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remoteService.DoSomethingService;

/**
 * Server, erhält eine Anfrage für eine Verbindung 
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class Server {
	
	/*
	 * Registriert eine Verbindung und verarbeitet die Anfrage
	 * 
	 * @since 2016-04-26
	 */
    public static void main(String[] args) {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
        	// Verbindungsobjekt 
            Service uRemoteObject = new Service();
            
            // Stub das verbundene Obj
            DoSomethingService stub = (DoSomethingService) UnicastRemoteObject.exportObject(uRemoteObject, 0);
            Registry registry = LocateRegistry.createRegistry(1234);
            
            // Registriert den Service
            registry.rebind("Service", stub);
            System.out.println("Service bound! Press Enter to terminate ...");

            // Schaltet sich ab sobald Enter eingelesen wird 
            while ( System.in.read() != '\n' );
                UnicastRemoteObject.unexportObject(uRemoteObject, true);

            System.out.println("Service unbound, System goes down ...");

        } catch (RemoteException re) {
            System.err.println("Service already bound?" + " Check your RMI-Registry settings!");
            re.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Service exception:");
            e.printStackTrace();
            System.exit(1);
        }

    }

}