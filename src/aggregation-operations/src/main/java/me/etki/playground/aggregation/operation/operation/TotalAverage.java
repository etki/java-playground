package me.etki.playground.aggregation.operation.operation;

import lombok.val;
import lombok.var;
import me.etki.playground.aggregation.operation.AggregationOperation;
import me.etki.playground.aggregation.operation.CurrencyRate;

import java.util.List;

public class TotalAverage implements AggregationOperation<Double> {
    @Override
    public Double apply(List<CurrencyRate> history) {
        var accumulator = 0D;
        for (val entry : history) {
            accumulator += entry.getRate() / history.size();
        }
        return accumulator;
    }

    public static double average(Iterable<Double> values) {
        val accumulator = 0D;
        for (val value : values) {
            accumulator += value;
        }
        return accumulator;
    }
}
