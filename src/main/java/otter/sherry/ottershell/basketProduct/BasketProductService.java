package otter.sherry.ottershell.basketProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.basket.BasketEntity;
import otter.sherry.ottershell.basket.BasketRepository;
import otter.sherry.ottershell.product.ProductEntity;
import otter.sherry.ottershell.product.ProductRepository;
import otter.sherry.ottershell.user.UserEntity;
import otter.sherry.ottershell.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketProductService {

    private final BasketProductRepository basketProductRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    public BasketProductEntity addProductToBasket(Integer basketId, Integer productId, Integer count){

        BasketEntity basketEntity = basketRepository.findById(basketId).get();
        ProductEntity productEntity = productRepository.findById(productId).get();
        Optional<BasketProductEntity> basketProductEntity = basketProductRepository.findByBasketEntityAndProductEntity(basketEntity, productEntity);
        if(basketProductEntity.isPresent()){
            Integer basketInitialCount = basketProductEntity.get().getCount();
            basketProductEntity.get().setCount(count + basketInitialCount);
                return basketProductEntity.get();
        }
        else {
            BasketProductEntity basketProductEntity_2 = new BasketProductEntity();
            basketProductEntity_2.setProductEntity(productEntity);
            basketProductEntity_2.setBasketEntity(basketEntity);
            basketProductEntity_2.setCount(count);

            return basketProductRepository.save(basketProductEntity_2);
        }
    }

    // 유저 ID로 장바구니 안의 상품 목록 조회
    public List<BasketProductEntity> getBasketProductsByUser(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));

        BasketEntity basket = user.getBasket(); // BasketEntity에 getBasket() 있으면
        return basketProductRepository.findByBasketEntity(basket);
    }

    // 특정 상품 삭제
    public void deleteBasketProduct(Integer basketProductId) {
        basketProductRepository.deleteById(basketProductId);
    }
}
