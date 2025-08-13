package otter.sherry.ottershell.product;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import otter.sherry.ottershell.user.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    @Operation(summary = "상품등록", description = "구매할 상품을 등록하는 API(로그인 필요)")
    public ProductEntity addProduct(@RequestBody ProductEntity product, @AuthenticationPrincipal UserEntity user) { // 지금 로그인한 사용자를 가져오는 어노테이션
                                    // 로그인 확인 용도로 @AuthenticationPrincipal 사용
        return productService.addProduct(product);
    }

    @GetMapping
    @Operation(summary = "상품 목록 조회", description = "모든 상품을 조회하는 API (로그인 필요)")
    public List<ProductEntity> getAllProducts(@AuthenticationPrincipal UserEntity user) {
        return productService.getAllProducts();
    }

    @DeleteMapping("{id}")
    @Operation(summary="상품삭제", description = "상품 하나를 삭제하는 API(로그인 필요)")
    public void deleteProduct(@PathVariable Integer id,@AuthenticationPrincipal UserEntity user) {
        productService.deleteProduct(id);
    }

//    @GetMapping("/{id}")
//    @Operation(summary = "상품 1개 조회", description = "상품 1개 조회하는 API(로그인 필요)")
//    public ProductEntity getAProduct(@PathVariable Integer id, @AuthenticationPrincipal UserEntity user) {
//        return productService.getAProduct(id);
//    }

}
