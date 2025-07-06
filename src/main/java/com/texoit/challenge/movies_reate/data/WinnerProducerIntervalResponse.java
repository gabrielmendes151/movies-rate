package com.texoit.challenge.movies_reate.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WinnerProducerIntervalResponse {
    private List<ProducerIntervalData> min;

    private List<ProducerIntervalData> max;

}
