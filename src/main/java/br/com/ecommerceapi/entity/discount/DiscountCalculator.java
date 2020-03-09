package br.com.ecommerceapi.entity.discount;

public class DiscountCalculator {

    public double calculate(Double total){
        Discount d1 = new Discount10K ();
        Discount d2 = new Discount5K();
        Discount d3 = new Discount1K();
        Discount d4 = new NoDiscount();

        d1.setNext(d2);
        d2.setNext(d3);
        d3.setNext(d4);

        return d1.discount(total);
    }
}
