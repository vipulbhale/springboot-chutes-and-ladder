package com.candidate.priceline.chutesandladder.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Spinner {

    public int spin() {
        return new Random().nextInt(6) + 1;
    }
}
