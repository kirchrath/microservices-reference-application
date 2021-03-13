package com.anitechcs.bookservice.service;

import com.anitechcs.bookservice.dto.BookRatingDto;
import com.anitechcs.bookservice.model.Book;
import com.anitechcs.bookservice.model.BookPrice;
import com.anitechcs.bookservice.model.BookRatings;
import com.anitechcs.bookservice.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
public class BookServiceImplIntegrationTest {
    @Autowired
    private BookServiceImpl testee;
    @Autowired
    private BookRepository repository;

    @Test
    public void thatBookServiceUpdatesRating() {
        // given
        float expectedRating = 4.5F;
        int expectedRatingCount = 2;

        long bookId = 1L;
        BookRatings ratings = BookRatings.builder().rating(4F).ratingCount(1).build();
        Book book = Book.builder()
                .bookId(bookId)
                .authors(List.of("Author 1"))
                .title("THE Book")
                .description("A funny book")
                .isbn("ISBN-String")
                .language("english")
                .price(BookPrice.builder().amount(19.99F).currency("EUR").mrp(19.99F).build())
                .publicationDate(LocalDate.now())
                .ratings(ratings)
                .build();
        repository.save(book);


        BookRatingDto rating = BookRatingDto.builder()
                .rating(5F)
                .bookId(bookId)
                .build();

        // when
        testee.updateRating(rating);

        // then
        Optional<Book> savedBook = repository.findById(bookId);
        assertThat(savedBook).isNotEmpty();
        assertThat(savedBook.get().getRatings().getRating()).isEqualTo(expectedRating);
        assertThat(savedBook.get().getRatings().getRatingCount()).isEqualTo(expectedRatingCount);
    }
}
