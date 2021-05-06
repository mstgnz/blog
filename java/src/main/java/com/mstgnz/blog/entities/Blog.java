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
public class Blog implements Serializable {
    @Id
    @SequenceGenerator(name="seq_blog", allocationSize = 1)
    @GeneratedValue(generator = "seq_blog", strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Column(length = 100)
    private String title;
    @Column(length = 1000)
    private String content;
    private Boolean status;
    @OneToMany
    @JoinColumn(name = "comments_id")
    private List<Comment> comments;
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;
}
