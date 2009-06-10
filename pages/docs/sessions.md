# Sessions

HTTP sessions in Compojure are handled via the `with-session`
[middleware](/docs/middleware) function.

## Enabling sessions

To enable sessions, you need to decorate your [handler](/docs/handlers)
function using `with-session`:

<pre class="brush:clojure">
(decorate your-handler
  (with-session {:type :memory}))
</pre>

The argument to `with-session` is a map of describing the type of storage your
sessions will use, along with any additional options. Currently only two types
of sessions are supported:

* `:memory` - In-memory sessions stored in a Clojure ref.
* `:cookie` - Sessions serialized and stored in the cookie. These are secured
  with a [HMAC](http://en.wikipedia.org/wiki/Hmac).

You can also set the type by just using a keyword:

<pre class="brush:clojure">
(decorate your-handler
  (with-session :memory))
</pre>

Sessions defined like this will last indefinitely, so it's usually a good idea
to set an expiry time. You can do this by setting the `:expires` key. This
denotes the number of seconds to wait before a session times out and is
removed.

<pre class="brush:clojure">
(decorate your-handler
  (with-session {:type :memory, :expires 600}))
</pre>

In this case, the session timeout is set to 600 seconds, or 10 minutes.

## Reading sessions

TODO

## Writing sessions

TODO
