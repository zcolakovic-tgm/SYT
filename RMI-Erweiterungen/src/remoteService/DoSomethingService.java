package remoteService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.commands.Command;

/**
 * Interface
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public interface DoSomethingService extends Remote {

	/*
	 * Führt den erhaltenen Command aus
	 * 
	 * @since 2016-04-26
	 */
    public void doSomething(Command c) throws RemoteException;

}