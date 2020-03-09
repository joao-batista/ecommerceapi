package br.com.ecommerceapi.controller;

import br.com.ecommerceapi.dto.LoginDTO;
import br.com.ecommerceapi.dto.TokenDTO;
import br.com.ecommerceapi.security.AuthService;
import br.com.ecommerceapi.security.TokenProvider;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signin")
    public ResponseEntity<TokenDTO> signin(@Valid @RequestBody @ApiParam(value = "credentials", name = "login") LoginDTO loginDTO,
                                           HttpServletResponse response) throws AuthenticationException {

        TokenDTO tokenDTO = new TokenDTO();
        String login    = loginDTO.getLogin();
        String password = loginDTO.getPassword();
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        tokenDTO.setToken(token);

        return ResponseEntity.ok(tokenDTO);
    }


}
