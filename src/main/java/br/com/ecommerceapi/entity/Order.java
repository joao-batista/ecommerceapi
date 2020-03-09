package br.com.ecommerceapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name="order_generator", sequenceName = "order_seq")
    private long id;
    private Double totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    private String buyerEmail;

    private String buyerName;

    private String buyerPhone;

    public Order(User user) {
        this.buyerEmail = user.getEmail();
        this.buyerName  = user.getFirstName();
        this.buyerPhone = user.getPhone();
    }
}
