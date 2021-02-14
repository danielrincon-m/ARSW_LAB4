package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SubsamplingBlueprintFilterTest {

    @Test
    public void filter() {
        SubsamplingBlueprintFilter rbpf = new SubsamplingBlueprintFilter();
        String author1 = "Pau";
        String name1 = "Nada";

        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(0, 0),
                new Point(15, 43),
                new Point(15, 43),
                new Point(18, 87),
                new Point(23, 43),
                new Point(23, 43),
                new Point(23, 43),
                new Point(54, 32)));
        ArrayList<Point> finalPts = new ArrayList<>();
        finalPts.add(new Point(15, 43));
        finalPts.add(new Point(18, 87));
        finalPts.add(new Point(23, 43));
        finalPts.add(new Point(54, 32));
        Blueprint bpp = new Blueprint(author1, name1, pts);
        assertEquals(finalPts, rbpf.filter(bpp).getPoints());
    }
}