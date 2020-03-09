package br.com.ecommerceapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value="Token")
public class TokenDTO {

    private String token;

}
