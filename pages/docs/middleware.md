# Middleware

Middleware are
[higher-order functions](http://en.wikipedia.org/wiki/Higher-order_function)
that provide common functionaly to [handlers](/docs/handlers). A middleware
function takes a handler function as an argument, and returns a modified
handler function.

In essense, middleware acts as a wrapper around your handlers.

<img title="Middleware diagram" alt=""
     class="diagram"
     src="/img/middleware-diagram.png"/>

Compojure uses middleware to provide common functionality without making it
mandatory. For example, many websites use HTTP sessions, but many web services
do not. In Compojure, sessions are handled with middleware, so you can choose
whether or not to include sessions in your application.

Another benefit is that middleware can be applied to only parts of an
application. For instance, you might wish to limit file uploads to logged in
users only.

To apply middleware to a handler, use the `decorate` macro:

<pre class="brush:clojure">
(decorate your-handler
  (with-multipart)
  (with-session :memory))
</pre>

This macro will wrap a pre-defined function in middleware, whilst retaining the
metadata. In the above example, middleware is used to add support for HTTP
sessions and file uploads via multipart forms.

Middleware is not only simple to use; it is also simple to write. A useful
middleware function can be written in a few lines of Clojure:

<pre class="brush:clojure">
(defn with-header [handler header value]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers header] value)))) 
</pre>

This middleware adds a custom header to the response.

Note that by convention, the first argument of Compojure middleware is always
the handler. This is required for your middleware to work with `decorate`:

<pre class="brush:clojure">
(decorate your-handler
  (with-multipart)
  (with-session :memory)
  (with-header "X-Framework" "Compojure"))
</pre>

Middleware is used extensively in Compojure, and is a valuable tool for
abstracting common functionality.
