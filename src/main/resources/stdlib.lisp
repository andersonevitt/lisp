;; The very basic lisp standard library

(def nil '())

(def car first)
(def cdr rest)


;; Returns the evaluated form of an s-expression
(defun eval (val) val)