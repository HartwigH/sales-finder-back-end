package com.sf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Product.findByNameAndDataId",
                query = "from Product where name = :name and dataId = :dataId"
        )
})
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_url")
    private String productUrl;

    @Column(name = "product_img_url")
    private String productImgUrl;

    @Column(name = "product_data_id")
    private String dataId;

    @Column(name = "product_visibility")
    private int productVisibility;

    @Column(name = "store_id")
    private Long storeId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "store_id")
//    private Store store;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Price> price = new ArrayList<Price>();

}
