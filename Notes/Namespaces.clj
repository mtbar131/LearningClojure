;;Namespaces in Clojure
;;========================
(ns org.learn.clojure.amit)
;Above line defines a workspace named 'org.learn.clojure.amit' and all
;the functions written in this file will now be a part of this namespace
(defn namespace-hello-world []
  (println "Hello world from org.learn.clojure.amit"))

;To use above function in some other file we will eiter have to use one of the
;following keywords
;;use
;;require 
;;import

;; use can be used like so...
(use 'org.learn.clojure.amit)
(namespace-hello-world)
;use takes all public functions from the namespace and includes them in
;the current namespace. But this created one problem: Sometimes finding out
;the source of the function becomes difficult.
;Require solve this problem. Require keyword includes the namespaces with the
;name given by the programmer..as shown below
(require '(org.learn.clojure [amit :as amit-namespace]))
;Now to use the function we have to do
(amit-namespace/namespace-hello-world)
;This makes code very readable and finding out the source of the funciton 
;becomes very easy.

;;Functions on namespaces
;to create a new namespace with given name (if it already doesn't exist)
;notice how the name of the namespace is given (starting with a ')
(create-ns 'some-name-space)
;to move to a namespae with given name
(in-ns 'org.learn.clojure.amit)
;to show list of all the namespaces that are currently loaded
(all-ns)
;to find a particular ns with given name
(find-ns 'org.learn.clojure.amit)
;to find out the all internal mappings of a namepsace
;this will return a map of all internal mappings
(ns-interns 'org.learn.clojure.amit)
;to find out only public mappings of a namespace
(ns-publics 'org.learn.clojure.amit)

