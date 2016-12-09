(ns aoc.day2
  (:require [aoc.utils :as utils]
            [clojure.string :as str]))

(def num-pad [[1 2 3]
              [4 5 6]
              [7 8 9]])

(def big-pad [[nil nil \1 nil nil]
              [nil \2 \3 \4 nil]
              [\5 \6 \7 \8 \9]
              [nil \A \B \C nil]
              [nil nil \D nil nil]])

(defn -move [pad [row col] dir]
  (let [new-pos (case dir
                  \U [(dec row) col]
                  \D [(inc row) col]
                  \L [row (dec col)]
                  \R [row (inc col)])]
    (if (get-in pad new-pos)
      new-pos
      [row col])))

(defn -process [lines pad start]
  (str/join (map (partial get-in pad)
       (loop [lns lines pos start res []]
    (if (empty? lns)
      res
      (let [new-pos (reduce (partial -move pad) pos (first lns))]
        (recur (rest lns)
               new-pos
               (conj res new-pos))))))))

(defn challenge []
  (println "Day 2")
  (let [lines (str/split-lines (utils/input-str "day2.txt"))]
   (println "Part 1: " (-process lines num-pad [1 1]))
   (println "Part 2: " (-process lines big-pad [2 0]))))
