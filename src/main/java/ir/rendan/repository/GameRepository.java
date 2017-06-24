package ir.rendan.repository;

import ir.rendan.model.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Amir Shams on 6/23/2017.
 */
public interface GameRepository extends CrudRepository<Game,Integer> {

}
