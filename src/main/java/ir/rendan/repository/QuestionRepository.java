package ir.rendan.repository;

import ir.rendan.model.Question;
import ir.rendan.repository.custom.PagingAndSortingRepositoryWithReadOnlyRestResource;
import org.springframework.security.access.prepost.PreAuthorize;


/**
 * Created by Amir Shams on 5/26/2017.
 */

public interface QuestionRepository extends PagingAndSortingRepositoryWithReadOnlyRestResource<Question,Long> {

    @PreAuthorize("hasAuthority('ADMIN')")
    void delete(Long id);
}
