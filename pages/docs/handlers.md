# Handlers

Handlers are functions that respond to HTTP requests. They take an
[request map](/docs/requests) as an argument, and return a
[response map](/docs/responses).

<img title="Handler diagram" alt=""
     class="diagram"
     src="/img/handler-diagram.png"/>

Handlers are the basic building block of Compojure. They provide a way to
define web applications in a purely functional way. Here is a basic handler
that outputs "hello world" in response to any request:

<pre class="brush:clojure">
(defn hello-world [request]
  {:status  200
   :headers {}
   :body    "Hello World"})
</pre>

However, in practise, you'd rarely write a handler like this. Compojure
provides a powerful [routes syntax](/docs/routes) that allows complex handlers
to be built in a clear and concise fashion.

The idiomatic way of writing the above handler in Compojure is:

<pre class="brush:clojure">
(defroutes hello-world
  (ANY "*" "Hello World"))
</pre>
