package de.wieczorek.dataloader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SECOND")
public class Second {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SECOND_FIELD")
    private String secondField;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_second")
    private List<Third> thirds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_second")
    @JsonIgnore
    private Second parent;
}
