package ir.rendan.repository;

import ir.rendan.model.DataFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Amir Shams on 8/2/2017.
 */
public interface DataFileRepository extends CrudRepository<DataFile,Integer> {

    @Query("select d from DataFile d where d.key = ?1")
    DataFile getByKey(String key);
}
