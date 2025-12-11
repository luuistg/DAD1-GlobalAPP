package edu.ucam.logic;

public class CommandParser {
	private String id;
	private String commandName;
    private String[] params;
    
    public CommandParser(String[] rawArgs) {

        this.id = rawArgs[0];
        this.commandName = rawArgs[1].toUpperCase();
        
        // Copiamos el resto de argumentos si existen
        if (rawArgs.length > 2) {
            this.params = new String[rawArgs.length - 2];
            System.arraycopy(rawArgs, 2, this.params, 0, rawArgs.length - 2);
        } else {
            this.params = new String[0];
        }
    }
    
    public String getId() { return id; }
    
    public String getName() { return commandName; }
    
    public String getParam(int index) {
        if (index >= 0 && index < params.length) {
            return params[index];
        }
        return null;
    }
    


}
