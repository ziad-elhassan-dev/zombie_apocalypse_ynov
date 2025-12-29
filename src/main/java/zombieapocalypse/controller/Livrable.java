package zombieapocalypse.controller;

import zombieapocalypse.controller.game.Game;
import zombieapocalypse.model.mapcreation.Map;
import zombieapocalypse.model.mapcreation.MapGenerator;

public class Livrable {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        int nbSurvivors = 4;

        if (args.length > 2) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
            nbSurvivors = Integer.parseInt(args[2]);
        }

        MapGenerator mapg = new MapGenerator(width, height);
        Map map = mapg.generateMap();
        Game game = new Game(map, nbSurvivors);

        game.gameLoop();
    }
}
