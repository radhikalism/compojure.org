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

However, in practise, you'd rarely write a handler like this. Handlers are
usually built up out of a number of [routes](/docs/routes) defined using the
[route macro](/docs/route-macros) syntax.

Once a handler is written, it can be turned into a Java servlet object using
the `servlet` inline function. It can then be attached to a
[server](/docs/servers):

<pre class="brush:clojure">
(run-server {:port 8080}
  "/*" (servlet hello-world))
</pre>
