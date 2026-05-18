package com.bookstoreapi;

import com.bookstoreapi.entity.Book;
import com.bookstoreapi.repository.BookRepository;
import com.bookstoreapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//enables Mockito annotations.
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository repo;

    @InjectMocks
    BookService service;

    void getBook_existingId_returnsBook(){

        // Arrange: prepare inputs and mocks.
        Book b = new Book(new Book(), "Title");
        when(repo.findById(1L)).thenReturn(Optional.of(b));

        //Act: call the method under test.
        Book result = service.getBookById(1L);

        //Assert: verify outputs and/or interactions.
        assertThat(result.getTitle()).isEqualTo("Title");
        verify(repo).findById(1L);
    }

   /* @Test
    void getBook_missingId_throws() {
        when(repo.findById(2L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getBookById(2L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("book not found");
    }*/

   /* @Test
    void createBook_trimsTitleBeforeSave() {
        Book input = new Book(null, "  hello  ");
        when(repo.save(any())).thenAnswer(inv -> {
            Book arg = inv.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        service.createBook(input);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(repo).save(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo("hello");
    }*/
    //Why: test business logic in isolation.

    /*Key points:

@ExtendWith(MockitoExtension.class) enables Mockito annotations.

@Mock creates mocks; @InjectMocks injects them.

ArgumentCaptor verifies objects passed to collaborators.*/
}
