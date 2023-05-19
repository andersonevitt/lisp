
(println "hello world")

(def nil '())

(def a-list '(122 23 34 321))

; List operations
(println (first a-list))
(println (rest a-list))

(def f (lambda (x) (* x 10 20)))

(println (+ 20 12 10 (f 100)))

(defun fact (n)
    (if (= n 0)
        1
        (* n (fact (- n 1)))))

(println (fact 5))

;; Errors
; (
; ()
; (println 12))