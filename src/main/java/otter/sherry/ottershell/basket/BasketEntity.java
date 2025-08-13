package otter.sherry.ottershell.basket;


import jakarta.persistence.*;
import lombok.*;
import otter.sherry.ottershell.basketProduct.BasketProductEntity;
import otter.sherry.ottershell.user.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basketId;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @OneToMany(mappedBy = "basketEntity")
    List<BasketProductEntity> basketProducts;

    public BasketEntity(UserEntity user) {
        this.user = user;
    }
    

}
