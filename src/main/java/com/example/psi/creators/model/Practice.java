package com.example.psi.creators.model;


import com.example.psi.commons.model.BaseClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Practice extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private EPractice practice;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    public Practice(EPractice practice, Video video) {
        this.practice = practice;
        this.video = video;
    }

}
