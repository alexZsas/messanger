package com.messanger.auth.common.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class MapperHelper {

    public static Collection<String> mapSplit(String str) {
        return mapSplit(str, ",", Function.identity(), ArrayList::new);
    }

    public static <C extends Collection<String>> C mapSplit(String str, Supplier<? extends C> collector) {
        return mapSplit(str, ",", Function.identity(), collector);
    }

    public static <E> Collection<E> mapSplit(String str, Function<String, E> function) {
        return mapSplit(str, ",", function, ArrayList::new);
    }

    public static <E, C extends Collection<E>> C mapSplit(String str,
                                                          Function<String, E> function,
                                                          Supplier<? extends C> collector) {
        return mapSplit(str, ",", function, collector);
    }

    public static <E, C extends Collection<E>> C mapSplit(String str,
                                                          String delim,
                                                          Function<String, E> function,
                                                          Supplier<C> collector) {
        return Optional.ofNullable(str)
                .filter(not(String::isEmpty))
                .map(s -> s.split(delim))
                .stream()
                .flatMap(Arrays::stream)
                .map(function)
                .collect(Collectors.toCollection(collector));
    }
}
