package com.candidate.priceline.chutesandladder.model;

import java.io.Serializable;
import java.util.List;

public class ChutesAndLadderConfig implements Serializable {
    private static final long serialVersionUID = 5024825926300416884L;

    private String configId;
    private List<ChuteAndLadder> configuration;

    public ChutesAndLadderConfig() {
    }

    public ChutesAndLadderConfig(String configId, List<ChuteAndLadder> configuration) {
        this.configId = configId;
        this.configuration = configuration;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public List<ChuteAndLadder> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<ChuteAndLadder> configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "ChutesAndLadderConfig{" +
                "configId='" + configId + '\'' +
                ", configuration=" + configuration +
                '}';
    }
}
