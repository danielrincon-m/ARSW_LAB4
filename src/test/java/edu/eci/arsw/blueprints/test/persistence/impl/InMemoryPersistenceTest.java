/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        ArrayList<Point> pts0 = new ArrayList<>(Arrays.asList(new Point(40, 40), new Point(15, 15)));
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        ibpp.saveBlueprint(bp0);

        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(10, 10)));
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.", ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(10, 10)));
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        ArrayList<Point> pts2 = new ArrayList<>(Arrays.asList(new Point(10, 10), new Point(20, 20)));
        Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

        try {
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        } catch (BlueprintPersistenceException ex) {
            //Pasó
        }
    }


    @Test
    public void getBlueprintsByAuthorTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        String author = "Pau";

        try {
            ibpp.getBlueprintsByAuthor(author);
            fail("An exception was expected after searching for an author without blueprints");
        } catch (BlueprintNotFoundException e) {
            //Pass
        }

        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(10, 10)));
        Blueprint bp = new Blueprint(author, "El significado de la nada", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        ArrayList<Point> pts2 = new ArrayList<>(Arrays.asList(new Point(10, 10), new Point(20, 20)));
        Blueprint bp2 = new Blueprint(author, "El ocaso", pts2);

        try {
            ibpp.saveBlueprint(bp2);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the second blueprint.");
        }

        try {
            HashSet<Blueprint> bps = (HashSet<Blueprint>) ibpp.getBlueprintsByAuthor(author);
            assertEquals(2, bps.size());
            for (Blueprint blueprint : bps) {
                assertEquals(author, blueprint.getAuthor());
            }
        } catch (BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed returning blueprints set.");
        }
    }
}
