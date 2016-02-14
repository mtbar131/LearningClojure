;;Basic clojure operations such as loop, some string operations,
;;mostly used functions etc.

;str - concats a string
(str "Hello" "World" "From" "Clojure")

;if else - General for is as follows
;; (if (condition)
;;   (things to be done if true)
;;   (Things to be done for else)
;Examples
(if true
  (print "Condition was true")
  (print "Condition was false"))
(if false
  (print "Condition was true")
  (print "Condition was false"))
;Note no else keyword required

(def x 1000)
(if (> x 100)
  (do
    (print "X is > : " x)
    (+ 1000 x))
  (do 
    (print "X is < : " x)
    (* 100 x)))

;When keyword - its a combination of if and do
;There is no else branch for when.
(when (= "somecondition" "somecondition")
  (println "Hello Amit")
  "Good try")

;nil is a keyword in clojure to refer to something that
;has no value
;nil? function can b used to test if soemthing is nil
(if (nil? x)
(do
  (println "X is nil! Can't proceed further"))
(do
(println "X is" x "Starting basic configuration process")))

;cond statement in clojure - like a switch case in java
(def x 101)
(cond 
  (= x 0) (println "X is zero")
  (< x 0) (println "X is negative")
  (> x 0) (println "X is positive"))


;In clojure nil and false represent false value evertything else
;is considered as true
(if "Some string which is considered as true"
  (do
    (println "In TRUE branch")))


;Boolean and or operators in clojure
(if (or (> 1 10) (< 1 10))
  (do
    (println "Above condition will alwasy be true"))
  (do
    (println "change 'or' to 'and' and condition will always be false")))

;Using def to declare variables
(def vectorOfNames
  ["Name1" "Name2" "Name3"])

(println vectorOfNames)
;In clojure you can assign the same variable to another value
;However it is strongly recommended to not do that!

;Data Structures
;Map in clojure
;Below is an example of how to define a map in clojure

(def myMap {:language "Clojure"
 :type "Functional Programming"
 :Difficulty 10
 :Userfulness 10})
(println (get myMap :language))
;We can access the map value by using get function and key.
;If key is not found then get will return nil
(println (get myMap :SomeOtherKey))
;Maps can be used to store anything. 
;Example of how to store vectors in map
(def aNestedMap
  {:countries ["India" "XYZ" "ABC"]
   :population ["1.2B" "0.9B" "0.5M"]})
(println aNestedMap)
(println (get aNestedMap :countries))
;Example: Storing a map inside map / Nested maps
;To get the values from nested maps we use get-in function
(def oneMoreNestedMap
  {:1 {:name "ABC"
       :age "20"
       :DOB "29/2/2016"}
   :2 {:name "XYZ"
       :age "19"
       :DOB "1/1/2019"}})
(println (get-in oneMoreNestedMap [:1 :name]))

;:keywords in clojure
;In previous examples we used get function to find a particular
;value from the map. However there is an easier way using keywords
;In previous examples we used :1, :name, :age, :countries as 
;keywores and we can use those as function to get a value from map
(println (:countries aNestedMap))
(println (:2 oneMoreNestedMap))

;Vectors
;Vectors can be used to store elements of any type. These 
;elements can be accessed using 0-based indexing
(def aSimpleVector [3 "random" "String" 1 {:type "Map"}])
(println (get aSimpleVector 0))
(println (get aSimpleVector 3))
(println (get aSimpleVector 4))

;vectors can be created using vector function as follow
(vector "vector" "of few" "string" "&" "a map" {:key "value"})

;conj is a function which can be used to add elements to the end
;of the vector. However remember that is creates a new vector
;and returns it. See output of below example to understand 
;the difference
(def aNewVector (conj aSimpleVector 10))
(println "Original vector:" aSimpleVector)
(println "Newly created vector:" aNewVector)

;Lists in clojure
;Lists are similar to vectors. They can be used to store any type
;of data. To create list simply start it with a single quote
;Example
(def aList '(1 2 "sdfj" "string" {"tuple" "here"}))
(println aList)
;to get nth element of list use 'nth' function
(println (nth aList 3))
;similar to maps 'conj' function can be used to add element
;at the beginning of the list
(def newList (conj aList 1000))
(println newList)


;Set in clojure
;Set is nothing but a collection of unique values (of any datatype)
;There are two types of set in clojure 'Hash-set' and 'sorted-set'
;To create a hash-set use #{} or hash-set function
(def aHashSet #{"Name" 20 18121.15 {"t" 1} {:key "value"
                              :key2 "value2"}})
(def oneMoreHashSet (hash-set 1 2 "data" {1 2} {1 2}))
(println aHashSet)
(println oneMoreHashSet)
;In above example notice how a tuple that was added twice is stored 
;only once
;Conj function can be again used to add a new value to a set
(def newSet (conj aHashSet "somemoreData"))
(println newSet)

;contains? can be used to check if the element is present in the 
;set or not
(println (contains? newSet {"t" 1}))
;We can also use the get function to get the value from the set
;if it exists.
(println (get newSet "somemoreData"))
;Above will return "somemoreData"


;Loops in clojure - known as 'functional iterations'
;while loop - general structure is as follows
;; (while (condition) (body))
;Examle
;For while condition to be false some side effect is necessary
;Will add example later


;For loop is generally achieved using recursion
;Basic structure for loop construct in clojure
;; (loop (bindings) (body)
;Example: Calculating factorial

(defn myFactorial [n]
  (loop [current n 
         fact 1]
    (if (= current 1)
      fact
      (recur (dec current) (* fact current)))))
;First after loop keyword we assign current the value of n
;and fact the value of 1. Then comes the body of the loop
(println (myFactorial 10))


;Above constructs are not very easy to use. Clojure provides macros for
;looping 
;DOSEQ
;If we have a vectors and we need to do some function for each element of the
;vector we can use DOSEQ
;Basic form is as follows
;; (doseq [elem vector])
;elem is the variable which represents some value from the vector at a 
;particular iteration
(doseq [x [1 2 3 4 5]]
  (println "Iteration value: " x))

;DOTIMES
;; (dotimes [x n])
;do times will accept a symbol x and run the loop for all values
;of that symbol upto n-1. To roughly convert in traditional java
;for loop. x is 'i' n is the i <= LIMIT condition.
(defn count-numbers-till [n]
  (dotimes [x n]
    (println x)))

(defn myTest [lst]
  (loop [len (count lst)
         i 2
         output [(get lst 1)]]
    (if (odd? i)
      (do
      (conj output (get lst i))
      (dec i)))
      output))
  

  
