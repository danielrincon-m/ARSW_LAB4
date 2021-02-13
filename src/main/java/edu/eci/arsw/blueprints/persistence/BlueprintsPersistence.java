/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

/**
 * @author hcadavid
 */
public interface BlueprintsPersistence {

    /**
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *                                       or any other low-level persistence error occurs.
     */
    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * @param author     blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    /**
     * @param author Blueprint's author
     * @return Set of all the blueprits from the author
     * @throws BlueprintNotFoundException if there are no blueprints from the author
     */
    Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    /**
     * @return Set of all the blueprints
     * @throws BlueprintNotFoundException If there are no blueprints
     */
    Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException;
}
