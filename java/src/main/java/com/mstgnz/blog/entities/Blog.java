package com.mstgnz.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blogs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Blog implements Serializable, IEntity {
    @Id
    @SequenceGenerator(name="seq_blog", allocationSize = 1)
    @GeneratedValue(generator = "seq_blog", strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;
    @Column(length = 100)
    private String title;
    @Column(length = 150)
    private String slug;
    @Column(length = 255)
    private String shortText;
    @Column(length = 10000)
    private String longText;
    private Boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date updateDate;
}
