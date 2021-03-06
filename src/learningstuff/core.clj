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
          (let [new-input-list (into [] (drop-while #(= % first-item) input-list))]
            (recur new-input-list (concat to-return [first-item]))))))))

; 9, pack = "consecutive duplicates of list elements into sublists"
(defn pack
  [input-list]
  (loop [input-list input-list to-return []]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        (into [] to-return)
        (do 
          (let [new-input-list (into [] (drop-while #(= % first-item) input-list))]
            (let [packed-items (into [] (take-while #(= % first-item) input-list))]
            (recur new-input-list (into [] (conj to-return packed-items))))))))))

; 10, run-length = "count of consecutive duplicates"
(defn run-length
  [input-list]
  (into [] (map (fn [next-elem] [(first next-elem) (count next-elem)]) (pack input-list))))

; 11, run-length-modified = "run-length but if count == 1, then just copy the element"
(defn run-length-modified
  [input-list]
  (into [] (map 
    (fn [next-elem]
      (if (= 1 (last next-elem))
        (first next-elem)
        next-elem))
    (run-length input-list))))

; 12, decode-run-length
(defn decode-run-length
  [input-list]
  (into [] (reduce 
    (fn [to-return next-elem]
      (let [next-repeat (into [] (repeat (last next-elem) (first next-elem)))]
        (concat to-return next-repeat)))
      []
      input-list)))

; 13, run-length-direct = "count of consecutive duplicates (without using other methods)"
(defn run-length-direct
  [input-list]
  (loop [input-list input-list to-return []]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        (into [] to-return)
        (do 
          (def new-input-list (into [] (drop-while #(= % first-item) input-list)))
          (def packed-size (count (take-while #(= % first-item) input-list)))
          (recur new-input-list (into [] (conj to-return [first-item packed-size]))))))))

; 14, duplicate
(defn duplicate
  [input-list]
  (into [] (reduce
    (fn [to-return next-elem]
      (concat to-return [next-elem next-elem]))
      []
      input-list)))

; 15, duplicate-n
(defn duplicate-n
  [n input-list]
  (into [] (reduce
    (fn [to-return next-elem]
      (concat to-return (into [] (repeat n next-elem))))
      []
      input-list)))

; 16, drop-every-n
(defn drop-every-n
  [n input-list]
  (into [] (keep-indexed #(if (not= 0 (mod (inc %1) n)) %2) input-list)))

; 17, split-at-alt
(defn split-at-alt
  [n input-list]
  (loop [input-list input-list to-return-start [] to-return-end [] c 1]
    (let [[first-item & remaining] input-list]
      (if (nil? first-item)
        [to-return-start to-return-end]
        (do 
          (def new-to-return-start
            (if (< n c)
              to-return-start
              (into [] (concat to-return-start [first-item])))) 
          (def new-to-return-end
            (if (>= n c)
              to-return-end
              (into [] (concat to-return-end [first-item]))))
          (recur remaining new-to-return-start new-to-return-end (inc c)))))))

; 18, slice
(defn slice
  [start end input-list]
  (loop [input-list input-list to-return [] c 1]
    (let [[first-item & remaining] input-list]
      (if (or (nil? first-item) (> c end))
        (into [] to-return)
        (do 
          (if (> c start)
            (recur remaining (concat to-return [first-item]) (inc c))
            (recur remaining to-return (inc c))))))))

; 19, rotate n places to the left
(defn rotate
  [n input-list]
  (if (= 0 (count input-list))
    []
    (do
      (let [start-index (mod n (count input-list))]
        (let [start (drop start-index input-list)]
          (let [end (take start-index input-list)]
            (into [] (concat start end))))))))

; 20, remove-at index and return removed element
(defn remove-at
  [n input-list]
  (loop [remaining-list input-list to-return [] c 0]
    (let [[first-item & remaining] remaining-list]
      (if (nil? first-item)
        [input-list nil]
      (do
        (if (= c n)
          [(concat to-return remaining) first-item]
          (recur remaining (concat to-return [first-item]) (inc c))))))))

; 21, insert-at
(defn insert-at
  [new-elem n input-list]
  (let [start-list (take n input-list)]
    (let [end-list (drop n input-list)]
      (into [] (concat start-list [new-elem] end-list)))))

; 22, range-alt
(defn range-alt
  [start end]
  (loop [to-return [] c start]
    (if (> c end)
      to-return
      (recur (concat to-return [c]) (inc c)))))

(defn -main
  "run examples here"
  [& args]
  (println (rotate 2 [1 2 3 4 5 6])))