package ir.rendan.repository;

import ir.rendan.model.Game;
import ir.rendan.repository.custom.PagingAndSortingRepositoryWithReadOnlyRestResource;

/**
 * Created by Amir Shams on 6/23/2017.
 */
public interface GameRepository extends PagingAndSortingRepositoryWithReadOnlyRestResource<Game,Integer> {

}
