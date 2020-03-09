package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Cart;
import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.CartRepository;
import br.com.ecommerceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;


    @Autowired
    private ImageService imageService;


    @Override
    @Transactional
    public User save(User user) {
        User savedUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(savedUser);
        Cart savedCart = cartRepository.save(cart);
        savedUser.setCart(savedCart);
        return userRepository.save(savedUser);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User userDB = findById(id);
        User userMapper = Mapper.map(user, userDB);
        return userRepository.save(userMapper);
    }

    @Override
    public User findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User id '" + id + "' does no exist"));
        return user;
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);

    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }


    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login : " + login));
    }

    @Override
    public User saveImage(MultipartFile file, Long id) {
        byte[] image = imageService.processImage(file);
        User user = findById(id);
        user.setImage(image);
        return save(user);
    }
}

