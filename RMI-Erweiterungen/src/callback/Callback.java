package callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public interface Callback<T> extends Remote {

	/*
	 * Antwortet dem Client mit einer Nachricht
	 * 
	 * @since 2016-04-26
	 */
    public void execute(T command) throws RemoteException;
}