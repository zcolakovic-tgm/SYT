package server.commands;

import java.io.Serializable;

/**
 * Interface
 * 
 * @author Zeljko Colakovic
 * @version 2016-04-26
 */
public interface Command extends Serializable {

    public void execute();

}