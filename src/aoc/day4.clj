(ns aoc.day4
  (:require [aoc.utils :as utils]
            [clojure.string :as string]))

(def name-regex #"(.*)-(\d+)\[(.*)\]")

(defn -real-room? [name checksum]
  (let [freq (frequencies name)
        chksm (->> freq                   
                   (group-by val)
                   (sort-by key >)
                   (map second)
                   (map sort)
                   (map #(map key %))
                   (map #(apply str %))
                   (string/join)
                   )]
    (= (subs chksm 0 5) checksum)))

(defn -parse-line [line]
  (let [matches (re-matches name-regex line)
        name (string/replace (nth matches 1) #"-" "")
        sector-id (read-string (nth matches 2))
        checksum (nth matches 3)
        real (-real-room? name checksum)]
    {:name name :sector-id sector-id :checksum checksum :real? real}))

(defn -part1 [lines]
  (let [rooms (map #(-parse-line %) lines)
        sectors (map #(if (% :real?)
                        (% :sector-id)
                        0) rooms)]
    (reduce + sectors)))

(defn challenge []
  (println "Day 4")
  (let [lines (string/split-lines (utils/input-str "day4.txt"))]
    (println "Part 1: " (-part1 lines))))

