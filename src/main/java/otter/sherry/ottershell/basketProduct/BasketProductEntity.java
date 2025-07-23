package otter.sherry.ottershell.basketProduct;


import jakarta.persistence.*;
import lombok.*;
import otter.sherry.ottershell.basket.BasketEntity;
import otter.sherry.ottershell.product.ProductEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasketProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basketProductId;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "basketId")
    private BasketEntity basketEntity;

    private Integer count;

}
