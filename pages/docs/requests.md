# Requests

HTTP requests are represented as Clojure maps, and adhere to the
[Ring](http://github.com/mmcgrana/ring) specification. This means they have a
set of standard keys that any Ring-based application can use. 

## Standard Ring keys

<dl class="keys">
  <dt><code>:server-port</code> - <em>Integer</em></dt>
  <dd>The port on which the request is being handled.</dd>

  <dt><code>:server-name</code> - <em>String</em></dt>
  <dd>The resolved server name, or the server IP address.</dd>

  <dt><code>:remote-addr</code> - <em>String</em></dt>
  <dd>The resolved server name, or the server IP address.</dd>

  <dt><code>:uri</code> - <em>String</em></dt>
  <dd>The request URI. Always starts with "/".</dd>

  <dt><code>:query-string</code> - <em>String</em></dt>
  <dd>The query string, if present.</dd>

  <dt><code>:scheme</code> - <em>Keyword</em></dt>
  <dd>The transport protocol. Either <code>:http</code> or
      <code>:https</code></dd>
</dl>
