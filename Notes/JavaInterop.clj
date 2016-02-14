;;How to interact with java from clojure
;To import java classes in our code we can use import statement in clojure
;example

;; (import '(org.apache.hadoop.hbase.client HTable Scan))

;above line will import hadoop client package and the HTable & 
;Scan classes from that package
;However the recommended way of importing java classes is using
;namespace with :import option. Example below

;; (ns org.learn.clojure.amit
;;   (:import (java.util Scanner)))

;Above line will import scanner class from java util pacakge

;Creating instances of the class
;------------------------------------------
(import '(java.text SimpleDateFormat))
(def simpleDate (new SimpleDateFormat "yyyy-MM-dd"))
;in the first line we have imported the simpleDateFormat. In the 
;second line we create a new variable named 'simpleDate' and call
;the SimpleDateFormat constructor using new keyword just like java.
;also we pass "yyyy-MM-dd" as argument to this constructor.
;In clojure we can call a constructor in one more way
(def simpleDate2 (SimpleDateFormat. "yyyy-MM-dd"))
;Notice the 'dot' after SimpleDateFormat in above line. This tell
;clojure compiler that this is the call to constructor and 
;remaining symbols are parameters to that constrcutor

;Accessing the members of class
;-------------------------------------------
;To access a member of the class we can use 'dot' operator.
;For example to access the parse method of object simpleDate2 
;(SimpleDateFormat class) we write 'dot' followed by method name
;General format is (.member object arguments);
;Example
(.parse simpleDate2 "2016-02-14")
;To call static method we can directly use class name 
;General format is (ClassName/methodname arguments)
;Example
(+ 1 (Integer/parseInt "20160214"))
;similarly static fields can be accessed
(println "Integer min value" (Integer/MIN_VALUE))

;There is one more way to access class members
;which is especially preferred while writing macros. All the above
;written expressions eventually get converted to this form

;; (. class/ObjectName membername arguments)

;Examples
(. simpleDate parse "2016-02-16") ;using object
(println (. System getProperty "java.io.tmpdir")) ;using class


;;(dot dot) operator 
;============================
;When we want to apply sequence of methods chained together so that
;output of first will be the input of next we can use dot dot operator
;ExampleL suppose we want to get instance of a calendar object and
;print its name. Normally we can do it like this
(import '(java.util Calendar TimeZone))
(. (. (. Calendar getInstance) getTimeZone) getDisplayName)
;Above line will get the instance of calendar, then get a particular
;timezone from it and then get the name of that timezone. This
;line of code however looks very messy with all those dots and 
;brackets. By using dot dot operator we can simply write
(.. Calendar getInstance getTimeZone getDisplayName)
;Look how we can simply call the methdos with dot dot operator and 
;it will automatically take care of input output redirection
;Note: If methods take argument then we have to put them inside
;paranthesis and add arguments as follows
(.. Calendar getInstance (getTimeZone) (getDisplayName))
