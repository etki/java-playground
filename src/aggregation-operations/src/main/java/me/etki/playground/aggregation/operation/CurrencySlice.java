package me.etki.playground.aggregation.operation;

import lombok.Data;
import me.etki.playground.aggregation.operation.operation.AverageByMonth;
import me.etki.playground.aggregation.operation.operation.AverageByYear;
import me.etki.playground.aggregation.operation.operation.TotalAverage;

import java.util.List;
import java.util.Map;

@Data
public class CurrencySlice {
    private final List<CurrencyRate> value;

    public Map<Integer, Double> getYearlyAverage() {
        return new AverageByYear().apply(value);
    }

    public Map<AverageByMonth.Month, Double> getMonthlyAverage() {
        return new AverageByMonth().apply(value);
    }

    public double getTotalAverage() {
        return new TotalAverage().apply(value);
    }
}
