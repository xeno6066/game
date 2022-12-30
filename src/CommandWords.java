import java.util.HashMap;
public class CommandWords
{
    private HashMap<String, CommandWord> validCommands = new HashMap<>();


    public CommandWords()
    {
        validCommands.put("ga", CommandWord.Ga);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("exit", CommandWord.QUIT);
        validCommands.put("help", CommandWord.HELP);
    }


    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public String getAllCommands() {
        String show = "";
        for (String command : validCommands.keySet()) {
            show += command + " ";
        }
        return show;
    }

    public CommandWord getCommand(String aString) {
        if (validCommands.containsKey(aString)) return validCommands.get(aString);
        return CommandWord.UNKNOWN;
    }
}
