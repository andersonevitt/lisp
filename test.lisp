; This is a comment

; Numbers:
10
201
; Strings
"this is a string, just like Java"
; Symbols:
; *some*
; another
; can-have-other-symbols?
; +

; Lists:
; Note: The leading quotation is equal to (quote args) and delays prevents evaluation.
; Without this it would try to call the function "1" which would result in an error
'(1 2 3 4)
'(name 120 (this is a sublist 15) 20)

; Functions:
; Functions are called by creating a list with the name as the first item
; There are builtins for working with the environment, lists, math, and booleans
(+ 12 23)
(< 15 10)
(= 12 12)


(def x 10)
(println (+ 12 23 3))
(println (* x (+ 12 32)))

; Lambda constructs a function without a name attached to it
(def add (lambda (arg1 arg2) (+ arg1 arg2)))
; These defun defines functions and is equal to (def name (lambda ...
(defun fib (n)
    (if (or (= n 0) (= n 1))
        n
        ; Functions can be recursive
        (+ (fib (- n 1)) (fib (- n 2)))))

(println "The 16th fibonacci is: ")
(println (fib 16))