package br.com.ecommerceapi.entity.discount;

public class NoDiscount implements Discount {

    private Discount next;

    @Override
    public double discount(Double total) {
        return total;
    }

    @Override
    public void setNext(Discount next) {
        //
    }
}
