package me.etki.playground.aggregation.operation.operation;

import lombok.Data;
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

public class AverageByMonth implements AggregationOperation<Map<AverageByMonth.Month, Double>> {
    @Override
    public Map<Month, Double> apply(List<CurrencyRate> history) {
        return history.stream()
                .reduce(
                        new HashMap<Month, List<Double>>(),
                        (carrier, rate) -> {
                            val month = toMonth(rate.getTimestamp());
                            carrier.putIfAbsent(month, new ArrayList<>(32));
                            carrier.get(month).add(rate.getRate());
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

    private static Month toMonth(Instant timestamp) {
        val calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getEpochSecond() * 1000);
        return new Month(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    @Data
    public static class Month {
        private final int year;
        private final int month;
    }
}
