
(println "hello world")

(def nil '())

(def a-list '(122 23 34 321))

; List operations
(println (first a-list))
(println (rest a-list))

(def f (lambda (x) (* x 10 20)))

(println (+ 20 12 10 (f 200)))

(defun fact (n)
    (if (= n 0)
        1
        (* n (fact (- n 1)))))

(println (fact 5))

;; Syntax Errors
; (
; ()
; (println 12))

(def x 10)

(println (/ 10 20))