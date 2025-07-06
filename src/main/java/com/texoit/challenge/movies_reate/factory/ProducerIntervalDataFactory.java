package com.texoit.challenge.movies_reate.factory;

import com.texoit.challenge.movies_reate.data.ProducerIntervalData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProducerIntervalDataFactory {

    private enum Position {
        PRODUCER(0),
        INTERVAL(1),
        FOLLOWING_WIN(2),
        PREVIOUS_WIN(3),
        FLAG(4);

        private final int index;

        Position(int index) {
            this.index = index;
        }

        static int minSize() {
            return Arrays.stream(values())
                .mapToInt(p -> p.index)
                .max()
                .orElse(0) + 1;
        }
    }

    public static ProducerIntervalData build(Object[] data) {
        validateInput(data);

        try {
            return ProducerIntervalData.builder()
                .producer(getStringValue(data, Position.PRODUCER))
                .interval(getIntegerValue(data, Position.INTERVAL))
                .followingWin(getIntegerValue(data, Position.FOLLOWING_WIN))
                .previousWin(getIntegerValue(data, Position.PREVIOUS_WIN))
                .flag(getStringValue(data, Position.FLAG))
                .build();
        } catch (Exception e) {
            log.error("Erro ao construir ProducerIntervalResult a partir de: {}", Arrays.toString(data), e);
            throw new IllegalArgumentException("Falha ao criar ProducerIntervalResult", e);
        }
    }

    private static void validateInput(Object[] data) {
        if (Objects.isNull(data)) {
            throw new IllegalArgumentException("O array de entrada está nulo");
        }
        if (data.length < Position.minSize()) {
            throw new IllegalArgumentException(String.format(
                "O array deve ter pelo menos %d posições, mas possui %d",
                Position.minSize(), data.length));
        }
    }

    private static String getStringValue(Object[] data, Position pos) {
        if (pos.index >= data.length || Objects.isNull(data[pos.index])) {
            return null;
        }
        String value = data[pos.index].toString().trim();
        return value.isEmpty() ? null : value;
    }

    private static Integer getIntegerValue(Object[] data, Position pos) {
        String str = getStringValue(data, pos);
        if (Objects.isNull(str)) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            log.warn("Valor não numérico '{}' na posição {}", str, pos);
            return null;
        }
    }
}