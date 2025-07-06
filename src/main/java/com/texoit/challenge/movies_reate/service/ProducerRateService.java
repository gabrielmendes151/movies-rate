package com.texoit.challenge.movies_reate.service;

import com.texoit.challenge.movies_reate.data.ProducerIntervalData;
import com.texoit.challenge.movies_reate.data.WinnerProducerIntervalResponse;
import com.texoit.challenge.movies_reate.factory.ProducerIntervalDataFactory;
import com.texoit.challenge.movies_reate.factory.WinnerProducerIntervalResponseFactory;
import com.texoit.challenge.movies_reate.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerRateService {

    private final MovieRepository repository;

    public WinnerProducerIntervalResponse rateWinners() {
        List<ProducerIntervalData> winners = repository.findCustomQuery().stream()
            .map(ProducerIntervalDataFactory::build)
            .toList();

        return WinnerProducerIntervalResponseFactory.build(winners);
    }
}
