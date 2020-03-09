package br.com.ecommerceapi.controller;

import br.com.ecommerceapi.dto.UserResponseDTO;
import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.dto.UserRequestDTO;
import br.com.ecommerceapi.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid @ApiParam(name = "User", value = "User") UserRequestDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.save(convertToEntity(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @ApiParam(name = "User", value = "User") @RequestBody UserRequestDTO userDTO) {
        User user = userService.update(id, convertToEntity(userDTO));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(convertToDTO(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<UserResponseDTO> upload(@RequestParam("image") MultipartFile image, @PathVariable("id") Long id) {
        User user = userService.saveImage(image, id);
        return ResponseEntity.ok(convertToDTO(user));
    }

    private User convertToEntity(UserRequestDTO userDTO) {
        return Mapper.map(userDTO, User.class);
    }

    private UserResponseDTO convertToDTO(User user) {
        return Mapper.map(user, UserResponseDTO.class);
    }

}
