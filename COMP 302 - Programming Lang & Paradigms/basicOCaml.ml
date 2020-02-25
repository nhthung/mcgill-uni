(* Winter 2012 *)
(* Brigitte Pientka *)
(* Code for Lecture 1: Evaluation and Typing *)

(* Integers *)
(-1 : int) ;; 
( 4 : int) ;;
 -3+2 ;;
5 / 2 ;; (* Result: 2 : int *)

(* Reals *)
(3.14 : float);;

((5.0 /. 2.0) : float);; (* NOT 5 / 2 and NOT 5.0 / 2.0 *)

(* Type errors
# 5 / 3.0;;
Characters 4-7:
  5 / 3.0;;
      ^^^
Error: This expression has type float but an expression was expected of type
         int

# 5.0 / 3;;
Characters 0-3:
  5.0 / 3;;
  ^^^
Error: This expression has type float but an expression was expected of type
         int

# 5 /. 3;;
Characters 0-1:
  5 /. 3;;
  ^
Error: This expression has type int but an expression was expected of type
         float
# 
*)  


(* Booleans *)
(true : bool);;
(false : bool);;

if 0 = 0 then 1.0 else 2.0 ;;
(* = tests structural equality *) 

if true then 4.0 else 1.0 /. 0.0 ;;  
(* NOTE: division by 0.0 is not caught statically during type checking *)

(* Type error
# if 0 = 1 then 1 else 2.0;;
Characters 21-24:
  if 0 = 1 then 1 else 2.0;;
                       ^^^
Error: This expression has type float but an expression was expected of type
         int
*)


(* Typing and Evaluation *)
(* Some ill-typed expressions *)
(*
# 2.0 + 1;;
Characters 0-3:
  2.0 + 1;;
  ^^^
Error: This expression has type float but an expression was expected of type
         int
*)

(* Some well-typed expressions without a value *)
(* 
# 1 / 0;;
Exception: Division_by_zero.

# if 1 / 0 = 0 then true else false ;;
Exception: Division_by_zero.

# if false then 4 else 1 / 0 ;;
Exception: Division_by_zero.

*)

