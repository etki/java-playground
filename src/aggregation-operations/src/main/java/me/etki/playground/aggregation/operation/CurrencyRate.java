package me.etki.playground.aggregation.operation;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class CurrencyRate {
    private final double rate;
    private final Instant timestamp;
}
