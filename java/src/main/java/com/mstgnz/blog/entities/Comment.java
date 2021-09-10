package com.mstgnz.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Comment implements Serializable, IEntity {
    @Id
    @SequenceGenerator(name="seq_comment", allocationSize = 1)
    @GeneratedValue(generator = "seq_comment", strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long blogId;
    private Long userId;
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date createDate;
}
