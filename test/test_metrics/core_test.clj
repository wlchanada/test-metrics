(ns test-metrics.core-test
  (:require [clojure.test :refer :all]
            [test-metrics.core :refer :all]))

(deftest double-test 
  (testing "for metricx"
    (is (= 2 (double 1)))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest reg-test
  (testing "send a count to the dog"
    (is (= 0 users-connected))))
