package com.petstore.rest_assured.hooks;

import com.petstore.rest_assured.support.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class TestHooks {

    private final ScenarioContext context;

    public TestHooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario() {
        System.out.println("Starting a new scenario...");
    }

    @After
    public void afterScenario() {
        System.out.println("Scenario finished.");
    }
}
