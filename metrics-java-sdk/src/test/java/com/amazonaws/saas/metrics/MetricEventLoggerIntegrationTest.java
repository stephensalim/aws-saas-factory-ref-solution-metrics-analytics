// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
package com.amazonaws.saas.metrics;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import software.amazon.awssdk.regions.Region;

public class MetricEventLoggerIntegrationTest {

    private MetricEvent event;

    @Before
    public void setup() {
        event = new MetricEventBuilder()
                .withType(MetricEvent.Type.Application)
                .withWorkload("AuthApp")
                .withContext("Login")
                .withMetric(new MetricBuilder()
                        .withName("ExecutionTime")
                        .withUnit("msec")
                        .withValue(1000L)
                        .build()
                )
                .withTenant(new TenantBuilder()
                        .withId("123")
                        .withName("ABC")
                        .withTier("Free")
                        .build())
                .addMetaData("user", "111")
                .addMetaData("resource", "s3")
                .build();
    }

    @Ignore
    @Test
    public void logSingleEventShouldSendToKinesisRighAway() {
        MetricEventLogger logger = MetricEventLogger.getLoggerFor("Metrics", Region.US_EAST_1);
        logger.log(event);
    }

    @Ignore
    @Test
    public void logBatchEventShouldSendToKinesisWhenBufferIsFull() {
        int buffer = 5;
        MetricEventLogger logger = MetricEventLogger.getBatchLoggerFor("Metrics", Region.US_EAST_1, buffer, 60);
        for (int i = 0 ; i <= (buffer + 1) ; i++)
            logger.log(event);
    }

    @Ignore
    @Test
    public void logBatchEventShouldSendToKinesisWhenBTimeIsElapsed() throws Exception {
        int buffer = 25;
        MetricEventLogger logger = MetricEventLogger.getBatchLoggerFor("Metrics", Region.US_EAST_1, buffer, 2);
        for (int i = 0 ; i <= (buffer + 1) ; i++) {
            logger.log(event);
            Thread.sleep(1000);
        }

    }




}

