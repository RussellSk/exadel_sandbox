package com.exadel.team2.sandbox.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "IMAGE")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "IMG_ID", nullable = false)
    private Long id;

    @Column(name = "IMG_NAME")
    private String imageName;

    @Column(name = "IMG_EXT")
    private String ext;

    @Column(name = "IMG_SIZE")
    private Long size;

    @Column(name = "IMG_ALT_TEXT")
    private String altText;

    @CreationTimestamp
    @Column(name = "IMG_CREATED_AT")
    private LocalDateTime createdAt;

}
