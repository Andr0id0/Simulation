public class MapConsoleRenderer {

    private static final String SYMBOL_TREE = "\uD83C\uDF3E";
    private static final String SYMBOL_HERBIVORE = "🦌";
    private static final String SYMBOL_PREDATOR = "🐅";
    private static final String SYMBOL_ROCK = "⛰️";
    private static final String SYMBOL_GRASS = "\uD83C\uDF31";
    private static final String ANSI_BACKGROUND = "\u001B[48;5;34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String SYMBOL_EMPTY = "🟩";


    private final Map map;

    public MapConsoleRenderer(Map map) {
        this.map = map;
    }

    public void render() {
        int height = map.getHeight();
        int length = map.getLength();

        for (int x = 0; x < height; x++) {
            StringBuilder line = new StringBuilder();
            line.append(ANSI_BACKGROUND);
            for (int y = 0; y < length; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (map.isSquareEmpty(coordinates)) {
                    line.append(SYMBOL_EMPTY).append(" ");
                } else {
                    line.append(renderEntity(coordinates)).append(" ");
                }
            }
            line.append(ANSI_RESET);
            System.out.println(line);
        }
    }

    private String renderEntity(Coordinates coordinates) {
        Entity entity = map.getEntity(coordinates);
        switch (entity.getClass().getSimpleName()) {
            case "Tree":
                return SYMBOL_TREE;
            case "Rock":
                return SYMBOL_ROCK;
            case "Grass":
                return SYMBOL_GRASS;
            case "Predator":
                return SYMBOL_PREDATOR;
            case "Herbivore":
                return SYMBOL_HERBIVORE;
            default:
                return SYMBOL_EMPTY;
        }
    }
}
