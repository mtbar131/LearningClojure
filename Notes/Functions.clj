;;All about functions in clojure
;;================================

;In clojure functions are created using defn macro
;defn macro has basic structure as follows
(comment

  (defn function-name doc-string? attr-map? [parameter-list]
    conditions-map?
    (expressions))

)

;A simple function can be written as follows
(defn total-cost [number-of-items per-item-cost]
  (* number-of-items per-item-cost))

;total-cost is the function name 
;number-of-items & per-item-cost are parameters to function
;remaining part is the function body

;As mentioned above in the basic structure we can add documentation string
;to function definition as follows

(defn total-cost 
  "returns the total cost of things purchased"
  [number-of-items per-item-cost]
  (* number-of-items per-item-cost))
;This above written documentation string can be seen using (doc total-cost)

;As mentioned in the above basic structure we can also give conditions map
;in the function definition. This map is nothing but the conditions to be
;checked before and after running the function
;Example
(defn total-cost
  "returns the total cost of things purchased"
  [number-of-items per-item-cost]
  {:pre [(> number-of-items 0) (> per-item-cost 0)]
   :post [(> % 1)]}
  (* number-of-items per-item-cost))
;Here we have defined the pre and post conditions using pre and post keywords
;These conditions are nothing but Assertions. If assert fails then instead of
;running the function Assertion failure will be thrown.
;In pre conditions we check that both values are greater than zero
;In post condition we check that return value is greater than zero
;NOTE: return value can be accessed using '%' character

;A better way of writing above function with pre and post checks is as follows

(defn basic-item-total [price quantity]
  (* price quantity))

(defn with-line-item-conditions [function price quantity]
  {:pre [(> price 0) (> quantity 0)]
   :post [(> % 1)]}
  (function price quantity))

;;Call above function as (with-line-item-conditions basic-item-total 17 7)

;look at the beauty of language. How easily you can pass a function as 
;a parameter to other function and how easily that parameter is again
;used to evaluate the function

;;Function overloading
;;=====================
;In clojure functions can be overloaded based on the number of arguments
;Example:

(defn total-cost-overloaded
  ([item-count per-item-cost]
   (* item-count per-item-cost))
  ([item-cost]
   (* item-cost 1))
)

;Above we have overloaded the total-cost function which can now accept either
;one or two parameters. If two params are passed then it will calculate the
;total by normal multiplication otherwise it will simply multiply with 1

;The general format for function overloading is as follows

(defn function-name
  ([parameter1 parameter-2]
   ;; body
   )
  ([paramter-list-2]
   ;; body
   )
  ;;more cases
  )

;we can have function with variable number of arguments as follows
(defn varArgFunction [& numbers]
  (apply + numbers))
;Passing named parameters to function with varargs as follows
(defn varArgFunction [parm1, parm2, & othernumbers]
  (println "First Parameter: " parm1)
  (println "Second Parameter: " parm2)
  (println "Sum of others: "
           (apply + othernumbers)))

;;Recursive Functions
;;===========================
;A simple recursive function that will keep counting down values until
;it reaches 0 and print all values that are divisible by 100
;can be written as follows
(defn simpleRecursiveCounter [n]
  (if-not (zero? n)
    (do
      (if (= (rem n 100) 0)
        (println n))
      (simpleRecursiveCounter (dec n)))))
;However this function will give 'Stackoverflow' error for any input biggr 
;than a certain limit
;Clojure proivdes a clever way to solve this problem. 
;Clojure provides a 'recur' construct which can be used to for tail recursion
;recur only allows tail recursion!! But thats the beauty of the function
;for input of any size it won't overflow the stack
;Below is example
(defn newSimpleRecursiveCounter [n]
  (if-not (zero? n)
    (do
      (if (= (rem n 100) 0)
        (println n))
      (recur (dec n)))))
;Notice how we have called the same function again using recur keyword
;we are not giving explicite call by using function name.
;recur can only be the last statement of the function otherwise clojure will
;not complie the code


;;Some Interesting higher order functions
;;=========================================

;every?
;--------
;This is a function which will accept one function(a predicate to be more 
;specific) and a list as an argument. Then it will run that predicate on all
;members of the list and if predicate returns true for all values then every
;will return true else will return false
;Example: Check if all elements in list are even
(def myNumberList [10, 201, 17, 18, 190])
(every? even? myNumberList) ;this will return false

;some
;-----
;This is same as every except that it will return true even if only one element
;of the list returns true for given predicate. We can compare every? and some?
;with AND OR logical operators
(def studentNamesList ["abc", "xyz", "abcd", "test"])
(defn equalsMyName [x]
  (= x "abc"))
(some equalsMyName studentNamesList)
;Notice that some doesn't have ? like every?

;filter
;-------
;This function accepts a predicate and a list as arguments. Then applies 
;this predicate on the list and returns a new list of the elements for which 
;predicate is true.
;Example
(defn greaterThan100 [x]
  (> x 100))
(def randomSeq [1, 109, 781, 99, 108412, 10, 77])
(filter greaterThan100 randomSeq) ;Will return all numbers > 100

;memoize
;-------
;The famous memoization technique! Yes, clojure has a built in function to
;do this. This function will cachec the results of previous function calls
;and whenever a function is called with same arguments it will return cached
;results
;Example
(defn slow-function [n m]
  (Thread/sleep 1000)
  (* n m))
;Above function will accept two arguments, then it will sleep for 1 sec
;and print the product of two argument. We have deliberately made this 
;function slow by making it sleep.
;Lets right a function with memoize
(def fast-function (memoize slow-function))
;Lets try running both functions and compare the timings
;; (time (slow-function 10 20))
;; (time (fast-function 10 20))
;; (time (fast-function 10 20))
;From the output of above three calls we can see that in 3rd call fast-function
;returns the memoized results. The result that was cached during 2nd call

;Partial
;--------
;Partial is very powerful keyword. It works just like currying works in haskell
;Lets say there is a function f which accepts 10 arguments. If you pass f to 
;partial with only 3 arguments then partial will return a new function which
;will accept remaining 7 arguments and then do the taks of f.
;Example
(defn myFunction [a b c d e f]
  (println "Ans is: " (+ (* a b c) (* d e f))))

(def partialFunction (partial myFunction 10 20 30))
(partialFunction 1 2 3)

;In above example the call (partial myFunction 10 20 30) returns a new function
;which now accepts remaining 3 arguments and finishes the task of myFunction
;with all 6 arguments

;;Anonymous function
;;===================
;We have seen anonymous functions before. In the explanation of keyword 'defn'
;we have seen that fn creates a function; this is nothing but an anonymous 
;function which is later bound to given name with def
;Example
(filter (fn [x] (> x 10)) [1, 2, 56 11, 81, 76123])
;Here we have used an anonymous function as predicate
;However there is even shorter way for writing anonymous function;
;Same above function can be written as 
(filter #(> % 10) [1, 2, 56, 11, 81, 76123])
;If there are more arguments to anonymous functions then they can be used;
;by using %1, %2 and so on..



;;Scope
;;=========
;Clojure offers two types of scopes: Lexical (Compile time) and Dynamic
;Lexical scope is same as the scope provided by languages like JAVA
;Dynamic scoping is bit different

;Lexical scope
;Variables can be define in two ways in clojure
(def VARIABLE1 10) 
(println VARIABLE1)
;Now VARIABLE1 will be available to all parts of the program
;We can also declare the variable and assign value later(during runtime)
;like below. For that we need to declare variable as dynamic
(declare ^:dynamic *DB_SERVER*)

(binding [*DB_SERVER* "localhost"]
  (println "DB server @:" *DB_SERVER*)
  (binding [*DB_SERVER* "128.1.1.9"]
    (println "DB server @:" *DB_SERVER*))
  (println "DB server @:" *DB_SERVER*))

;If we run above code we can see that output looks like4
;; DB server @: localhost
;; DB server @: 128.1.1.9
;; DB server @: localhost
;look carefully at the values of DB_SERVER. As soon as the block of 2nd 
;binding gets over the DB_SERVER returns to the value of previous block.
;we can nest any number of such binding blocks
;Generally dynamic variables are names in between '*' to specify its dynamic

;Dynamic binding needs to be used very carefully when it comes to lazy sequences
(def ^:dynamic *FACTOR* 10)
(defn multiply [a]
  (* a *FACTOR*))
;Above we have defined a multiplication factors as dynamic and set its value to
;10 and written a function which will multiply a given number with that factor.
(binding [*FACTOR* 20]
  (map multiply [1 2 3 4 5]))
;In above code the initial value of *FACTOR* was 10 we then changed it to 20
;using a binding and in the same binding we mapped our multiply function to a
;list. At that point value of factor is 20 so answer should be 
;[20, 40, 60 80, 100] but answer it gives is [10, 20, 30, 40, 50]. This is due
;to the fact that its a lazy sequence. Clojure tries to calculate its value
;only when needed (in our case while printing it). At that time the value of
;*FACTOR* is again back to 10 and hence we get this answer. To get the correct
;answer we need to force the realization of lazy sequence using "doall" 
;construct from withing binding block as follows
(binding [*FACTOR* 30]
  (doall (map multiply [1 2 3 4 5])))
;this will produce correct answer as [30, 60, 90, 120, 150]
