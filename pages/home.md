# Web Development in Clojure

Compojure is an open source web framework for the
[Clojure programming language](http://clojure.org).

Like Clojure, it favours a functional approach, with little to no side-effects.

Functions are connected to routes via a simple and concise syntax. Here's the
obligatory "hello&nbsp;world" example:

<pre class="brush:clojure">
(defroutes greetings
  (GET "/"
    (html [:h1 "Hello World"]))
  (ANY "*"
    (page-not-found)))
</pre>

More examples of Compojure syntax can be found in the
[documentation](/documentation), or you can grab the latest Compojure
builds from the [download](/download) section.
