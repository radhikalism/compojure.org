# Requests

HTTP requests are represented as Clojure maps, and adhere to the
[Ring](http://github.com/mmcgrana/ring) specification. This means they have a
set of standard keys that any Ring-based application can use. 

Request maps can be extended with custom keys. Compojure adds a number of
additional keys through [middleware](/docs/middleware) functions.

## Standard Ring keys

<dl class="keys">
  <dt><code>:server-port</code></dt>
  <dd>The port on which the request is being handled.</dd>

  <dt><code>:server-name</code></dt>
  <dd>The resolved server name, or the server IP address.</dd>

  <dt><code>:remote-addr</code></dt>
  <dd>The resolved server name, or the server IP address.</dd>

  <dt><code>:uri</code></dt>
  <dd>The request URI string. Always starts with "/".</dd>

  <dt><code>:query-string</code></dt>
  <dd>The query string, if present.</dd>

  <dt><code>:scheme</code></dt>
  <dd>The transport protocol. Either <code>:http</code> or
      <code>:https</code></dd>

  <dt><code>:request-method</code></dt>
  <dd>The HTTP request method. One of
        <code>:get</code>,
        <code>:head</code>,
        <code>:options</code>,
        <code>:put</code>,
        <code>:post</code> or
        <code>:delete</code></dd>

  <dt><code>:content-type</code></dt>
  <dd>The MIME type of the request body, if known.</dd>

  <dt><code>:content-length</code></dt>
  <dd>The number of bytes in the request body, if known.</dd>

  <dt><code>:character-encoding</code></dt>
  <dd>The name of the character encoding used in the request body, if
      known.</dd>

  <dt><code>:headers</code></dt>
  <dd>A Clojure map of lowercase header name strings to corresponding header
      value strings.</dd>

  <dt><code>:body</code></dt>
  <dd>An <em>InputSteam</em> for the request body, if present.</dd>
</dl>

## Standard Compojure keys

<dl class="keys">
  <dt><code>:query-params</code></dt>
  <dd>A Clojure map of keyword/string pairs that correspond to url-encoded
      parameters parsed from the query-string.</dd>

  <dt><code>:form-params</code></dt>
  <dd>A Clojure map of keyword/string pairs that correspond to url-encoded
      parameters parsed from request body.</dd>

  <dt><code>:route-params</code></dt>
  <dd>Parameters matched from parsing the path of a
      <a href="/docs/routes">route</a>.
      If the path is a formatted string, the parameters are encoded as a
      keyword/string map. If the path is a regular expression, the paramaters
      are encoded as a vector of matches.</dd>

  <dt><code>:params</code></dt>
  <dd>A merged map of <code>:query-params</code>, <code>:form-params</code> and
      <code>:route-params</code>.</dd>

  <dt><code>:cookies</code></dt>
  <dd>A Clojure map of keyword/string pairs that correspond to the HTTP cookies
      stored for this URI.</dd>
</dl>

## Optional Compojure keys

<dl class="keys">
  <dt><code>:multipart-params</code><dt>
  <dd>A Clojure map of keyword/string pairs from multipart-encoded parameters.
      Added by the <code>with-multipart</code> middleware. Also adds these
      parameters to the <code>:params</code> key.</dd>

  <dt><code>:session</code></dt>
  <dd>A Clojure map of session data. Added by the <code>with-session</code>
      middleware.</dd>

  <dt><code>:flash</code></dt>
  <dd>A Clojure map of flash data. Added by the <code>with-session</code>
      middleware.</dd>
</dl>
