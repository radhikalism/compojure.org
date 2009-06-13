# HTML

The `html` function generates a string of HTML from a tree of vectors. This
allows HTML to be defined without the need for external template files.

<pre class="brush:clojure">
(html [:p "Hello World"]) =&gt; "&lt;p&gt;Hello World&lt;/p&gt;"
</pre>

The first item of the vector defines the tag name. This can be a string, symbol
or keyword.

The second item can optionally be a map. This defines the set of attributes on
the tag.

<pre class="brush:clojure">
(html [:img {:src "logo.png"}]) =&gt; "&lt;img src=\"logo.png\"/&gt;"
</pre>

Any further items in the vector are considered to be part of the tag's content.
This can also include nested vectors.

<pre class="brush:clojure">
(html [:p "Hi " [:em "World"]]) =&gt; "&lt;p&gt;Hi &lt;em&gt;World&lt;/em&gt;&lt;/p&gt;"
</pre>

Any sequence in the vector is automatically expanded.

<pre class="brush:clojure">
(html [:p (list "AB" "CD" "EF")]) =&gt; "&lt;p&gt;ABCDEF&lt;/p&gt;"
</pre>

This can be useful for producting HTML from sequences of data. For example:

<pre class="brush:clojure">
(defn unordered-list [items]
  [:ul
    (for [item items]
      [:li items])])
</pre>

For defining "id" and "class" attributes, the `html` function has some syntax
sugar based on CSS selectors.

<pre class="brush:clojure">
(html [:p.greet "Hello"]) =&gt; "&lt;p class=\"greet\"&gt;Hello&lt;/p&gt;"

(html [:h1#title "About"]) =&gt; "&lt;h1 id=\"title\"&gt;About&lt;/h1&gt;"
</pre>
