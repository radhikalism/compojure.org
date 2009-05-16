;; Copyright (c) James Reeves. All rights reserved.
;; The use and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which
;; can be found in the file epl-v10.html at the root of this distribution. By
;; using this software in any fashion, you are agreeing to be bound by the
;; terms of this license. You must not remove this notice, or any other, from
;; this software.

(ns org.compojure.view
  "Defines the page design."
  (:use compojure.html)
  (:use org.compojure.page))

(defn styles
  "Return HTML to include CSS stylesheets."
  [& styles]
  (for [style styles]
    (include-css (str "/css/" style ".css"))))

(defn scripts
  "Return HTML to include javascript files."
  [& scripts]
  (for [script scripts]
    (include-js (str "/js/" script ".js"))))

(defn show-page [page]
  (html
    [:html
      [:head
        (styles  'reset 'screen 'shCore 'syntax)
        (scripts 'shCore 'shClojure)
        (javascript-tag "SyntaxHighlighter.all({light: true});")
        [:title "compojure/" page]]
      [:body
        [:div#header  [:h1 [:span "Compojure"]]]
        [:ul#tabs     (render-tabs page)]
        [:div#content (render-page page)]]]))
