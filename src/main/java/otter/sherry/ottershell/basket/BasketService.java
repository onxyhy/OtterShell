package otter.sherry.ottershell.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otter.sherry.ottershell.user.UserEntity;
import otter.sherry.ottershell.user.UserService;

@Service
@RequiredArgsConstructor
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    UserService userService;


    public BasketEntity getBasketEntity (Integer userId){

        UserEntity userEntity = userService.getUserEntity(userId);
        BasketEntity basketEntity = new BasketEntity();
        basketEntity = basketRepository.findByUser(userEntity).orElse(null);
        if(basketEntity == null)
            basketEntity = basketRepository.save(new BasketEntity(userEntity));

        return basketEntity;
    }
    public void deleteBasket (Integer userId){
        UserEntity userEntity = userService.getUserEntity(userId);
        BasketEntity basketEntity = getBasketEntity(userId);
        basketRepository.deleteByBasketId(basketEntity.getBasketId());

    }

}

