;; Copyright (c) James Reeves. All rights reserved.
;; The use and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which
;; can be found in the file epl-v10.html at the root of this distribution. By
;; using this software in any fashion, you are agreeing to be bound by the
;; terms of this license. You must not remove this notice, or any other, from
;; this software.

(ns org.compojure.main
  "Routes for compojure.org."
  (:use compojure.http)
  (:use compojure.server.jetty)
  (:use org.compojure.view)
  (:use org.compojure.page))

(defroutes root
  (GET "/"
    (show-page "about"))
  (GET "/*"
    (if (page-exists? (params :*))
      (show-page (params :*))
      :next))
  (ANY "/*"
    (or (serve-file (params :*))
        :next))
  (ANY "*"
    (page-not-found)))

(defn run []
  (run-server {:port 8080}
    "/*" (servlet root)))
