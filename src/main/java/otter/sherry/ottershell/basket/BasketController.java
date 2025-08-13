package otter.sherry.ottershell.basket;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {
    @Autowired
    BasketService basketService;

    @GetMapping("/{userId}/basket")
    @Operation(summary = "장바구니 목록 보기", description = "장바구니 물건 확인하는 API")
    // swagger 관련 API : “이 API는 장바구니 보기용이야. Swagger 문서에 그렇게 표시해줘!”
    public BasketEntity getBasketEntity(@PathVariable Integer userId) {
        return basketService.getBasketEntity(userId);
    }

}

