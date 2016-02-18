;;Some pure functions in clojure
;;====================================
;pure functions are the functions which don't change the data

;comp
;This function can be used to compose many functions into one. Suppose we have 
;3 functions f1, f2, f3 and we have some data values d1, d2. If we want to apply
;these functions to data value in following way f3(f2(f1(d1, d2))) the easy
;way in clojure is comp function. -> ((comp f3 f2 f1) d1 d2)
;In above given function first f1 will be applied to d1 and d2 and then ouput
;of f1 will be passed as argument to f2, again output of f2 will be passed to
;f3 and so on..
;For this to work, f1 can take any number of arguments but remaining functions
;should be able to take only one argument
;Example
((comp inc *) 2 3)
;=> 7
;above function will first apply * to 2&3 and then increment that value


