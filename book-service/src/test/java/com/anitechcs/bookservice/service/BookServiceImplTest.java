package com.anitechcs.bookservice.service;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookServiceImplTest {
    @Test
    void thatCalculateRatingWorksWithCompleteZeroInput() {
        // given
        int ratingCount = 0;
        float currentRating = 0;
        float rating = 0;
        float expected = 0;

        // when
        float actual = BookServiceImpl.calculateRating(ratingCount, currentRating, rating);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void thatCalculateRatingWorksWithPreviousZeroInput() {
        // given
        int ratingCount = 0;
        float currentRating = 0;
        float rating = 4;
        float expected = 4;

        // when
        float actual = BookServiceImpl.calculateRating(ratingCount, currentRating, rating);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void thatCalculateRatingWorksWithNormalInput() {
        // given
        int ratingCount = 6;
        float currentRating = 3.5F;
        float rating = 4;
        float expected = 3.57F;

        // when
        float actual = BookServiceImpl.calculateRating(ratingCount, currentRating, rating);

        // then
        assertThat(actual).isCloseTo(expected, Offset.offset(0.01F));
    }
}
