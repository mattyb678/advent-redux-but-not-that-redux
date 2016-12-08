(ns aoc.utils
  (:require [clojure.java.io :as io]))

(defn input-str [file-name]
  (slurp (io/file (io/resource file-name))))
