package de.wieczorek.dataloader.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="THIRD")
public class Third {

    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="THIRD_FIELD")
    private String thirdField;

}
