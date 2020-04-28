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
  (testing "Should return false if list is not a palindrome"
    (is (= false (is-palindrome [1 2 3])))
    (is (= false (is-palindrome [1 2])))))

;7
(deftest flatten-list-test
  (testing "Should flatten a list"
    (is (= [1 2 4 5] (flatten-list [1 [2 []] [4 5]]))))
    (is (= [1] (flatten-list [[[1]] []])))
    (is (= [] (flatten-list []))))

;8 compress = "eliminate consecutive duplicates"
(deftest compress-test
  (testing "Should eliminate consecutive duplicates"
    (is (= ["a", "b", "d"] (compress ["a" "a" "a" "a" "a" "b" "d" "d"]))))
    (is (= [] (compress [])))
    (is (= ["c"] (compress ["c" "c" "c" "c"]))))

;10 pack
(deftest pack-test
  (testing "Return consecutive elements together in a sequence"
    (is (= [["a" "a" "a" "a" "a"] ["b"] ["d" "d"]] (pack ["a" "a" "a" "a" "a" "b" "d" "d"]))))
    (is (= [["c" "c" "c" "c"]] (pack ["c" "c" "c" "c"])))
    (is (= [] (pack []))))

;10 run-length
(deftest run-length-test
  (testing "Return tuples of elements with number of consecutive duplicates"
    (is (= [["a" 5] ["b" 1] ["d" 2]] (run-length ["a" "a" "a" "a" "a" "b" "d" "d"]))))
    (is (= [] (run-length [])))
    (is (= [["c" 4]] (run-length ["c" "c" "c" "c"]))))

;11 run-length-modified
(deftest run-length-modified-test
  (testing "Return tuples of elements with number of consecutive duplicates or element if consecutive count is 1"
    (is (= [["a" 5] "b" ["d" 2]] (run-length-modified ["a" "a" "a" "a" "a" "b" "d" "d"]))))
    (is (= [] (run-length-modified [])))
    (is (= [["c" 4]] (run-length-modified ["c" "c" "c" "c"]))))

;12 decode-run-length
(deftest decode-run-length-test
  (testing "Return the original sequence from a run length encoding"
    (is (= ["a" "a" "a" "a" "a" "b" "d" "d"] (decode-run-length [["a" 5] ["b" 1] ["d" 2]]))))
    (is (= [] (decode-run-length [])))
    (is (= ["c" "c" "c" "c"] (decode-run-length [["c" 4]]))))

;13 run-length-direct
(deftest run-length-direct-test
  (testing "Return tuples of elements with number of consecutive duplicates"
    (is (= [["a" 5] ["b" 1] ["d" 2]] (run-length-direct ["a" "a" "a" "a" "a" "b" "d" "d"]))))
    (is (= [] (run-length-direct [])))
    (is (= [["c" 4]] (run-length-direct ["c" "c" "c" "c"]))))

;14 duplicate
(deftest duplicate-test
  (testing "Return a vector of each element repeated once"
    (is (= ["a" "a" "b" "b" "c" "c" "e" "e" "a" "a"] (duplicate ["a" "b" "c" "e" "a"]))))
    (is (= [] (duplicate []))))

;15 duplicate-n
(deftest duplicate-n-test
  (testing "Return a vector of each element repeated n times"
    (is (= ["a" "a" "a" "b" "b" "b" "c" "c" "c" "e" "e" "e" "a" "a" "a"] (duplicate-n 3 ["a" "b" "c" "e" "a"])))
    (is (= [] (duplicate-n 4 [])))
    (is (= [1 1 1 1] (duplicate-n 4 [1])))))

;16 drop-every-n
(deftest drop-every-n-test
  (testing "Drop every n elements"
    (is (= [1 2 4 5 7 8 10] (drop-every-n 3 [1 2 3 4 5 6 7 8 9 10])))
    (is (= [] (drop-every-n 4 [])))
    (is (= ["a" "c" "e"] (drop-every-n 2 ["a" "b" "c" "d" "e"])))))

;17 split-at-alt
(deftest split-at-alt-test
  (testing "Split the vector and return a vector of two vectors"
    (is (= [[1 2 3] [4 5 6 7 8 9 10]] (split-at-alt 3 [1 2 3 4 5 6 7 8 9 10])))
    (is (= [[] []] (split-at-alt 4 [])))
    (is (= [[1 2 3 4] []] (split-at-alt 10 [1 2 3 4])))
    (is (= [["a"] ["b" "c" "d"]] (split-at-alt 1 ["a" "b" "c" "d"])))))

;18 slice
(deftest slice-test
  (testing "Return a subset of the vector of the range of specified indices"
    (is (= [4 5 6 7] (slice 3 7 [1 2 3 4 5 6 7 8 9 10])))
    (is (= [] (slice 3 7 [])))
    (is (= ["a" "b" "c" "d"] (slice 0 4 ["a" "b" "c" "d" "e" "f" "g"])))))

;19 rotate
(deftest rotate-test
  (testing "Rotate the vector"
    (is (= ["d" "e" "f" "g" "h" "i" "j" "k" "a" "b" "c"] (rotate 3 ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k"])))
    (is (= ["j" "k" "a" "b" "c" "d" "e" "f" "g" "h" "i"] (rotate -2 ["a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k"])))
    (is (= [] (rotate 3 [])))))

;20 remove-at
(deftest remove-at-test
  (testing "Should return the vector without the item at the specified index")
  (is (= [[1 2 4] 3] (remove-at 2 [1 2 3 4])))
  (is (= [["a" "b" "c" "d"] "e"] (remove-at 4 ["a" "b" "c" "d" "e"])))
  (is (= [["a" "b" "c" "d"] nil] (remove-at 5 ["a" "b" "c" "d"]))))

;21 insert-at
(deftest insert-at-test
  (testing "Should insert the given element at the specified index")
  (is (= [1 2 3 4] (insert-at 2 1 [1 3 4])))
  (is (= ["a" "b" "c" "d" "e"] (insert-at "e" 5 ["a" "b" "c" "d"])))
  (is (= ["a" "b" "c" "d" "e"] (insert-at "a" 0 ["b" "c" "d" "e"]))))

; 22 range-alt
(deftest range-alt-test
  (testing "Should return a range inclusive of the given integers")
  (is (= [4 5 6 7 8 9] (range-alt 4 9)))
  (is (= [-1 0 1 2 3 4] (range-alt -1 4)))
  (is (= [0] (range-alt 0 0))))
