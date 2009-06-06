# Routes

Routes are a type of [handler](/docs/handlers) that return `nil` if the
[request](/docs/requests) does not match certain criteria.

For example, the route function below will only return a
[response](/docs/responses) if URI is "/":

<pre class="brush:clojure">
(defn hello-route [request]
  (if (= (:uri request) "/")
    {:status  200
     :headers {}
     :body    "Hello World"}))
</pre>

This example demonstrates a basic, but verbose way of defining a route. A more
concise method is to use [route macros](/docs/route-macros).

Routes are useful because they can be chained in sequence. If a route in the
sequence returns nil, the request is passed to the next route. This continues
until a valid response is returned, or the list of routes is exhaused.

<img title="Routes diagram" alt=""
     class="diagram"
     src="/img/route-diagram.png"/>

Routes can be chained in this fashion using the `routes` function. Compojure
also supplies a `defroutes` macro to simplify the process of defining route
chains.

In the example below, three route functions are combined to make a single
handler function:

<pre class="brush:clojure">
(defroutes my-handler
  hello-route
  some-other-route
  404-route)
</pre>
