(ns learningstuff.core
  (:gen-class))

; 1
(defn end
  [input-list]
  (let [[first-item & remaining] input-list]
    (if (empty? remaining)
      first-item
      (recur remaining))))

; 2
(defn last-but-one
  [input-list]
  (let [[first-item & remaining] input-list]
    (if (= 1 (count remaining))
      first-item
      (recur remaining))))

; 3
(defn kth
  ([input-list k]
    (if (or (> k (count input-list)) (< k 1))
      nil
      (kth input-list 1 k)))
  ([input-list c k]
    (let [[first-item & remaining] input-list]
      (if (= c k)
        first-item
        (recur remaining (+ c 1) k)))))

; 4
(defn length
  [input-list]
  (reduce 
    (fn [to-return next-elem]
    (inc to-return))
    0
    input-list))

; 5
(defn reverse-list
  [input-list]
  (into [] (reduce 
    (fn [to-return next-elem]
    (cons next-elem to-return))
    []
    input-list)))

; 6
(defn is-palindrome
  [input-list]
  (= (reverse-list input-list) input-list))

; 7
(defn flatten-list
  [input-list]
  (loop [input-list input-list to-return []]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        (into [] to-return)
        (if (vector? first-item)
          (recur remaining (concat to-return (flatten-list first-item)))
          (recur remaining (concat to-return [first-item])))))))

; 8, compress = "eliminate consecutive duplicates"
(defn compress
  [input-list]
  (loop [input-list input-list to-return []]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        (into [] to-return)
        (do 
          (def new-input-list (into [] (drop-while #(= % first-item) input-list)))
          (recur new-input-list (concat to-return [first-item])))))))

; 9, pack = "consecutive duplicates of list elements into sublists"
(defn pack
  [input-list]
  (loop [input-list input-list to-return []]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        (into [] to-return)
        (do 
          (def new-input-list (into [] (drop-while #(= % first-item) input-list)))
          (def packed-items (into [] (take-while #(= % first-item) input-list)))
          (recur new-input-list (into [] (conj to-return packed-items))))))))

; 10, run-length = "count of consecutive duplicates"
(defn run-length
  [input-list]
  (into [] (map (fn [next-elem] [(first next-elem) (count next-elem)]) (pack input-list))))

(defn -main
  "run examples here"
  [& args]
  (println (pack ["a" "a" "a" "a" "a" "b" "d" "d"])))