package br.com.ecommerceapi.entity.discount;

public class Discount10K implements Discount {

    private Discount next;

    @Override
    public double discount(Double total) {
        if (total >= 10000) {
            return total * 0.90;
        } else {
            return next.discount(total);
        }
    }

    @Override
    public void setNext(Discount next) {
        this.next = next;
    }
}
