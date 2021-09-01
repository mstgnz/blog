package com.mstgnz.blog.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class User implements UserDetails, IEntity {
    @Id
    @SequenceGenerator(name="seq_user", allocationSize = 1)
    @GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Blog> blogs;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date updateDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

