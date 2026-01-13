package com.example.demo.servicetest;

import com.example.demo.entity.users;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class userargumentprovider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(users.builder().userid("950").password("8977").build()),
                Arguments.of(users.builder().userid("9890").password("89700").build())
        );
    }
//
//    @Override
//    public Stream<? extends Arguments> provideA

}
