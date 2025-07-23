package otter.sherry.ottershell.product;

import org.springframework.data.jpa.repository.JpaRepository; // SQL을 쓰지 않더라도 DB를 위한 CRUD작업을 해줌

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
