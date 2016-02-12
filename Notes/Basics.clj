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
;of the vector
(conj [1 2 3] 10)
(println (get aSimpleVector 5))
