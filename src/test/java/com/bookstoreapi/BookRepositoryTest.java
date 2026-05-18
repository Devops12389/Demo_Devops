package com.bookstoreapi;

import com.bookstoreapi.entity.Book;
import com.bookstoreapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository repo;

    @Test
    void saveAndFind() {
        Book b = repo.save(new Book(null, "Title"));
        var found = repo.findById(b.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Title");
    }
    //Why: validate JPA mapping and repository queries using an in-memory DB.
    //Tip: use Testcontainers when you need real DB behavior (Postgres) in CI.
}
