package otter.sherry.ottershell.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.user.UserEntity;
import otter.sherry.ottershell.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    @Autowired
    private UserRepository userRepository;
    private BasketRepository basketRepository;

    public BasketEntity getBasketEntity (Integer userId){

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        BasketEntity basketEntity = new BasketEntity();
        basketEntity =  basketRepository.findByUserEntity(userId).get();

        if(basketEntity == null)
            basketEntity = basketRepository.save(new BasketEntity());

        return basketEntity;
    }

}

