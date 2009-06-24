#! /usr/bin/env clj

(require '(clojure inspector)
         'compojure)

(defn as-id [s]
  (let [s (.replaceAll (str s) "\\W" "_")]
    (if (= \_ (first s))
      (apply str (rest s))
      s)))

(defn html-doc [v]
  (println (str "<hr id=\"" (as-id (:name ^v)) "\" />\n"))
  (if (:arglists ^v)
    (doseq [args (:arglists ^v)]
      (print (str "<h3>(" (:name ^v) " "))
      (apply print (map compojure.html.gen/escape-html args))
      (println ")</h3>"))
    (println (str "<h3>" (:name ^v) "</h3>")))
  (when (:macro ^v)
    (println "<h4>Macro</h4>"))
  (println (str "<p>"
                (compojure.html.gen/escape-html
                 (.replaceAll (.matcher #"(?<!\n)\n(?!\n)" (:doc ^v)) ""))
                "</p>")))

(def compojure-nss
  (sort-by #(. % name)
           (filter #(. (str %) (contains "compojure."))
                   (all-ns))))

(defn compojure-vs [ns]
  (for [v (sort-by (comp :name meta) (vals (ns-interns ns)))
        :when (and (:doc ^v)
                   (not (:private ^v)))]
    v))

(defn toc-entry [vs]
  (apply str (map #(str "<li class=\"toc-entry\"><a href=\"#" (as-id (:name ^%))
                        "\">" (:name ^%)
                        "</a></li>") vs)))

(defn html-docs []
 (println "<h1>API</h1>\n"
          "<p>This is documentation for all of Compojure's functions and"
          "macros, arranged alphabetically within namespace.</p>"
          "<div id=\"toc\"><h2>Table Of Content</h2><ol>")
 (doseq [ns compojure-nss]
   (if-let [vs (compojure-vs ns)]
     (println "<li><h3>" (. ns name) "</h3>"
              "<ol>" (toc-entry vs) "</ol></li>")))
 (println "</ol></div><div id=\"api\">")
 (doseq [ns compojure-nss]
   (if-let [vs (compojure-vs ns)]
     (do
       (println (str "<h2>" (. ns name) "</h2>")
                "<div class=\"toc\"><ol>"
                (toc-entry vs)
                "</ol></div>")
       (doseq [v vs] (html-doc v)))))
 (println "</div>"))

(html-docs)
