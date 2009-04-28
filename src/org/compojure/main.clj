(ns org.compojure.main
  (:use compojure))

(def header
  [:div#header
    [:h1 [:span "Compojure"]]])

(def tabs
  [:ul#tabs
    [:li.current "Home"]
    [:li "Documentation"]
    [:li "Download"]])

(defn site-page [title & body]
  (html
    [:html
      [:head
        (include-css "css/reset.css")
        (include-css "css/screen.css")
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
    (or (serve-file (params :*))
        :next))
  (ANY "*"
    (page-not-found)))
