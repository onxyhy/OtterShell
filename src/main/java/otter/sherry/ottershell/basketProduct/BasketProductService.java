package otter.sherry.ottershell.basketProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.basket.BasketEntity;
import otter.sherry.ottershell.basket.BasketService;
import otter.sherry.ottershell.product.ProductEntity;
import otter.sherry.ottershell.product.ProductService;
import otter.sherry.ottershell.user.UserEntity;
import otter.sherry.ottershell.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketProductService {

    @Autowired
    private final BasketProductRepository basketProductRepository;
    private final UserService userService;
    private final ProductService productService;
    private final BasketService basketService;

    //장바구니에 물건 담기
    public BasketProductEntity addProductintoBasket(int userId, int productId, int count) {
       UserEntity userEntity = userService.getUserEntity(userId);
       BasketEntity basketEntity = basketService.getBasketEntity(userId);
       ProductEntity productEntity = productService.getProductEntity(productId);

       // 존재 여부 파악
       Optional<BasketProductEntity> basketProductEntity = basketProductRepository.findByBasketEntityAndProductEntity(basketEntity, productEntity);
       BasketProductEntity savedBasketProductEntity;
       // 있으면 수량을 합쳐
        if (basketProductEntity.isPresent()) {
            int originCount = basketProductEntity.get().getCount();
            basketProductEntity.get().setCount(originCount + count);
            savedBasketProductEntity = basketProductRepository.save(basketProductEntity.get());
        }
        // 없으면 냅둬
        else{
            savedBasketProductEntity = basketProductRepository.save(new BasketProductEntity(basketEntity, productEntity, count));
        }
        return savedBasketProductEntity;

    }
    public BasketProductEntity plusOnlyOneProduct(int userId, int productId){
        UserEntity userEntity = userService.getUserEntity(userId);
        BasketEntity basketEntity = basketService.getBasketEntity(userId);
        ProductEntity productEntity = productService.getProductEntity(productId);

        Optional<BasketProductEntity> basketProductEntity= basketProductRepository.findByBasketEntityAndProductEntity(basketEntity, productEntity);
        int increaseCount = basketProductEntity.get().getCount() + 1;
        basketProductEntity.get().setCount(increaseCount);
        return basketProductRepository.save(basketProductEntity.get());
    }

    public BasketProductEntity minusOnlyOneProduct(int userId, int productId){
        UserEntity userEntity = userService.getUserEntity(userId);
        BasketEntity basketEntity = basketService.getBasketEntity(userId);
        ProductEntity productEntity = productService.getProductEntity(productId);

        Optional<BasketProductEntity> basketProductEntity = basketProductRepository.findByBasketEntityAndProductEntity(basketEntity, productEntity);
        int decreaseCount = basketProductEntity.get().getCount() - 1;
        basketProductEntity.get().setCount(decreaseCount);
        return basketProductRepository.save(basketProductEntity.get());
    }
    public void deleteOneProductInBasket(Integer userId, Integer productId){
        UserEntity userEntity = userService.getUserEntity(userId);
        BasketEntity basketEntity = basketService.getBasketEntity(userId);
        ProductEntity productEntity = productService.getProductEntity(productId);

        Optional<BasketProductEntity> basketProdutEntity = basketProductRepository.findByBasketEntityAndProductEntity(basketEntity, productEntity);
        basketProductRepository.delete(basketProdutEntity.get());
    }
    public void deleteAllProductsInBasket(Integer userId){
        UserEntity userEntity = userService.getUserEntity(userId);
        basketService.deleteBasket(userId);

    }
}
