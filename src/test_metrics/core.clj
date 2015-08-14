(ns test-metrics.core
  (:gen-class)
  ;(require '[merckx.processors.metrics :as metricx])
  (:require 
            [metrics.core :refer [new-registry]]
            [test-metrics.metrics :as metrics]
            [metrics.reporters.console :as console]
            [metrics.counters :refer [counter]]
            [metrics.reporters :as reporters]
            [metrics.counters :refer [inc!]]))

(def reg (new-registry))
;(def users-connected (counter reg "users-connected"))
;(def CR (console/reporter reg {}))
;(console/start CR 10)



(def users-connected (counter reg "users-connected"))
(def datadog-reporter (metrics/ec2-datadog-reporter reg "e5092fdfe4caad7771dcba6815d1ac13"))
;(def datadog-reporter (metrics/ec2-datadog-reporter))
(reporters/start datadog-reporter reg) 

(declare reg)
(declare test2)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (test2 1))
  (println (test2 2))
  (test2 3)
  (test2 4)
  (test2 5)
  (test2 6)
  (test2 7)
  (test2 8)
  (test2 9))

(defn test2
  [x]
  (* 2 x)
  ;  (counter/inc!)
  (inc! users-connected)
  (Thread/sleep 100000)
)
