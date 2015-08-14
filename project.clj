(defproject test-metrics "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[metrics-clojure "2.5.1"]
                 [org.clojure/clojure "1.6.0"]
                 [org.coursera/metrics-datadog "1.1.0"]]
  :main ^:skip-aot test-metrics.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
