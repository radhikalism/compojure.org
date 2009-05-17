# Web Development in Clojure

Compojure is an open source web framework for the
[Clojure programming language](http://clojure.org). It is compatible with the
[Ring](http://github.com/mmcgrana/ring/tree) specification.

Like Clojure, it favours a functional approach, with little to no side-effects.
Compojure is also designed to be concise, simple and explicit.

Here's a basic "hello&nbsp;world" example:

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
