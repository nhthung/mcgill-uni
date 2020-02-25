
(* ---------------------------------------------------- *)
(* Revisiting Exceptions and Continuations              *)
(* ---------------------------------------------------- *)

exception Change

(* change: : int list -> int -> int list *)
let rec change coins amt =
  if amt = 0 then []
  else
    begin match coins with
      | [] -> raise Change
      | coin::cs ->
	  if coin > amt then
	    change cs amt
	  else
	    try
	      coin :: change coins (amt - coin)
	    with Change -> change cs amt
    end

let change_top coins amt =
  try
    let c = change coins amt in
    print_string ("Return the following change: "
                  ^ listToString c ^ "\n")
  with Change -> print_string ("Sorry, I cannot give change\n")


(* Giving change with continuations *)
let rec cchange (coins: int list) (amt:int)
          (sc: int list -> unit) (fc: unit -> unit) =
  if amt = 0 then fc ()
  else begin
      match coins with
      | [] -> sc []
      | coin::cs -> begin
          if coin > amt then
            cchange cs amt sc fc
          else
            cchange coins (amt - coin)
            (fun cl -> sc (coin :: cl))
            (fun () -> cchange cs amt (fun cl -> sc cl) fc)
      end
  end


let cchange_top coins amt =
  let sc = fun c ->  print_string ("Return the following change: "
                                   ^ listToString c ^ "\n") in
  let fc = (fun () -> print_string ("Sorry, I cannot give change\n")) in
  cchange coins amt sc


(* Here is the behavior of change_top :

# change_top [5 ; 2] 3;;
Sorry, I cannot give change
- : unit = ()
# change_top [5 ; 2] 8;;
Return the following change: 2, 2, 2, 2
- : unit = ()
# change_top [25;10;5;2] 43;;
Return the following change: 25, 10, 2, 2, 2, 2
- : unit = ()
# change_top [25;10;5;2] 44;;
Return the following change: 25, 10, 5, 2, 2
- : unit = ()
# change_top [25;10;2] 44;;
Return the following change: 10, 10, 10, 10, 2, 2
- : unit = ()
# change_top [25;10;2] 43;;
Return the following change: 25, 10, 2, 2, 2, 2
- : unit = ()
# change_top [25;10;2] 23;;
Sorry, I cannot give change
- : unit = ()
#
*)
(* ---------------------------------------------------- *)
(* ---------------------------------------------------- *)
(* Defining an n-ary tree *)

type 'a ntree = Children of 'a * ('a ntree) list

(* ---------------------------------------------------- *)
let even x = (x mod 2) = 0

let f = Children (22, [
         Children (19, [] ) ;
         Children (24, [Children (2, [] ) ; Children (1,[] ) ] ) ;
	 Children (7, [] ) ] )
(*


f =              22
              /   |   \
            19   24    7
                /  \
               2    1
 *)
(* ---------------------------------------------------- *)
(* Traversing the n-ary tree using exceptions           *)
exception NotFound

(* find the first element in the n-ary tree that
   satisfies the property p – traversing the tree
   infix traversal, checking the data at the node first,
   and then checking all children from left to right
*)

let rec find p t = assert false
(* ---------------------------------------------------- *)
(* Lazy Programming                                     *)
(* ---------------------------------------------------- *)
(* ---------------------------------------------------- *)
(* Suspended computation : we can suspend computation
   by wrapping it in a closure. *)
type 'a susp = Susp of (unit -> 'a)

(* delay: *)
let delay f = Susp(f)

(* force: *)
let force (Susp f) = f ()

(* ---------------------------------------------------- *)
(* Define an infinite stream via observations we can make
   about it.
*)

type 'a str = {hd: 'a  ; tl : ('a str) susp}

let rec str_ones = {hd = 1 ; tl = Susp (fun () -> str_ones)}

let rec numsFrom n =
{hd = n ;
 tl = Susp (fun () -> numsFrom (n+1))}

let nats = numsFrom 0

(* ---------------------------------------------------- *)
(* Inspect a stream up to n elements

*)
let rec take_str n s = match n with
  | 0 -> []
  | n -> s.hd :: take_str (n-1) (force s.tl)

(* ------------------------------------------------------- *)
(* val zip : ('a -> 'b -> 'c) -> 'a str -> 'b str -> 'c str *)
(* zip two streams togethether using f *)
let rec zip f s1 s2 =  assert false


let rec add_str s1 s2 = zip (fun x1 x2 -> x1 + x2) s1 s2


(* ------------------------------------------------------- *)
(* Computing partial sums lazily over a stream of nats

   psums 0 1 2 3 4 5  .....
   returns: 0 1 3 6 10 15 ...

  *)

let rec psums s =  assert false

(* let pSeq = psums nats *)

(* ---------------------------------------------------- *)
(* Objects and references                               *)
(* ---------------------------------------------------- *)

type action = Open | Close
exception Error of string

(* Write a function make_lock which generates a function  action -> unit
   which acts on a lock which is either Open or Close.

   If the lock is Open and ask to Close it, we set the lock to Close.
   If the lock is Close and we ask to Open it, we set the lock to Open.
   If the lock is Open and we ask to Open it
   or the lock is Close and we ask to Close it (again) then we raise an error.

*)

let make_lock () = begin
    let lock = ref Close in
    fun action -> match !lock, action with
        | Open, Close -> lock := Close
        | Close, Open -> lock := Open
        | Open, Open
        | Close, Close -> raise (Error "Bapbapaba")
end

(* ---------------------------------------------------- *)
(* ---------------------------------------------------- *)
(* INDUCTION PROOF *)
(*
let rec size t = match t with
  | Leaf -> 0
  | Node (l, x, r) -> x + size l + size r

let rec size_acc t acc = match t with
  | Leaf -> acc
  | Node (l, x, r) -> size_acc l (x + size_acc  r acc)

(*
THEOREM:  size t + acc = size_acc t acc

*)
*)

(* ---------------------------------------------------- *)
(* THANK YOU - IT WAS A LOT OF FUN!                     *)
(* ---------------------------------------------------- *)
