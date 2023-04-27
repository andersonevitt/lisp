

(defun fib (n)
    (if (or (= n 0) (= n 1))
        n
        (+ (fib (- n 1)) (fib (- n 2)))))

(println (fib 16))

(println 'x)