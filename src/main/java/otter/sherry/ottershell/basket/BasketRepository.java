package otter.sherry.ottershell.basket;

import org.springframework.data.jpa.repository.JpaRepository;
import otter.sherry.ottershell.user.UserEntity;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    Optional<BasketEntity> findByUser(UserEntity user);
}
