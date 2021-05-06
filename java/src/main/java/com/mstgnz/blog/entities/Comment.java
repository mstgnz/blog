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
public class Comment implements Serializable {
    @Id
    @SequenceGenerator(name="seq_comment", allocationSize = 1)
    @GeneratedValue(generator = "seq_comment", strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;
    private String Content;
    private Date createDate;
}
