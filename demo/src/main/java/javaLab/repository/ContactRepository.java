package javaLab.repository;

import javaLab.domain.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    Optional<Contact> findByName(@Param("name") String name);
}