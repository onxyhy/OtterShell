package otter.sherry.ottershell.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder; //스프링이 이미 만들어서 가지고 있음(보안 설정, 알고리즘 종류 등을 자동으로 설정)


    // 이 사이에 userDetails 포함
    //: UserDetails는 Spring Security에서 '사용자 한 명'을 표현하기 위한 인터페이스(약속)
    //Spring Security는 로그인을 처리하려고 할 때 "이 사용자 누구야?" 하고 UserDetails 타입의 객체를 요구
    //우리는 우리의 사용자 클래스(UserEntity 등)가 UserDetails라는 인터페이스를 "구현(implements)"해서 Spring Security한테 "이게 사용자 정보야"라고 넘겨줘야 해요
    public UserEntity signUp(UserEntity userEntity){
        userEntity.setUserPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public UserEntity getUserEntity(int userId){
        return userRepository.findByUserId(userId);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findById(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("username '" + username + "' not found"));
    }



}
