package otter.sherry.ottershell.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findById(String id);

    UserEntity findByUserId(Integer userId);
}
