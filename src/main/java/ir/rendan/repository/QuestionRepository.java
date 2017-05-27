package ir.rendan.repository;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Amir Shams on 5/26/2017.
 */
public interface QuestionRepository extends PagingAndSortingRepository<Question,Long> {

}
