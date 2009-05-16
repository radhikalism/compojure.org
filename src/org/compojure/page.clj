;; Copyright (c) James Reeves. All rights reserved.
;; The use and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which
;; can be found in the file epl-v10.html at the root of this distribution. By
;; using this software in any fashion, you are agreeing to be bound by the
;; terms of this license. You must not remove this notice, or any other, from
;; this software.

(ns org.compojure.page
  "Library for displaying markdown pages."
  (:use compojure.html.page-helpers)
  (:use compojure.str-utils)
  (:use clojure.contrib.java-utils)
  (:use clojure.contrib.str-utils)
  (:import com.petebevin.markdown.MarkdownProcessor))

(def markdown-processor (MarkdownProcessor.))

(def pages-dir "pages")

(defn markdown
  "Render markdown-formatted text."
  [text]
  (.markdown markdown-processor text))

(defn- list-top-level
  "List top level pages."
  []
  (filter
    #(or (.endsWith % ".md")
         (.isDirectory (file %)))
     (.list (file pages-dir))))

(defn get-tabs
  "Return a list of page tabs."
  [current-page]
  (for [page (list-top-level)]
    (let [page (.replace page ".md" "")
          uri  (str "/" page)
          tab  (link-to uri (capitalize page))]
      (if (= page current-page)
        [:li.current tab]
        [:li tab]))))
