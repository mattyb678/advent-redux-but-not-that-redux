(ns aoc.day1
  (:require  [aoc.utils :as utils]
             [clojure.string :as str]
             [clojure.set :as set]))

(def num-dir {0 :north 1 :east 2 :south 3 :west})
(def dir-num {:north 0 :east 1 :south 2 :west 3})
(def turn {\R 1 \L -1})
(def start-position [:north 0 0])

(defn -move [[dir blocksEW blocksNS] step]
  (let [delta (read-string (apply str (rest step)))
        newdir (num-dir (mod (+ (dir-num dir) (turn (first step))) (count dir-num)))]
    (cond
      (= :north newdir) [:north blocksEW (+ delta blocksNS)]
      (= :east newdir) [:east (+ delta blocksEW) blocksNS]
      (= :south newdir) [:south blocksEW (- blocksNS delta)]
      (= :west newdir) [:west (- blocksEW delta) blocksNS])))

(defn -distance-away [blocksEW blocksNS]
  (+ (Math/abs blocksEW) (Math/abs blocksNS)))

(defn -part1 [directions]
  (let [[dir blocksEW blocksNS] (reduce -move start-position  directions)]
    (-distance-away blocksEW blocksNS)))

(defn -intermediate-positions [start end]
  (let [startX (start 1)
        startY (start 2)
        endX (end 1)
        endY (end 2)]
    (if (= startX endX)
      (map #(vector startX %) (flatten (conj (range (inc endY) startY) (range (dec endY) startY -1))))
      (map #(vector % startY) (flatten (conj (range (inc endX) startX) (range (dec endX) startX -1)))))))

(defn -part2 [directions]
  (loop [pos start-position
         dirs directions
         visited #{[(start-position 1) (start-position 2)]}]
    (let [next-pos (-move pos (first dirs))
          intermediate-pos (-intermediate-positions pos next-pos)
          intersection (set/intersection (set visited) (set intermediate-pos))]      
      (if (not (empty? intersection))
        (-distance-away ((first intersection) 0) ((first intersection) 1))
        (recur next-pos (rest dirs) (concat visited intermediate-pos))))))

(defn challenge []
  (println "Day 1")
  (let [directions (map str/trim (str/split (utils/input-str "day1.txt") #", "))]
    (println "Part1: " (-part1 directions))
    (println "Part2: " (-part2 directions))))



