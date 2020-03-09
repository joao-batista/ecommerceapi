package br.com.ecommerceapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@ApiModel("Ticket")
public class TicketDTO implements Serializable {

    private String code;
    private Double discount;

}
