(ns org.compojure.main
  (:use compojure))

(def header
  [:div#top
    [:h1 [:span "Compojure"]]])

(def tabs
  [:ul#tabs
    [:li "Home"]])

(defn site-page [title & body]
  (html
    [:html
      [:head
        [:title title]]
      [:body
        header
        tabs
        body]]))

(defn index []
  (site-page "Compojure"
    [:div#content
      "Lorem Ipsum"]))

(defroutes root
  (GET "/"
    (index))
  (ANY "*"
    (page-not-found)))
