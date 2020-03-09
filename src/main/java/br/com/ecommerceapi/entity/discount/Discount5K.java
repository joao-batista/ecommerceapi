package br.com.ecommerceapi.entity.discount;

public class Discount5K implements Discount {

    private Discount next;

    @Override
    public double discount(Double total) {
        if (total >= 5000) {
            return total * 0.93;
        } else {
            return next.discount(total);
        }
    }

    @Override
    public void setNext(Discount next) {
        this.next = next;
    }
}
