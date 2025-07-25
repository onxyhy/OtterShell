package otter.sherry.ottershell.basketProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import otter.sherry.ottershell.basket.BasketEntity;
import otter.sherry.ottershell.product.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProductEntity, Integer> {
    Optional<BasketProductEntity> findByBasketEntityAndProductEntity(BasketEntity basket, ProductEntity product);

    List<BasketProductEntity> findByBasketEntity(BasketEntity basketEntity);
    List<BasketProductEntity> findByBasketEntity_User_UserId(Integer userId);
    BasketProductEntity findByBasketProductId(Integer basketProductId);
}
