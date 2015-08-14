(ns test-metrics.metrics
  (:require
    [metrics.core :as metrics]
    [metrics.reporters :as reporters]
    [metrics.reporters.console :as console])
  (:import
    [java.util
     EnumSet]
    [org.coursera.metrics.datadog
     DatadogReporter
     DatadogReporter$Expansion
     MetricNameFormatter]
    [org.coursera.metrics.datadog.transport
     HttpTransport$Builder]))

(defn ec2-datadog-reporter
  "Creates a DataDog metrics reporter"
  [registry api-key]
  (let [transport (.build (doto (HttpTransport$Builder.)
                            (.withApiKey api-key)))
        ;; dealing with varargs interop:
        expansions
        (EnumSet/of DatadogReporter$Expansion/COUNT
                    (into-array DatadogReporter$Expansion
                                [DatadogReporter$Expansion/RATE_1_MINUTE
                                 DatadogReporter$Expansion/RATE_5_MINUTE
                                 DatadogReporter$Expansion/MEAN
                                 DatadogReporter$Expansion/MEDIAN
                                 DatadogReporter$Expansion/P95
                                 DatadogReporter$Expansion/P99]))]
    (.build (doto (DatadogReporter/forRegistry registry)
;              (.withEC2Host)
              (.withTransport transport)
              (.withExpansions expansions)))))

(declare registry)

(defn reporting-registry
  [interval reporter-fn & args]
  (let [registry (registry)
        reporter (apply reporter-fn registry args)]
    (reporters/start reporter interval)
    registry))

;; public api:
;(def metric-registry (registry))

(defn registry
  "Creates a metrics registry."
  []
  (metrics/new-registry))

(defn ec2-datadog-registry
  "Creates a metrics registry that reports to DataDog."
  [interval api-key]
  (reporting-registry interval ec2-datadog-reporter api-key))

(defn console-registry
  "Creates a metrics registry that reports to STDOUT."
  [interval console-options]
  (reporting-registry interval console/reporter console-options))

