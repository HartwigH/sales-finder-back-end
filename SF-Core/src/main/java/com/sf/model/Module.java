package com.sf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Module  extends AbstractEntity{


    private String title;

    @Lob
    @Column(nullable=true)
    private byte[] programPdf;


    @ManyToOne
    @JoinTable(name = "course_module",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Product product;

}
