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
    (if (> k (count input-list))
      nil
      (kth input-list 1 k)))
  ([input-list c k]
    (let [[first-item & remaining] input-list]
      (if (= c k)
        first-item
        (recur remaining (+ c 1) k)))))

; 4
(defn length
  ([input-list]
    (if (empty? input-list)
      0
      (length input-list 1)))
  ([input-list l]
    (let [[first-item & remaining] input-list]
      (if (empty? remaining)
        l
        (recur remaining (+ l 1))))))

; 5
(defn reverse-list
  [input-list]
  (into [] (reduce 
    (fn [to-return next-elem]
    (cons next-elem to-return))
    []
    input-list)))

(defn -main
  "run examples here"
  [& args]
  (println (length [1])))
