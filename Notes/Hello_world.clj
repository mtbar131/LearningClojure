(println "Hello, World!") ;This is hello world in clojure
;;This is how you write a single line comment
(comment 
  This is the best way to write a comment in clojure.
  Basically you are calling a comment function
)

;;Define a function: use 'defn' keyword
(defn my-addition [x y]
  (+ x y))
;Here [x y] are the parameters to the function my-addition
;defn keyword is the combination of 'def' and 'fn' keywords
;def will assign (define) something and fn will create a new function
;so in defn 'fn' will create a new function and 'def' will assign it to
;the function name given by us

;;'Let': allows to create variables

(let [var1 "amit"
      var2 "abhijit"
      var3 "barve"]
  (println var1 var2 var3))

(defn my-function []
  (let [var1 10]
  (println var1)))
; In clojure we have _ character as placeholder
; we can use this to hold the values where we don't want to use a variable

