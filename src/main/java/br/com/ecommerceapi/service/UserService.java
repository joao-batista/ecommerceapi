package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User findByLogin(String login);

    boolean existsByLogin(String login);

    User save(User user);

    User update(Long id, User user);

    User findById(Long id);

    void deleteById(Long id);

    User saveImage(MultipartFile file, Long id);
}
