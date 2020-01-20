package tax;

import java.time.LocalDate;

public class DefaultTaxCalculator extends TaxCalculator {

    @Override
    int calculateTax(Vehicle vehicle) {

        int taxPrice = 0;
        // first check if it is the first tax or not,
        // if it is then do something else
        // if not then just pay for tax 140, 0 or 130
        // or if the price is more than 40k then 450, 310 or 440

        LocalDate today = LocalDate.now();

        if (vehicle.getDateOfFirstRegistration().getYear() >= (getYear() - 1)) {
            taxPrice = CO2Range
                    .getCO2Range(vehicle.getCo2Emissions())
                    .getPriceForCO2FromFuelType(vehicle.getFuelType());
        }else{
            taxPrice = calculateTaxForSecondaryYears(vehicle.getFuelType(), vehicle.getListPrice());
        }
        return taxPrice;
    }

    private int calculateTaxForSecondaryYears(FuelType fuelType, int vehiclePrice) {
        switch (fuelType) {
            case PETROL:
            case DIESEL:
                return vehiclePrice < 40000 ? 140 : 450;
            case ALTERNATIVE_FUEL:
                return vehiclePrice < 40000 ? 130 : 440;
            case ELECTRIC:
                return vehiclePrice < 40000 ? 0 : 310;
            default:
                return 0;
        }
    }

}
