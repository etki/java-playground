package me.etki.playground.aggregation.operation;

import java.util.List;

public interface AggregationOperation<T> {
    T apply(List<CurrencyRate> history);
}
