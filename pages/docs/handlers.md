# Handlers

Handlers are functions that respond to HTTP requests. The request is encoded as
a Clojure map and passed to the handler function as an argument. The handler
returns a map representing a HTTP response.

<img title="Handler diagram" alt=""
     class="diagram"
     src="/img/handler-diagram.png"/>

Handlers are the basic building block of Compojure. They provide a way to
define web applications in a purely functional way.

<pre class="brush:clojure">
(defn hello-world [request]
  {:status  200
   :headers {}
   :body    "Hello World"})
</pre>
