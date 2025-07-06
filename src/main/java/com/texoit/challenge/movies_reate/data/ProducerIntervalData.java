package com.texoit.challenge.movies_reate.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProducerIntervalData {
    private String producer;
    private Integer interval;
    private Integer followingWin;
    private Integer previousWin;

    @JsonIgnore
    private String flag;
}
