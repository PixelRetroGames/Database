package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.ActorData;
import ciolty.engine.database.Filter;

import java.util.List;

public interface ActorAction extends VideoDBActionInterface {
        /**
         * @param filter
         * @return List of actors with filter
         */
        default List<ActorData> getAllActorsWithFilter(Filter filter) {
            List<ActorData> actors = getUnitOfWork().getActorRepository().find(filter);
            return actors;
        }
}
