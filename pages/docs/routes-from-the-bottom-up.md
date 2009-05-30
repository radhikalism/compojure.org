# Routes from the bottom up

## I. Handlers

In Compojure, HTTP requests and HTTP responses are represented by
Clojure maps. A *handler* is a function that takes a request map as an
argument, and returns a response map. 
