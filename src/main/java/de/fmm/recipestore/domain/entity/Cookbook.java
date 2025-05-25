package de.fmm.recipestore.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "cookbook_id")
    private List<Tag> tags;

}
