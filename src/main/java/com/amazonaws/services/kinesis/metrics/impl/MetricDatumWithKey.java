/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates.
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amazonaws.services.kinesis.metrics.impl;

import java.util.Objects;
import com.amazonaws.services.cloudwatch.model.MetricDatum;

/**
 * This class is used to store a MetricDatum as well as KeyType which stores specific information about
 * that particular MetricDatum.
 *
 * @param <KeyType> is a class that stores information about a MetricDatum. This is useful
 *        to compare MetricDatums, aggregate similar MetricDatums or store information about a datum
 *        that may be relevant to the user (i.e. MetricName, CustomerId, TimeStamp, etc).
 *
 *        Example:
 *
 *        Let SampleMetricKey be a KeyType that takes in the time in which the datum was created.
 *
 *        MetricDatumWithKey<SampleMetricKey> sampleDatumWithKey = new MetricDatumWithKey<SampleMetricKey>(new
 *        SampleMetricKey(System.currentTimeMillis()), datum)
 *
 */
public class MetricDatumWithKey<KeyType> {
    public KeyType key;
    public MetricDatum datum;

    /**
     * @param key an object that stores relevant information about a MetricDatum (e.g. MetricName, accountId,
     *        TimeStamp)
     * @param datum data point
     */

    public MetricDatumWithKey(KeyType key, MetricDatum datum) {
        this.key = key;
        this.datum = datum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, datum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MetricDatumWithKey<?> other = (MetricDatumWithKey<?>) obj;
        return Objects.equals(other.key, key) && Objects.equals(other.datum, datum);
    }

}
