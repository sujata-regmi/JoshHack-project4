package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CombatRulesTest {

    private int maxHp;

    @BeforeEach
    void setUp() {
        // Arrange regular setup
        maxHp = 100;
    }

    @Test
    void testNormalHealing() {
        // Arrange
        int currentHp = 50;
        int amount = 20;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(70, actualHp);
        assertFalse(actualIsDead);
    }

    @Test
    void testCapBoundaryExact() {
        // Arrange
        int currentHp = 99;
        int amount = 1;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(100, actualHp);
        assertFalse(actualIsDead);
    }

    @Test
    void testHpCappingOverflow() {
        // Arrange
        int currentHp = 90;
        int amount = 20;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(100, actualHp);
        assertFalse(actualIsDead);
    }

    @Test
    void testNormalDamage() {
        // Arrange
        int currentHp = 50;
        int amount = -20;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(30, actualHp);
        assertFalse(actualIsDead);
    }

    @Test
    void testSurvivalBoundary() {
        // Arrange
        int currentHp = 21;
        int amount = -20;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(1, actualHp);
        assertFalse(actualIsDead);
    }

    @Test
    void testDeathThreshold() {
        // Arrange
        int currentHp = 10;
        int amount = -15;

        // Act
        int actualHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        boolean actualIsDead = CombatRules.isDead(actualHp);

        // Assert
        assertEquals(-5, actualHp);
        assertTrue(actualIsDead);
    }
}