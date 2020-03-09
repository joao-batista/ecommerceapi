package br.com.ecommerceapi.entity.discount;

public interface Discount {

    double discount(Double total);
    void setNext(Discount next);
}
