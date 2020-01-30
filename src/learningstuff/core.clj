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
  (println (reverse-list [1 2 3 4 5])))
