package otter.sherry.ottershell.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    //상품 등록
    public ProductEntity addProduct(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }
    //상품 전체 조회
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }
    //상품 삭제
    public void deleteProduct(Integer id){
        // id 값을 통해 repository에서 엔티티 한개 조회해서 오기
        ProductEntity productEntity = productRepository.findById(id).get();
        productRepository.delete(productEntity);
    }
    //상품 1개 조회
    public ProductEntity getAProduct(Integer id){
        ProductEntity productEntity = productRepository.findById(id).get();
        return productEntity;
    }
}
