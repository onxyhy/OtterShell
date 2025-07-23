package otter.sherry.ottershell.basketProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basketProducts") //다양한 HTTP 메서드 처리 (Get, Post, Patch...)
@RequiredArgsConstructor
public class BasketProductController {

    private final BasketProductService basketProductService;

    //장바구니에 물건을 담는 API (장바구니 있으면 가져오고 없으면 만들기)
    @PostMapping("/{userId}/add") //왜 userId로 검색을 해야만 했는지가 basketId로 검색하면 안되는 이유 : 장바구니 유무를 모르기때문
    public ResponseEntity<String> getBasketEntity(
            @PathVariable Integer userId,
            @RequestParam Integer productId,
            @RequestParam int count) {

        basketProductService.addProductToBasket(userId, productId, count);
        return ResponseEntity.ok("상품이 장바구니에 추가되었습니다.");
    }

    // 장바구니 상품 전체 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<BasketProductEntity>> getProductsByUser(@PathVariable Integer userId) {
        List<BasketProductEntity> products = basketProductService.getBasketProductsByUser(userId);
        return ResponseEntity.ok(products);
    }

    // 장바구니 상품 하나 삭제
    @DeleteMapping("/{basketProductId}")
    public ResponseEntity<String> delete(@PathVariable Integer basketProductId) {
        basketProductService.deleteBasketProduct(basketProductId);
        return ResponseEntity.ok("상품이 장바구니에서 삭제되었습니다.");
    }
}
