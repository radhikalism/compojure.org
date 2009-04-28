(ns org.compojure.main
  (:use compojure))

(def header
  [:div#header
    [:h1 [:span "Compojure"]]])

(def tabs
  [:ul#tabs
    [:li "Home"]
    [:li.current "Documentation"]
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

(def example-code
"(defroutes site
  (GET \"/\"
    (html [:h1 \"Hello World\"]))
  (ANY \"*\"
    (page-not-found)))")

(defn index []
  (site-page "Compojure"
    [:div#content
      [:h2 "Web Development in Clojure"]
      "Lorem Ipsum"
      [:pre.code
        example-code]]))

(defroutes root
  (GET "/"
    (index))
  (ANY "*"
    (or (serve-file (params :*))
        :next))
  (ANY "*"
    (page-not-found)))
