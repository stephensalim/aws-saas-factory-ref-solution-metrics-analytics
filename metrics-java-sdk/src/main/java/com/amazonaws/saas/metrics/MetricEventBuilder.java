// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
package com.amazonaws.saas.metrics;

import com.amazonaws.saas.metrics.MetricEvent.Type;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricEventBuilder {
    private static final Logger logger = LoggerFactory.getLogger(MetricEventBuilder.class);
    private MetricEvent metricEvent;

    public MetricEventBuilder() {
        metricEvent = new MetricEvent();
    }

    public MetricEventBuilder withType(Type type) {
        this.metricEvent.setType(Type.Application);
        return this;
    }

    public MetricEventBuilder withWorkload(String workload) {
        this.metricEvent.setWorkload(workload);
        return this;
    }

    public MetricEventBuilder withContext(String context) {
        this.metricEvent.setContext(context);
        return this;
    }

    public MetricEventBuilder withTenant(Tenant tenant) {
        this.metricEvent.setTenant(tenant);
        return this;
    }

    public MetricEventBuilder withMetaData(Map<String, String> metaData) {
        this.metricEvent.setMetaData(metaData);
        return this;
    }

    public MetricEventBuilder addMetaData(String key, String value) {
        this.metricEvent.getMetaData().put(key, value);
        return this;
    }

    public MetricEventBuilder withMetric(Metric metric) {
        this.metricEvent.setMetric(metric);
        return this;
    }

    public MetricEvent build() {
        if (this.metricEvent.isValid()) {
            return this.metricEvent;
        } else {
            logger.debug("Error: MetricEvent is missing required data");
            return null;
        }
    }
}

