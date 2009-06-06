# Route macros

However, the above example is a very verbose way of defining a route. Compojure
provides a very powerful syntax that enables routes to be defined clearly and
concisely. A more idiomatic way of writing the above function is:

<pre class="brush:clojure">
(GET "/" "Hello World")
</pre>

The `GET` macro is part of a family of macros for defining routes. There is one
macro for each of the common HTTP methods (`GET`, `POST`, `PUT`, `DELETE` and
`HEAD`). Additionally, there is `ANY`, which matches any HTTP method.

## Path templates

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

As well as parameters, you can match wildcards, denoted by a "\*". A wildcard
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
using a regular expression. In this case the `:route-params` key contains a
vector corresponding to the groups matched by the expression.

<pre class="brush:clojure">
(GET #"/product/(\d+)"
  (str "You chose product: "
       ((:route-params request) 0)))
</pre>

Unlike re-groups, the first element of the parameter vector is not the
entire match, but the first nested group.

## Route output

In the Compojure route syntax, the return value represents a modification to a
blank "200 OK" response map:

<pre class="brush:clojure">
{:status 200, :headers {}}
</pre>

The class of the return value determines how it alters the response map. The
following classes are used:

<dl>
  <dt><code>java.lang.Integer</code></dt>
  <dd>An integer return value sets the status code of the response.</dd>

  <dt><code>java.lang.String</code></dt>
  <dd>A string return value is added to the response body.</dd>

  <dt><code>clojure.lang.ISeq</code></dt>
  <dd>A return value of a Clojure sequence sets the response body.</dd>

  <dt><code>java.io.File</code></dt>
  <dd>A return value of a File sets the response body.</dd>

  <dt><code>java.io.InputStream</code></dt>
  <dd>A return value of an InputStream sets the response body.</dd>

  <dt><code>java.net.URL</code></dt>
  <dd>A InputStream to the URL is opened and the response body set to the
  stream.</dd>

  <dt><code>clojure.lang.Keyword</code></dt>
  <dd>If the keyword is :next, the response is nil. Otherwise the keyword is
  treated as a string.</dd>

  <dt><code>java.util.Map</code></dt>
  <dd>The map is intelligently merged into the response map.</dd>

  <dt><code>clojure.lang.Fn</code></dt>
  <dd>The request and response maps are passed to the function as arguments,
  and the return value of the function is used to determine the response.</dd>

  <dt><code>clojure.lang.IPersistentVector</code></dt>
  <dd>Each element in the vector is used to update the response.</dd>
</dl>

Some examples of usage:

<pre class="brush:clojure">
(GET "/"
  "Index page")

(ANY "*"
  [404 "Page Not Found"])

(GET "/image"
  (File. "./public/image.png"))

(GET "/new-product"
  (if product-released?
    "Our product is amazing"
    :next))

(GET "/map-example"
  {:body "Hello World"})
</pre>