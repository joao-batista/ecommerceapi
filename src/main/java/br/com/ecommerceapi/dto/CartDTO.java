package br.com.ecommerceapi.dto;

import br.com.ecommerceapi.entity.Item;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@ApiModel("Cart")
public class CartDTO implements Serializable {

    private Set<Item> items = new HashSet<>();
    private double total;
    private double totalDiscount;
    private double totalDiscountTicket;

}
