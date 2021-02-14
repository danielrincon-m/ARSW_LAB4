package edu.eci.arsw.blueprints.filter;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

public interface BlueprintFilter {

    /**
     * @param blueprint The blueprint to filter
     * @return A filtered blueprint
     */
    Blueprint filter(Blueprint blueprint);

    /**
     * @param blueprints The set of blueprints to filter
     * @return A filtered set of blueprints
     */
    Set<Blueprint> filter(Set<Blueprint> blueprints);
}
