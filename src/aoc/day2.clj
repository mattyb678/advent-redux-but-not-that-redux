(ns aoc.day2
  (:require [aoc.utils :as utils]
            [clojure.string :as str]))

(def num-pad [[1 2 3]
              [4 5 6]
              [7 8 9]])

(defn -move [pad [row col] dir]
  (let [new-pos (case dir
                  \U [(dec row) col]
                  \D [(inc row) col]
                  \L [row (dec col)]
                  \R [row (inc col)])]
    (if (get-in pad new-pos)
      new-pos
      [row col])))

(defn -part1 [lines]
  (str/join (map (partial get-in num-pad)
       (loop [lns lines pos [1 1] res []]
    (if (empty? lns)
      res
      (let [new-pos (reduce (partial -move num-pad) pos (first lns))]
        (recur (rest lns)
               new-pos
               (conj res new-pos))))))))

(defn challenge []
  (println "Day 2")
  (let [lines (str/split-lines (utils/input-str "day2.txt"))]
   (println "Part 1: " (-part1 lines)) ))
