package edu.ucam.interfaces;

import edu.ucam.logic.CommandParser;
import edu.ucam.threads.ClientHandler;

public interface ICommand {
	
	String execute(CommandParser cp, ClientHandler cl);

}
