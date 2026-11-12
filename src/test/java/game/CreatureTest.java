package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    private World world;
    private Creature creature;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        world = new World(tiles);

        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);

        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
    }

   @Test
    void testModifyHpLethalDeath() {
        // Act
        creature.modifyHp(-110, "slain by a monster");

        // Assert
        assertEquals(-10, creature.hp());
    }

    @Test
    void testModifyHpSurvival() {

        creature.modifyHp(-5, "took minor damage");
        assertEquals(95, creature.hp());
    }

    @Test
    void testModifyHpHealingCap() {
        creature.modifyHp(200, "healed");
        assertEquals(100, creature.hp());
    }
}