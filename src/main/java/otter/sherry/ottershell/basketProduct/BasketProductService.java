package otter.sherry.ottershell.basketProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.basket.BasketEntity;
import otter.sherry.ottershell.user.UserEntity;
import otter.sherry.ottershell.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketProductService {

    private final BasketProductRepository basketProductRepository;
    private final UserRepository userRepository;

    public BasketProductEntity addProductToBasket(Integer userId, Integer productId, Integer count){
        return basketProductRepository.save()
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
