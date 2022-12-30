public class Game
{
    private Parser parser;
    private Player player;

    public Game() 
    {
        player = new Player("Jos");
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room kamer, gang, living, kelder, tuin, tuinhuis,zolder,park,geheimeKamer , fabriek;
        Item hamer , mes , sleutel , schep ;

      
        // create the rooms
        kamer = new Room(" je bent in je kamer");
        gang = new Room("je bent in de gang van je huis");
        living = new Room("de lining ziet er mooi uit");
        kelder = new Room("de kelder is stofig zo als altijd");
        tuin = new Room("de tuin");
        tuinhuis = new Room("het tuinhuis is vies en damp");
        zolder = new Room("de zolder is romolig");
        park = new Room("dde mooie natuur");
        geheimeKamer = new Room("iets geheim??");
        fabriek = new Room("een oude fabriek die in jaren niet gebruikt is ");

        // initialise room exits
        kamer.setExit("buiten", gang);
        gang.setExit("south" ,kamer);
        gang.setExit("beneden" , living);
        living.setExit("boven", gang);
        living.setExit("terug",tuin);
        living.setExit("buiten",park);
        kelder.setExit("terug" , living);
        tuin.setExit("terug" , living );
        tuin.setExit("verder", tuinhuis);
        geheimeKamer.setExit("v1" , kelder);

        //items
        tuinhuis.addItem(new Item("water", "a crate bottles of water", 12.2));
        kamer.addItem(new Item("sleutel", "een sleutel dat naar een kamer leid", 0.5));
        living.addItem(new Item("mes","oei das scherp ", 5));
        tuinhuis.addItem(new Item("schep","een roestige schep" , 9));
        geheimeKamer.addItem(new Item("hamer", "een oude hamer" , 9.6));

        player.setCurrentRoom(kamer);  // start game outside
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome tot mijn spel ");
        System.out.println("ik hoop dat je het leuk vind \n gemaakt door xeno weij ");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private void printLocationInfo() {
        System.out.println(player.getName() + " is " + player.getCurrentRoom().getLongDescription());
        System.out.println(player.getBagDescription());
        System.out.println();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case Ga:
                goRoom(command);
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                break;
            case LOOK:
                look();
                break;
            case EAT:
                eat();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            default:
        }

        return wantToQuit;
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        int result = player.take(command.getSecondWord());
        if (result==Player.ITEM_GONE) {
            printLocationInfo();
        } else {
            if (result==Player.ITEM_NOTMOVEABLE) {
                System.out.println("Item " + command.getSecondWord() + " is not moveable");
            } else {
                System.out.println("Can't find " + command.getSecondWord());
            }

        }
    }
    // implementations of user commands:
    private void printHelp() 
    {
        System.out.println(player.getName() + " is lost. " + player.getName() + " is alone. He wanders");
        System.out.println("around in" + player.getCurrentRoom());
        System.out.println();
        System.out.println("your command words are:");
        System.out.println("   " + parser.showCommands());
    }

    private void look() {
        System.out.println(player.getName() + " is " + player.getCurrentRoom().getLongDescription());
    }

    private void eat() {
        System.out.println(player.getName() + " has eaten and is not hungry anymore");
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}