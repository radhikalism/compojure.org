# Responses

HTTP responses are represented as Clojure maps, and should follow thee
[Ring](http://github.com/mmcgrana/ring) specification. This means Compojure
expects response maps to contain at least the following keys:

## Standard Ring keys

<dl class="keys">
  <dt><code>:status</code></dt>
  <dd>An integer representing the HTTP status code.</dd>

  <dt><code>:headers</code></dt>
  <dd>A Clojure map of strings to represent the headers of the response.</dd>
</dl>

## Optional Ring keys

<dl class="keys">
  <dt><code>:body</code></dt>
  <dd>The body of the response. Can be either a <em>String</em>, <em>ISeq</em>,
      <em>File</em> or <em>InputStream</em> object.</dd>
</dl>

## Optional Compojure keys

<dl class="keys">
  <dt><code>:session</code></dt>
  <dd>If set, the HTTP session is set to this value. Must be a Clojure map.</dd>

  <dt><code>:flash</code></dt>
  <dd>If set, the flash stored in the HTTP session is set to this value. Must
      be a Clojure map.</dd>
</dl>
