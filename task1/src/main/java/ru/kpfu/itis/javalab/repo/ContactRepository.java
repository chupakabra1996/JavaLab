package ru.kpfu.itis.javalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.javalab.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
