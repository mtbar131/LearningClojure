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

