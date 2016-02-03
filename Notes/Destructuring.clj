;;Destructuring
;;================
;It is nothing but a clojures way of pattern matching
;Example If we have a hashmap for a person which contains all data about him
;but we want to print only his name and age then we do it as follows
(defn printRequiredData [person]
  (let [first (:first-name person)
        last (:last-name person)
        age (:age person)]
    (println first last "is" age "years old")))
;above function will accept a person object and print the data. For taking the 
;data from object we use let form and assign data to different variables such
;as first, last, age. However if we use destructuring then the same code can
;be written without much clutter as follows
(defn printRequiredData2 [{first :first
                           last :last
                           age :age}]
  (println first last "is" age "years old"))
;The above function uses destructuring. The incoming data is destructured and
;only the rquired parts are bound to the names given to them

;We can obviously have lists or vectors as an arguments to the function
;To use destructuring in such cases we use following way
;Lets say we are having a vector with only 3 elements as an argument
(defn destructuringVectorExample [[ele1 ele2 ele3]]
  (println "E1:" ele1 "E2:" ele2 "E3:" ele3))
;For lists having many number of elements 
(defn oneMoreExample [[ele1 ele2 & remaining]] 
  (println "E1:" ele1 "E2:" ele2 "Remaining:" remaining))
;We can use 'as' keyword to give some name to our vector/list
(defn asKeywordExample [[ele1 ele2 & remaining :as completeList]] 
  (println "E1:" ele1 "E2:" ele2)
  (println "Complete element list is:" completeList))
;If input to our function is vector of vectors or list of lists
;such as [["A" "1"] ["B" "2"] ["C" "3"]]
;then we can use destructuring as follows
(defn nestedVectors [[[firstElement secondElement] & _]]
  (println "First:" firstElement "Second:" secondElement))
;Note: how we can simply ignore remaining elements with a '_'
;Also We are using 3 pairs of [ paranthesis here
