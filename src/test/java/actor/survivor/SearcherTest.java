package actor.survivor;

import static org.junit.Assert.*;

import org.junit.Test;

import zombieapocalypse.model.actor.survivor.Searcher;

public class SearcherTest {

    @Test
    public void initSearcher() {
        Searcher searcher = new Searcher("", null);
        assertEquals(5, searcher.getHealthPoints());
        assertEquals(1, searcher.getLevel());
    }
}