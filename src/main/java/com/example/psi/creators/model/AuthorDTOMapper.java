package com.example.psi.creators.model;

import java.util.function.Function;

public class AuthorDTOMapper implements Function<Author, AuthorDTO> {

    private String jwtToken;

    public AuthorDTOMapper(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public AuthorDTO apply(Author author) {
        return new AuthorDTO(author.getUsername(), author.getRole().toString(), jwtToken);
    }
}
