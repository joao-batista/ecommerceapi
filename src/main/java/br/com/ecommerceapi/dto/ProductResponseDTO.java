package br.com.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String price;
    private String description;


}
