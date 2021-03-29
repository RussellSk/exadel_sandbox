package com.exadel.team2.sandbox.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "IMG_ID")
    private Long imgId;


    @Column(name = "IMG_PATH")
    private String imgPath;

    @Column(name = "IMG_NAME")
    private String imageName;

    @Column(name = "IMG_EXT")
    private String imgExt;

    @Column(name = "IMG_SIZE")
    private Integer imgSize;


    @Column(name = "IMG_CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date imgCreatedAt;          //datetime

}
