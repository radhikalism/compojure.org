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
  (:use clojure.contrib.duck-streams)
  (:use clojure.contrib.java-utils)
  (:use clojure.contrib.str-utils)
  (:import com.petebevin.markdown.MarkdownProcessor))

(def markdown-processor (MarkdownProcessor.))

(def pages-dir "pages")

(defn markdown
  "Render markdown-formatted text."
  [text]
  (.markdown markdown-processor text))

(defn top-path
  "Get the top-level path of the URI."
  [uri]
  (second (re-find #"^/?(\w+)" uri)))

(defn page-path
  "Return the internal path to a page file, nil if it doesn't exists."
  [page ext]
  (let [f (file pages-dir (str page "." ext))]
    (when (.exists f) f)))

(defn page-exists?
  "Return true if the page exists, false otherwise."
  [page]
  (or (page-path page "md")
      (page-path page "html")))

(defn render-page
  "Render a page. Look for Markdown page first, else check for HTML."
  [page]
  (if-let [page-md (page-path page "md")]
    (markdown (slurp* page-md))
    (slurp* (page-path page "html"))))
