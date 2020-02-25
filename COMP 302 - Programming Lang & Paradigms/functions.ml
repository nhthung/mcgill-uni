(* Code for Lecture 2: Binding, Scope, Functions *)
(* Bindings *)

(* pi up to 2 digits - we add the type annotation to be explicite, but
   the type annotation is not necessary. *)
let (pi : float) = 3.14 ;;

let pi = 3.14 ;;

(* local bindings *)

let (m:int) = 3 in 
let (n:int) = m * m in  (* n = 9 *) 
let (k:int) = m * m in  (* k = 9 *)
 k*n                  (* result will be 81 *)
;;

(* multiple local bindings which do not depend on each other can
   also be declared using the keyword and. *)
let (m:int) = 3 and
    (n:int) = 4 in  
let (k:int) = m + n in  (* k = 7 *)
 k*n                  (* result will be 28 *)
;;



(* Bindings persist -- however later
  binders may overshadow earlier binders.

*)

let (k : int) = 4;;

let (k : int) = 3 in  k * k ;;       (* the result will be 9! *)


k;;               (* at this point k = 4 again *)
    

(* Functions *)

let area : float -> float = function r -> pi *. r *. r;;
  
let a2 = area (2.0);;      (* result a2 = 12.56 *)

(* We can shadow the definition of pi. *)
let (pi : float) = 6.0;;      (* now pi = 6.0 *)

(* The area function will remain unchanged. ! *)
let a3 = area (2.0);;       (* result a3 = 12.56 *)

(* We can shadow area with a more accurate definition. *)
let pi = 4. *. atan 1.;;

let area (r:float) = pi *. r *. r;;

let a4  = area (2.0);;      (* result a4 =  12.5663706144 *)
                            (* it will use the new pi and the new area def. *)


(* Recursive Functions *)

(* 
       val fact:int->int
       fact(n) = n!
       Invariant: n >= 0
       Effects:   none
*)

let rec (fact:int->int) = 
          function (n:int) -> if n = 0 then 1 
                       else n * fact (n-1);;

(* Here is a slightly less cumbersome way of writing this: *)

let rec fact n =
  if n = 0 then 1 
  else n * fact (n-1);;


(*
   Checking the invariant (here: n >= 0) each time around
   the loop is often inefficient.
*)

exception Domain;;

let rec fact n =
  if n < 0 then raise Domain
  else if n = 0 then 1 
  else n * fact(n-1);;


(*
   Better to check the invariant for the externally
   visible function only, and not during recursion.
*)

let rec fact n =
  let rec f n = 
    if n = 0 then 1
       else n*f(n-1)
  in
    if n < 0 then raise Domain
    else f(n)
;;


(* Tail-recursive version of factorial *)

let rec fact_tr1 n = 
  let rec f (n, m) = 
    match n with  (* NOTE: Use of pattern matching *)
      | 0 -> m
      | n ->  f(n-1, n*m)
  in
   f(n,1)
;;

(* or .... *)

let rec fact_tr2 n = 
  let rec f(n, m) = 
    if n = 0 then m else f(n-1, n*m)
  in
    f(n,1)
;;


(* How do we know they will compute the same result? 

   Can we prove that fact_tr2(n) = fact(n) ? 

*)


(* More recursive functions... *)

(*
   A function of two arguments is actually a function
   taking a pair as argument.
 *)


(* add: int * int -> int *)
let rec add (x, y) = x + y


(* add': int -> int -> int *)
let rec add' x y = x + y

(* sum: int -> int *)
let rec sum n = 
  if n = 0 then 0 else n + sum(n-1)

(* power(n,k) = n^k for k >= 0 where 0^0 = 1 *)
(* power : (int * int) -> int *)
let rec power (n, k) =
  if k = 0 then 1
  else n * power (n, k-1)


(* power' : int -> int -> int *)
let rec power' n k = 
  if k = 0 then 1 else n * power' n (k-1)

(* How do we know that 

   power(n,k) = power' n k ?

   later in the course...

In general: any function f : 'a * 'b -> 'g 
can be rewritten as a function f' : a' -> b' -> g'

*)


(************************************************************************)

