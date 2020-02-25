(** Church numerals *)
type 'a church = Church of (('a -> 'a) -> 'a -> 'a)

let ch_zero = Church (fun f x -> x)
let ch_one = Church (fun f x -> f x)
let ch_two = Church (fun f x -> f (f x))

(* Check if a Church numeral is equal to zero *)

(* iszero : 'a church -> bool *)
let iszero (Church c) = (c (fun x -> false)) true
(* iszero ch_zero
=> (fun f x -> x) (fun _ -> false) true
=> true

iszero ch_one
=> (fun f x -> f x) (fun _ -> false) true
=> (fun _ -> false) false
=> false
*)

                      
(* Convert an integer to a Church numeral *)

(* create : int -> 'a church *)
let rec create (n : int) : 'a church =
  match n with
  | 0 -> Church (fun f x -> x)
  | _ -> match create (n - 1) with
    (* Add 1 to the Church numeral representing n - 1 *)
    | Church c -> Church (fun f x -> f (c f x))
       
(* Convert a Church numeral to an integer *)

(* churchToInt : 'a church -> int *)
let churchToInt (Church c) = c (fun n -> n + 1) 0
(* churchToInt ch_zero
=> (fun f x -> x) (fun n -> n + 1) 0
=> 0 

churchToInt ch_one
=> (fun f x -> f x) (fun n -> n + 1) 0
=> (fun n -> n + 1) 0
=> 1

churchToInt ch_two
=> (fun f x -> f (f x)) (fun n -> n + 1) 0
=> (fun n -> n + 1) ((fun n -> n + 1) 0)
=> (fun n -> n + 1) (0 + 1)
=> (fun n -> n + 1) 1
=> 1 + 1
=> 2
*)
  

(* Add 1 to a Church numeral *)
let succ (Church c) = Church (fun f x -> f (c f x))
(* Use f^{n+1} x = f (f^n x) *)
                    
(* Add two Church numerals *)
(* add : 'a church -> 'a church -> 'a church *)
let add (Church cm) (Church cn) =
  Church (fun f x -> cm f (cn f x))
(* Use f^{m+n} x = f^m (f^n x) *)

(* Multiply two Church numerals *)
(* mult : 'a church -> 'a church -> 'a church *)
let mult (Church cm) (Church cn) =
	Church (fun f x -> cn (cm f) x)
(* Use f^{mn} x = (f^m)^n x *)

(* Exponentiate two Church numerals *)
let exp (Church cm) (Church cn) = Church (cn cm) 

(** Church lists *)

(* The type of churchlist is the same as the type of
 * fun f b -> fold_right f lst b
 * i.e., ('a -> 'b -> 'b) -> 'b -> 'b
 *)
type ('a, 'b) churchList = ChurchList of (('a -> 'b -> 'b) -> 'b -> 'b)

(* Example integer Church lists *)
let ch_empty = ChurchList (fun cons nil -> nil)
let ch_list1 = ChurchList (fun cons nil -> cons 0 nil)
let ch_list2 = ChurchList (fun cons nil -> cons 1 (cons 2 nil))

(* Does essentially the same thing as fold_right,
 * just optimised for our usecase.
 *)
let rec createList lst = match lst with
  | [] -> ChurchList (fun cons nil -> nil)
  | x::xs -> match createList xs with
    | ChurchList xs' -> ChurchList (fun cons nil -> cons x (xs' cons nil))
        
(* To convert to "real" lists, we give the list constructors as f and b *)
let churchlistToList (ChurchList lst) = lst (fun x xs -> x::xs) []

(* isempty is similar to iszero for churchNats *)
let isempty (ChurchList lst) = lst (fun _ _ -> false) true

(* length is similar to churchToInt *)
let length (ChurchList lst) = lst (fun _ r -> 1 + r) 0
 
(* append is similar to add
 * use the fact that
 * append l1 l2 = fold_right (fun x xs -> x :: xs) l1 l2
 * i.e., we keep l1 as is except we replace its terminator
 * by the entirety of l2.
 *)
let append (ChurchList lst1) (ChurchList lst2) =
  ChurchList (fun cons nil -> lst1 cons (lst2 cons nil))

(* Cartesian product: we'll use the following observations to help us
 * 
 * map f lst = fold_right (fun x xs -> f x :: xs) lst []
 * 
 * cart_prod l1 l2 
 * = fold_right (fun x xs -> (map (fun y -> (x, y)) l2) @ xs) l1 []
 * = fold_right (fun x xs -> fold_right (fun y ys -> (x, y) :: ys) l2 @ xs) l1 []
 *)
let cartesian_product (ChurchList lst1) (ChurchList lst2) =
  let map f lst =
    fun cons nil -> lst (fun x xs -> cons (f x) xs) nil
  in ChurchList (fun cons nil ->
      lst1 (fun x xs -> (map (fun y -> (x, y)) lst2) cons xs) nil)
(* or if we inlined the definition of map:
 *    lst1 (fun x xs -> lst2 (fun y ys -> cons (x, y) ys) xs) nil)
 *)
    
