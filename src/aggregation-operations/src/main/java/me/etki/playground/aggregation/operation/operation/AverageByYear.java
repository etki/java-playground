package me.etki.playground.aggregation.operation.operation;

import lombok.val;
import me.etki.playground.aggregation.operation.AggregationOperation;
import me.etki.playground.aggregation.operation.CurrencyRate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageByYear implements AggregationOperation<Map<Integer, Double>> {
    @Override
    public Map<Integer, Double> apply(List<CurrencyRate> history) {
        return history.stream()
                .reduce(
                        new HashMap<Integer, List<Double>>(),
                        (carrier, rate) -> {
                            val year = toYear(rate.getTimestamp());
                            carrier.putIfAbsent(year, new ArrayList<>(32));
                            carrier.get(year).add(rate.getRate());
                            return carrier;
                        },
                        (a, b) -> a
                )
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> TotalAverage.average(entry.getValue())
                ));
    }

    private static int toYear(Instant timestamp) {
        val calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getEpochSecond() * 1000);
        return calendar.get(Calendar.YEAR);
    }
}
