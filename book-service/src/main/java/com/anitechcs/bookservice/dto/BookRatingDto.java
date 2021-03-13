package com.anitechcs.bookservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRatingDto {
    private long id;
    private long bookId;
    private float rating;
}
