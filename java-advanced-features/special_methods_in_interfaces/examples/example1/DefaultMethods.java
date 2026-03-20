package special_methods_in_interfaces.examples.example1;

import java.math.BigDecimal;

public interface DefaultMethods {
    BigDecimal calculatePrice(Object[] orders); // class need to implement this method

    default BigDecimal calculateDiscount(Object[] orders) { // not mandatory for the classed to implement this method,
                                                            // class will use this default implementation
        return BigDecimal.ZERO;
    }

}

class TestInterfaceImp implements DefaultMethods {
    @Override
    public BigDecimal calculatePrice(Object[] orders) {
        // TODO Auto-generated method stub
        return null;
    }

    // as this class is not providing the implementation of the calculateDiscount
    // method
    // default implementation will be used form the interface
}