(ns learningstuff.core-test
  (:require [clojure.test :refer :all]
            [learningstuff.core :refer :all]))

;1
(deftest end-test
  (testing "Should return end of list"
    (is (= 1 (end [5 4 3 2 1])))
    (is (= 9 (end [9]))))
  (testing "Should return nil for empty list")
    (is (= nil (end []))))

;2
(deftest last-but-one-test
  (testing "Should return end of list"
    (is (= 2 (last-but-one [5 4 3 2 1])))
    (is (= 1 (last-but-one [1 9])))))

;3
(deftest kth-test
  (testing "Should return kth element"
    (is (= 1 (kth [1 2 3 4] 1)))
    (is (= 2 (kth [1 2 3 4] 2)))
    (is (= 3 (kth [1 2 3 4] 3)))
    (is (= 5 (kth [1 2 3 5] 4))))
  (testing "Should return nil when k is greater than list size")
    (is (= nil (kth [] 1)))
    (is (= nil (kth [1 2] 5)))
  (testing "Should return nil when k is negative")
    (is (= nil (kth [1] -1))))

;4
(deftest length-test
  (testing "Should return the length of a list"
    (is (= 4 (length [1 2 3 4])))
    (is (= 1 (length [4])))
    (is (= 0 (length [])))))

;5
(deftest reverse-list-test
  (testing "Should reverse a list"
    (is (= [4 3 2 1] (reverse-list [1 2 3 4])))
    (is (= [1] (reverse-list [1])))
    (is (= [] (reverse-list [])))))

;6
(deftest is-palindrome-test
  (testing "Should return true if list is a palindrome"
    (is (= true (is-palindrome [1 2 3 5 3 2 1])))
    (is (= true (is-palindrome [1 2 3 3 2 1])))
    (is (= true (is-palindrome [1 3 1])))
    (is (= true (is-palindrome [1])))
    (is (= true (is-palindrome []))))
  (testing "Should return true if list is a palindrome"
    (is (= false (is-palindrome [1 2 3])))
    (is (= false (is-palindrome [1 2])))))