package com.example.psi.creators.model;


import com.example.psi.commons.model.BaseClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
public class Video extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String thumbNailPath;

    private String videoURL;

    private String videoTitle;

    private String videoDescription;

    private String subjectName;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "video", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    private Set<Practice> practice;

    public Video(String thumbNailPath, String videoURL,
                 String videoTitle, String videoDescription,
                 String subjectName, Author author, Set<Practice> practice) {

        this.thumbNailPath = thumbNailPath;
        this.videoURL = videoURL;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.subjectName = subjectName;
        this.author = author;
        this.practice = practice;
    }

    public String getThumbNailPath() {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath) {
        this.thumbNailPath = thumbNailPath;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Practice> getPractice() {
        return practice;
    }

    public void setPractice(Set<Practice> practice) {
        this.practice = practice;
    }
}
