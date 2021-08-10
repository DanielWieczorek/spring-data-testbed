package de.wieczorek.dataloader.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="FIRST")
public class First {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="FIRST_FIELD")
    private String firstField;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_first")
    private List<Second> seconds;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_first")
    private List<Third> thirds;
}
