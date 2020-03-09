package br.com.ecommerceapi.entity.discount;

public class Discount1K implements Discount {

    private Discount next;

    @Override
    public double discount(Double total) {
        if (total >= 1000) {
            return total * 0.95;
        } else {
            return next.discount(total);
        }
    }

    @Override
    public void setNext(Discount next) {
        this.next = next;
    }
}
