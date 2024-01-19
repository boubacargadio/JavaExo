public class Player {
    private String representation;
    private String name;

    public Player(){
        this( "| X ");
    }
    public Player(String representation){
        this(representation, "player 1");
    }

    public Player(String representation, String name){
        this.representation = representation;
        this.name = name;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public String getName() {
        return this.name;
    }
}
