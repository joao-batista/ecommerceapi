package br.com.ecommerceapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@ApiModel("Login")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String login;
    @NotBlank
    private String password;


}
