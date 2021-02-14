package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpServices = ac.getBean(BlueprintsServices.class);
        String author1 = "Pau";
        String author2 = "Dani";
        String name1 = "Nada";
        String name2 = "Feli";

        ArrayList<Point> pts = new ArrayList<>(Arrays.asList(new Point(0, 0), new Point(0, 10), new Point(10, 0)));
        Blueprint bpp = new Blueprint(author1, name1, pts);
        Blueprint bpd = new Blueprint(author2, name2, pts);

        try {
            bpServices.addNewBlueprint(bpp);
            bpServices.addNewBlueprint(bpd);
            System.out.println(!(bpServices.getBlueprint(author1, name1) == null));
            System.out.println(bpServices.getBlueprintsByAuthor(author2).size() == 1);
            System.out.println(bpServices.getAllBlueprints().size() == 2);
            bpServices.getBlueprint(author1, name1).getPoints().forEach((p) -> System.out.println(p.getX() + " - " + p.getY()));
//            bpServices.getAllBlueprints().forEach((bp) -> bp.getPoints().forEach((p) -> System.out.println(p.getX() + " - " + p.getY())));
        } catch (BlueprintNotFoundException | BlueprintPersistenceException e) {
            e.printStackTrace();
        }

//        System.out.println("-------------------");
//        System.out.println(new Point(0, 0).equals(new Point(10, 10)));
//        System.out.println(new Point(0, 0).equals(new Point(0, 0)));
    }
}
