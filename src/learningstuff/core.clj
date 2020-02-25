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
  ([input-list]
    (length input-list 0))
  ([input-list l]
    (if (empty? input-list)
      l
      (recur (rest input-list) (+ 1 l)))))

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
  ([input-list]
  (flatten-list input-list []))
  ([input-list to-return]
  (let [[first-item & remaining] input-list]
    (if (nil? first-item)
      (into [] to-return)
      (if (vector? first-item)
        (recur remaining (concat to-return (flatten-list first-item)))
        (recur remaining (concat to-return [first-item])))))))

(defn -main
  "run examples here"
  [& args]
  (println "Hi"))