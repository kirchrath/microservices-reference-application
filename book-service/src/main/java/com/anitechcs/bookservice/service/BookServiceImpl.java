package com.anitechcs.bookservice.service;

import com.anitechcs.bookservice.dto.BookRatingDto;
import com.anitechcs.bookservice.model.Book;
import com.anitechcs.bookservice.model.BookRatings;
import com.anitechcs.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This clas is the service implementation. 
 * Contains business logic for Book Service
 * 
 * @author Tapas Jena
 */
@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * 
     */
    @Override
    public List<Book> getAllBooks(int limit, int offset) {
        //TODO: Implement
        return null;
    }

    /**
     * 
     */
    @Override
    public Book getBookById(Long bookId) {
        Optional<Book> book = repository.findById(bookId);
        //TODO: Implement
        return null;
    }

    /**
     * 
     */
    @Override
    public List<String> getAllBookGenres() {
        //TODO: Implement
        return null;
    }

    @Transactional
    public void updateRating(BookRatingDto rating) {
        Optional<Book> optionalBook = repository.findById(rating.getBookId());
        optionalBook.ifPresent(book -> {
            BookRatings ratings = book.getRatings();
            float calculatedRating = calculateRating(
                    ratings.getRatingCount() == null ? 0 : ratings.getRatingCount(),
                    ratings.getRating() == null ? 0 : ratings.getRating(),
                    rating.getRating()
            );
            ratings.setRating(calculatedRating);
            ratings.setRatingCount(ratings.getRatingCount() + 1);
        });
    }

    static float calculateRating(int ratingCount, float currentRating, float rating) {
        return ((ratingCount * currentRating) + rating) / (ratingCount + 1);
    }
}
