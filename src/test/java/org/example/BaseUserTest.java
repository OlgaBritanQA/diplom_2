package org.example;

import org.example.webclients.UpdateUserClient;

public class BaseUserTest extends BaseTest{
    protected static UpdateUserClient updateUserClient;

    @Override
    public void setUp() {
        super.setUp();
        updateUserClient = new UpdateUserClient();
    }
}
