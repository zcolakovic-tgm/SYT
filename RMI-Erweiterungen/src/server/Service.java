package server;

import java.rmi.RemoteException;
import server.commands.*;

import remoteService.DoSomethingService;

/**
 * Excute Befehl wird ausgeführt
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public class Service implements DoSomethingService {

    @Override
    public void doSomething(Command c) throws RemoteException {
        c.execute();

    }

}