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

The `with-session` middleware adds a `:session` key to the request map. This
contains a map of the session data.

Inside a [route macro](/docs/route-macros) this is bound to the local `session`
variable. This makes it pretty straightforward to access session data:

<pre class="brush:clojure">
(GET "/welcome"
  (str "Hello " (session :name)))
</pre>

## Writing sessions

To set the entire session to a new value, add session map to the `:session` key
on the response. For example:

<pre class="brush:clojure">
(GET "/set-name"
  {:session {:name (params :name)}
   :body    "Your name has been set"})
</pre>

The `set-session` function can also be used to do the same thing.

Most of the time it's likely that an application will only want to update part
of a session. The `session-assoc` function can be used in this case to update a
key in the session without affecting other session data.

<pre class="brush:clojure">
(GET "/set-name"
  [(session-assoc :name (params :name))
   "Your name has been set"])
</pre>

## Deleting sessions

To completely remove a session, the session can be set to `nil` using the
`clear-session` function.

<pre class="brush:clojure">
(GET "/logout"
  [(clear-session)
   (redirect-to "/login")])
</pre>

## Flash data

TODO

## Adding custom storage types

TODO
