# Routes

Routes are a type of [handler](/docs/handlers) that return `nil` if the
[request](/docs/requests) does not match certain criteria. Typically, routes
are chained in sequence, so that if a route in the sequence returns nil, the
request is passed to the next route.

<img title="Routes diagram" alt=""
     class="diagram"
     src="/img/route-diagram.png"/>

Here is an example of a simple route funcion. The function will only return a
[response](/docs/responses) if the request method is GET, and the URI is "/":

<pre class="brush:clojure">
(fn [request]
  (if (and (= (:request-method request) :get)
           (= (:uri request) "/"))
    {:status  200
     :headers {}
     :body    "Hello World"}))
</pre>

## Route syntax

However, the above example is a very verbose way of defining a route. Compojure
provides a very powerful syntax that enables routes to be defined clearly and
concisely. A more idiomatic way of writing the above function is:

<pre class="brush:clojure">
(GET "/" "Hello World")
</pre>

The `GET` macro is part of a family of macros for defining routes. There is one
macro for each of the common HTTP methods (`GET`, `POST`, `PUT`, `DELETE` and
`HEAD`). Additionally, there is `ANY`, which matches any HTTP method.

The first argument of these macros is the path template. This matches against
the HTTP request URI. The path template can include parameters, which are
identifiers denoted by a beginning ":".

A parameter will match a string of any character apart from "/", ".", "," ";"
and "?". The matched value is stored in a map the `:route-params` key in the
request. This can also be accessed via the `params` binding:

<pre class="brush:clojure">
(GET "/product/:id"
  (str "Requesting product " (params :id)))
</pre>

You can include more than one parameter, and even the same parameter multiple
times. In the latter case the value in the route-params map will be a vector
with all the matching values from the URI. 

As well as parameters, you can match wildcards, denoted by a "*". A wildcard
will match a string of any character. The value matched by the wildcard is
stored under the `:*` key.

<pre class="brush:clojure">
(GET "/report/*"
  (str "Report " (params :*) " was not found"))
</pre>

Absolute URLs can also be matched:

<pre class="brush:clojure">
(GET "http://www.example.com/"
  "Only requests for www.example.com")
</pre>

This behaviour is triggered when the beginning of the path template is
a URL scheme, such as "http://" or "https://". You can use parameters
or wildcards in the domain: 

<pre class="brush:clojure">
(GET "http://:subdomin.example.com/"
  (str "Only requests for " (params :subdomain))
</pre>

You cannot use a parameter to match the URL protocol. However, the request map
does contain the `:scheme` key for circumstances where it is required to place
the URL scheme into a variable.

For more precise control over URI matching, the path template can be specified
using a regular expression. In this case the :route-params key contains a
vector corresponding to the groups matched by the expression.

<pre class="brush:clojure">
(GET #"/product/(\d+)"
  (str "You chose product: "
       ((:route-params request) 0)))
</pre>

Unlike re-groups, the first element of the parameter vector is not the
entire match, but the first nested group.
