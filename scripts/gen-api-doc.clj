#^:shebang '[
exec java -cp "$HOME/data/libraries/java/compojure/*" clojure.lang.Script "$0" -- "$@"
]

(require '(clojure inspector)
         'compojure)

(defn html-doc [v]
  (println "<hr />")
  (println "")
  (if (:arglists ^v)
    (doseq [args (:arglists ^v)]
      (print (str "<h3>(" (:name ^v) " "))
      (apply print args)
      (println ")</h3>"))
    (println (str "<h3>" (:name ^v) "</h3>")))
  (when (:macro ^v)
    (println "<h4>Macro</h4>"))
  (println (str "<p>"
                (.replaceAll (.matcher #"(?<!\n)\n(?!\n)" (:doc ^v)) "")
                "</p>")))

(defn html-docs []
 (println "<h1>API</h1>")
 (newline)
 (println "<p>This is documentation for all of Compojure's functions and"
          "macros, arranged alphabetically within namespace.</p>")
 (println "<div id=\"api\">")
 (doseq [ns (sort-by #(. % name)
                     (filter #(. (str %) (contains "compojure.")) (all-ns)))]
   (let [vs (for [v (sort-by (comp :name meta) (vals (ns-interns ns)))
                :when (and (:doc ^v) (not (:private ^v)))] v)]
     (when vs
       (println (str "<h2>" (. ns name) "</h2>"))
       (doseq [v vs] (html-doc v)))))
 (println "</div>"))

(html-docs)
