package br.com.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ItemRequestDTO {

    @NotBlank
    private Long productId;
    @NotBlank
    private int quantity;

}
