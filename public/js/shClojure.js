SyntaxHighlighter.brushes.Clojure = function() {
    var defs   = 'defroutes'
    var macros = 'GET POST PUT DELETE HEAD ANY';
    var funcs  = 'html page-not-found';

    this.regexList = [
        { regex: /;(.*)$/gm,
          css: 'comment' },
        { regex: /[\(\)]/gm,
          css: 'list' },
        { regex: /[\[\]]/gm,
          css: 'vector' },
        { regex: /[\{\}]/gm,
          css: 'map' },
        { regex: /:{1,2}[a-zA-Z0-9?!\-_+*\./=<>#]*/gm,
          css: 'keyword' },
        { regex: SyntaxHighlighter.regexLib.multiLineDoubleQuotedString,
          css: 'string' },
        { regex: new RegExp(this.getKeywords(defs), 'gmi'),
          css: 'defs' },
        { regex: new RegExp(this.getKeywords(funcs), 'gmi'),
          css: 'funcs' },
        { regex: new RegExp(this.getKeywords(macros), 'gmi'),
          css: 'macros' }
    ];
};
SyntaxHighlighter.brushes.Clojure.prototype = new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.Clojure.aliases   = ['clojure', 'clj']
