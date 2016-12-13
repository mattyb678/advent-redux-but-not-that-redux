(ns aoc.day3
  (:require [aoc.utils :as utils]
            [clojure.string :as str]))

(defn -possible-triangle [lst]
  (let [val1 (nth lst 0)
        val2 (nth lst 1)
        val3 (nth lst 2)]
    (and
     (< val1 (+ val2 val3))
     (< val3 (+ val1 val2))
     (< val2 (+ val1 val3)))))

(defn -count-possible [lines]
  (count (filter true? (map #(-possible-triangle %) lines))))

(defn challenge []
  (println "Day 3")
  (let [input-str (utils/input-str "day3.txt")
        lines (map #(map read-string (remove empty? (str/split (str/trim %) #" "))) (str/split-lines input-str))
        part2-lines (partition 3 (flatten (apply mapv vector lines)))]
    (println "Part 1: " (-count-possible lines))
    (println "Part 2: " (-count-possible part2-lines))))
