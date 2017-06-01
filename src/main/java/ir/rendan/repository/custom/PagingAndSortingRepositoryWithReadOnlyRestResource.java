package ir.rendan.repository.custom;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;

/**
 * Created by Amir Shams on 6/1/2017.
 */
@RepositoryRestResource
public interface PagingAndSortingRepositoryWithReadOnlyRestResource<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    @RestResource(exported = false)
    <S extends T> S save(S var1);

    @RestResource(exported = false)
    <S extends T> Iterable<S> save(Iterable<S> var1);

    @RestResource(exported = false)
    void delete(ID var1);

    @RestResource(exported = false)
    void delete(T var1);

    @RestResource(exported = false)
    void delete(Iterable<? extends T> var1);

    @RestResource(exported = false)
    void deleteAll();
}
