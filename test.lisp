(println "hello world")
(println (+ 10 20 30))
;; Must quote. (quote x) is equal to 'x
(println (quote x))

(def a-list '(122 23 34 321))

; List operations
(println (first a-list))
(println (rest a-list))

(def f (lambda (n)
    (def x (+ 10 20 n))
    x
))

(println (f 200))

(defun fact (n)
    (if (= n 0)
        1
        (* n (fact (- n 1)))))

(println (fact 100))

;; Syntax Errors
; (
; ()
; (println 12))

(def x 10)
