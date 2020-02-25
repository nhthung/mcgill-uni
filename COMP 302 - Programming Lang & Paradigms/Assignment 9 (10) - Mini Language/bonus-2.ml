(* Question 1: let's compose something! *)

(* 1.1 Composition *)

let compose (fs : ('a -> 'a) list) : 'a -> 'a =
  fun x -> List.fold_right (fun f a -> f a) fs x

(* 1.2 Replication *)

let rec replicate (n : int) : 'a -> 'a list =
  let n = ref n in let rec x a = (n:= !n - 1); if !n = -1 then [] else a::(x a) in x 
  
(* 1.3 Repeating *)

let repeat (n : int) (f : 'a -> 'a) : 'a -> 'a =
  fun x -> compose (replicate n f) x

(* Question 2: unfolding is like folding in reverse *)

(* 2.1 Compute the even natural numbers up to an exclusive limit. *)
let evens (max : int) : int list =
  unfold (fun x -> (x, x+2)) (fun x -> max <= x) 0

  
(* 2.2 Compute the fibonacci sequence up to an exclusive limit. *)
let fib (max : int) : int list =
  unfold (fun (x, y) -> (x, (y, x+y))) (fun (x, y) -> max <= x) (1, 1)

(* 2.3 Compute Pascal's triangle up to a maximum row length. *)
let pascal (max : int) : (int list list) = 
  unfold (fun a -> (a, List.map2 (fun x y -> x + y) ([0]@a) (a@[0]))) 
    (fun b -> max < List.length b) [1]
  

(* 2.4 Implement the zip, which joins two lists into a list of tuples.
 * e.g. zip [1;2] ['a', 'c'] = [(1, 'a'); (2, 'c')]
 * Note that if one list is shorter than the other, then the resulting
 * list should have the length of the smaller list. *)
let zip (l1 : 'a list) (l2 : 'b list) : ('a * 'b) list = 
  unfold (fun (h1::t1, h2::t2) -> ((h1, h2), (t1, t2))) (function |[], _ |_, [] -> true |_ -> false) (l1, l2)
  
    



