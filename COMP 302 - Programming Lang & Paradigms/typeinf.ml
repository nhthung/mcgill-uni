(* Here is a sequence of functions, whose type will 
   get bigger and bigger and demonstrates that 
   type-checking in OCaml can be exponential in the 
   worst-case   -bp
*)

let id x = x 
let f y z = z y y 
let g y = f (f y)
let h y = g (g y)
let k y = h (h y) 
let l y = k (k y) 

(* 
(* Proceed with caution ... -bp *)

let j y = k (k y)

*)

(*
let id x = x 
let f y z = z y y 
let g y = f (f y)
let h y = g (g y);;
      val id : 'a -> 'a = <fun>
val f : 'a -> ('a -> 'a -> 'b) -> 'b = <fun>
val g :
  'a -> ((('a -> 'a -> 'b) -> 'b) -> (('a -> 'a -> 'b) -> 'b) -> 'c) -> 'c =
  <fun>
val h :
  'a ->
  ((((((('a -> 'a -> 'b) -> 'b) -> (('a -> 'a -> 'b) -> 'b) -> 'c) -> 'c) ->
     (((('a -> 'a -> 'b) -> 'b) -> (('a -> 'a -> 'b) -> 'b) -> 'c) -> 'c) ->
     'd) ->
    'd) ->
   (((((('a -> 'a -> 'b) -> 'b) -> (('a -> 'a -> 'b) -> 'b) -> 'c) -> 'c) ->
     (((('a -> 'a -> 'b) -> 'b) -> (('a -> 'a -> 'b) -> 'b) -> 'c) -> 'c) ->
     'd) ->
    'd) ->
   'e) ->
  'e = <fun>
# 
*)
