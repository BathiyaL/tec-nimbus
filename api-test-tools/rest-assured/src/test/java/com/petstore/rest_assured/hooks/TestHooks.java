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
        // clear state before each scenario if needed
        // (context is a new instance per scenario by default)
    }

    @After
    public void afterScenario() {
        // optional cleanup: if a petId exists, delete it
        Long petId = context.getLong("petId");
        if (petId != null) {
            // call delete endpoint via Rest Assured (optional)
        }
    }
}
