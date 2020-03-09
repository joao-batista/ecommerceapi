package br.com.ecommerceapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ApiModel(value="Product")
public class ProductRequestDTO {

    @NotBlank(message = "{null.field}")
    private String name;

    @NotBlank(message = "{null.field}")
    private String price;

    @NotBlank(message = "{null.field}")
    private String description;



}
