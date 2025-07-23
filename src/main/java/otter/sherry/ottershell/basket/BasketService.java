package otter.sherry.ottershell.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.user.UserRepository;

@Service
public class BasketService {

    @Autowired
    private UserRepository userRepository;

    public BasketEntity getBasketEntity (Integer userId){

        BasketEntity basketEntity = BasketRepository.findByUserId(userId)

        if(basketEntity == null)
            basketEntity = BasketRepository.save(new BasketEntity(userId));

        return basketEntity
    }

}

