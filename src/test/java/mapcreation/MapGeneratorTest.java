package mapcreation;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

import zombieapocalypse.model.cell.*;
import zombieapocalypse.model.mapcreation.*;
import zombieapocalypse.model.structure.*;;
public class MapGeneratorTest {

    private MapGenerator mapGenerator;

    @Before
    public void setUp() {
        mapGenerator = new MapGenerator(10, 10); // Crée un nouveau MapGenerator pour chaque test
    }

    @Test
    public void testAddStreetX() {

        mapGenerator.addStreetX(new Street("Test Street", false), 5);
        assertTrue(mapGenerator.getMap().getCell(0, 5) instanceof StreetCell);
    }

    @Test
    public void testAddStreetY() {

        mapGenerator.addStreetY(new Street("Test Street", false), 5);
        assertTrue(mapGenerator.getMap().getCell(5, 0) instanceof StreetCell);
    }

    @Test
    public void testCanBeSplitX() {

        List<Integer> possibleColumns = mapGenerator.canBeSplitX();
        assertNotNull(possibleColumns);
    }

    @Test
    public void testCanBeSplitY() {

        List<Integer> possibleRows = mapGenerator.canBeSplitY();
        assertNotNull(possibleRows);
    }

    @Test
    public void testMapGenerator() {
        Map map = mapGenerator.generateMap();
        assertNotNull(map);
    }

}
