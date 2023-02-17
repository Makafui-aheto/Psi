package com.example.psi.creators.model;

import java.util.List;

public record VideoDTO(String videoTitle, String thumbNailPath,
                       String author, String videoDescription,
                       List<String> videoURL, String subjectName, String practiceArea) {
}
