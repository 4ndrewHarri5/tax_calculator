package tax;

import java.time.LocalDate;

abstract class TaxCalculator{
    private final int year;

    abstract int calculateTax(Vehicle vehicle);

    TaxCalculator(){
        this(LocalDate.now().getYear());
    }

    TaxCalculator(int year){
        this.year = 2019;
    }

    int getYear() {
        return year;
    }
}
