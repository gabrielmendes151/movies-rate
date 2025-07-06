package com.texoit.challenge.movies_reate.factory;

import com.texoit.challenge.movies_reate.data.ProducerIntervalData;
import com.texoit.challenge.movies_reate.data.WinnerProducerIntervalResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WinnerProducerIntervalResponseFactory {

    private final static String MIN_FLAG = "min";
    private final static String MAX_FLAG = "max";

    public static WinnerProducerIntervalResponse build(List<ProducerIntervalData> results) {
        return WinnerProducerIntervalResponse.builder()
                .min(results.stream().filter(winner -> winner.getFlag().equals(MIN_FLAG)).toList())
                .max(results.stream().filter(winner -> winner.getFlag().equals(MAX_FLAG)).toList())
                .build();
    }
}
