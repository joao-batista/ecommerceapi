package br.com.ecommerceapi.entity;

import br.com.ecommerceapi.entity.discount.DiscountCalculator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "cart")
    private Set<Item> items = new HashSet<>();

    private double total = 0.0;
    private double totalDiscount = 0.0;
    private double totalDiscountTicket = 0.0;

    public double getTotal() {
        total = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
        return total;
    }

    public double getTotalDiscount() {
        totalDiscount = items.stream().mapToDouble(item ->
        {
            Double price = item.getQuantity() >= 10 ? item.getProduct().getPrice() * 0.9 : item.getProduct().getPrice();
            return price * item.getQuantity();
        }).sum();

        DiscountCalculator calculator = new DiscountCalculator();
        totalDiscount = calculator.calculate(totalDiscount);

        return totalDiscount;
    }

}
