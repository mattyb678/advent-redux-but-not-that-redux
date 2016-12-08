(ns aoc.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if (= (count args) 1)
    (let [namespace (symbol (str "aoc." (first args)))]
      (do
        (require namespace)
        (let [day (find-ns namespace)]
          ((ns-resolve day 'challenge)))))
    (println "Specify the day that you want to run, e.g.'day1'")))

