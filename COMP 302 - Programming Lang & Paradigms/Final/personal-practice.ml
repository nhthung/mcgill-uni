(* Lazy programming *)

type 'a susp = Susp of (unit -> 'a)
type 'a str = { hd: 'a; tl: ('a str) susp }

let force (Susp f) = f ()

let rec add s1 s2 =
{ hd = s1.hd + s2.hd ;
  tl = Susp (fun () -> add (force s1.tl) (force s2.tl)) }

let rec psums s =
{ hd = s.hd;
  tl = Susp (fun () -> add (psums s) (force s.tl)) }

let rec natsFrom n =
{ hd = n;
  tl = Susp (fun () -> natsFrom (n + 1)) }

let rec lookTo n s = begin
    let rec aux n s acc =
        if n = 0 then
            acc
        else
            aux (n - 1) (force s.tl) (acc @ [s.hd])
    in
        aux n s []
end

let rec fib =
{ hd = 0;
  tl = Susp (fun () -> fib') }
and fib' =
{ hd = 1;
  tl = Susp (fun () -> add fib fib') }

let nats = natsFrom 0

let s = psums nats

(* Continuations *)

let rec append l k = match l with
    | [] -> [k]
    | h::t -> h::append t k

(*
append [1; 2; 3] 2;;
==> [1; 2; 3; 2]
*)

let rec appendC l k =
    let rec appendC' l k c = match l with
        | [] -> c [k]
        | h::t -> appendC' t k (fun r -> c (h::r))
    in
        appendC' l k (fun r -> r)

let l = appendC [1; 2] 3

type 'a tree = Empty | Node of 'a * 'a tree * 'a tree

let tree =
    Node (
        0,
        Node (
            1,
            Empty,
            Node (2, Empty, Empty)
        ),
        Node (3, Empty, Empty)
    )

let rec find' d t c = match t with
    | Empty -> c ()
    | Node (x, l, r) ->
        if x = d then Some x
        else find' d l (fun () -> find' d r c)

let find d t = find' d t (fun () -> None)

let result = find 2 tree

let rec cchange coins amt sc fc =
  if amt = 0 then sc []
  else
    match coins with
    | [] -> fc ()
    | coin :: cs -> begin
        if coin > amt then
          cchange cs amt sc fc
        else
            cchange coins (amt - coin)
            (fun cl -> sc (coin :: cl))
            (fun () -> cchange cs amt (fun cl -> sc cl) fc)
      end

(* Fall 2018 Midterm *)

(* Question 1: Types *)

let combine f g = function x -> f (g x)
(*
('a -> 'b) -> ('c -> 'a) -> 'c -> 'b  = <fun>
*)

let rec f x = f (f x) in f true
(*
bool
*)

let test f x = if f 3 > f 2 then f x else f
(*
ill typed
*)

let inc_head = fun (h::t) -> h+1 in inc_head []
(*
int
*)

(* Question 2 *)

let point = (3, 2)
let component_x (x, y) = x
let component_y (x, y) = y

(* Question 4: List.map *)

(* Exterior product *)
let rec eprod a b = List.map (fun be -> List.map (fun ae -> ae * be) a) b

(* Question 5: BST *)

type 'a tree = Empty | Node of 'a * 'a tree * 'a tree
type interval_order = Within | Less | Greater

let compare_interval x (lo, hi) =
    if x < lo then Less
    else (if x > hi then Greater
    else Within)

let rec subtree t (lo, hi) = match t with
    | Empty -> Empty
    | Node ((k, v), l, r) -> begin
        match compare_interval k (lo, hi), l, r with
        | Less ->  subtree r (lo, hi)
        | Greater -> subtree l (lo, hi)
        | Within -> Node ((k, v), subtree l (lo, hi), subtree r (lo, hi))
    end

let rec subtree t interval = begin
    tree_fold
        (fun ((k, v), l, r) -> match compare_interval k interver with
            | Within -> Node ((k, v), l, r)
            | Less -> r
            | Greater -> l)
        t
        Empty
end

(* Fall 2017 Final *)

(* Question 1: Types *)

fun x y -> if x < 0 then x * (-1) else y
(*
int -> int -> int = <fun>
*)

let rec comb f = f (comb f)
(*
('a -> 'a) -> 'a = <fun>
*)

let combine f g = function x -> f x (g x)
(*
('a -> 'b -> 'c) -> ('a -> 'b) -> 'a -> 'c
*)

let rec f x = f (f x) in (f true, f 1)
(*
bool * int
*)
let test f x = if f 3 > f 2 then f x else f
(*
ill typed
*)

let (y, x) = (ref 3, ref 2) in let shift y = y := !x + !y in (shift, shift x)
(*
(int ref -> unit) * unit
*)

(* Question 2: References *)

(* .i *)
let (incGen, init) =
    let y = ref 3 in
    let inc x = y := x + !y in
        (inc, inc 2)

(*
Type
    x -> unit

incGen 3;;
    init has already updated y to 3 + 2 = 5
    incGen 3 would update y to y + 3 = 5 + 3 = 8
*)

(* .ii *)
let x = 3 in
let r = ref (2 + x) in
let f = (
    let y = x * !r in
    let r = ref 4 in
    fun u -> u + x + y + !r
) in
let x = 5 in
let y = 10 in
    r := 2; f (!r * y)

(*
Evaluate to 42
*)

(* Question 3: Binary trees *)

type 'a tree = Empty | Node of 'a * 'a tree * 'a tree
type path = H | L of path | R of path
exception NotFound

let tree =
    Node (
        0,
        Node (
            1,
            Empty,
            Node (2, Empty, Empty)
        ),
        Node (3, Empty, Empty)
    )

let rec find_path d t = match t with
    | Empty -> raise NotFound
    | Node (x, l, r) -> begin
        if x = d then
            H
        else
            try L (find_path d l)
            with NotFound -> R (find_path d r)
    end

let p = find_path 2 tree

type 'a trie = Empty | Node of 'a * 'a trie * 'a trie * 'a trie
type triPath = H | L of triPath | S of triPath | R of triPath
type dir = L | S | R
exception None of dir

let trie =
    Node (
        0,
        Node (
            1,
            Empty,
            Node (4, Empty, Empty, Empty),
            Empty
        ),
        Node (
            2,
            Empty,
            Node (5, Empty, Empty, Empty),
            Empty
        ),
        Node (
            3,
            Node (6, Empty, Empty, Empty),
            Empty,
            Empty
        )
    )

let rec findPath d t =
    let rec findPath' d t dir = match t, dir with
        | Empty, L -> raise (None L)
        | Empty, S -> raise (None S)
        | Empty, R -> raise (None R)
        | Node (x, l, s, r), dir -> begin
            if x = d then
                H
            else match dir with
            | L -> try L (findPath' d l L) with None L -> S (findPath' d s S)
            | _ -> try S (findPath' d s S) with None S -> R (findPath' d r S)
        end
    in
        findPath' d t L

let p = findPath 5 trie

type 'a tree = Empty | Node of 'a * 'a tree * 'a tree
type path = H | L of path | R of path
exception NotFound

let tree =
    Node (
        0,
        Node (
            1,
            Empty,
            Node (2, Empty, Empty)
        ),
        Node (3, Empty, Empty)
    )

let rec findPathC d t cont sc = match t with
    | Empty -> cont ()
    | Node (x, l, r) -> begin
        if x = d then
            sc H
        else
            findPathC d l
            (fun () -> findPathC d r cont (fun p -> sc (R p)))
            (fun p -> sc (L p))
    end

let findPath d t = findPathC d t (fun () -> raise NotFound) (fun p -> p)

let p = findPath 2 tree

(* Question 4: HOF *)

C3 = C0 C2 + C1 C1 + C2 C0
C4 = C0 C3 + C1 C2 + C2 C1 + C3 C0
C5 = C0 C4 + C1 C3 + C2 C2 + C3 C1 + C4 C0

let sum f n = fold_left (fun a b -> a + b) 0 (tabulate n f)

let nth_catalan n = match n with
    | 0
    | 1 -> 1
    | _ -> sum (fun i -> (nth_catalan i) * (nth_catalan (n - 1 - i))) (n-1)

(* Question 5: Lazy programming *)

type 'a susp = Susp of (unit -> 'a)
type 'a str = { hd: 'a; tl: ('a str) susp }

let force (Susp f) = f ()

let rec mult s1 s2 =
{ hd = s1.hd *. s2.hd;
  tl = Susp (fun () -> mult (force s1.tl) (force s2.tl)) }

let rec seq i =
{ hd = float (2 * (2 * i + 1)) /. float (i + 2);
  tl = Susp (fun () -> seq (i + 1)) }

let s = seq 0

let rec get i s = match i with
    | 0 -> s.hd
    | _ -> get (i - 1) (force s.tl)

let rec catalan =
{ hd = 1.;
  tl = Susp (fun () -> mult catalan (seq 0)) }

(* Question 6: OOP *)
exception OutOfBound
type 'a mseg = Put of 'a | Get | Restore

let rec makeCell n =
    let cell = ref n in
    let store = ref [] in
    fun action -> match action, !store with
        | Put n', _ -> store := !cell :: !store; cell := n'; n'
        | Get, _ -> !cell
        | Restore, [] -> raise OutOfBound
        | Restore, h::t -> store := t; cell := h; h

(* Review lecture *)

(* Types *)

fun x y -> if x * (-1) < 0 then (y, x) else (x, y)
(*
int -> int -> int * int
*)

let rec f b l = match l with
    | [] -> b
    | x::xs -> f x
(*
ill typed
*)

let rec f x = f (f x) in (f true, f 1)
(*
bool * int
*)

let combine f g = function x -> f x (g x)
(*
('a -> 'b -> 'c) -> ('a -> 'b) -> 'a -> 'c
*)

(* TA review *)

(* Prefix compression *)

let chars_of_string s = begin
    let len = String.length s in
    let rec aux acc = function
        | -1 -> acc
        | n -> aux (s.[n] :: acc) (n - 1)
    in aux [] (len - 1)
end

let string_of_chars cl = List.fold_left (fun c1 c2 -> c1 ^ (Char.escaped c2)) "" cl

let prefixes l = List.fold_right (fun el ll -> [] :: List.map (fun l -> el :: l) ll) l [[]]
let l = prefixes ['a'; 'b'; 'c']

let str_prefixes str = List.map string_of_chars (prefixes (chars_of_string str))
let sl = str_prefixes "thun"

(* Huffman trees *)

type huff_tree = Leaf of char * float | Node of huff_tree * huff_tree
type binary = Zero | One ;;
type code = binary list ;;
exception Bad_code ;;

(*
	Example of a tree :
	this one happens to have frequencies added to 1,
	but it is not a requirement
*)
let tree_ex : huff_tree = Node (
	Leaf (' ', 0.6),
	Node (
		Leaf ('a', 0.2),
		Node (
			Leaf ('b', 0.1),
			Leaf ('c', 0.1)
		)
	)
)

(* Example of a string and code pair, using tree_ex *)
let (message, encoded) : string * code = (
	"a abba cab",
	[
		One; Zero;
		Zero;
		One; Zero;
		One; One; Zero;
		One; One; Zero;
		One; Zero;
		Zero;
		One; One; One;
		One; Zero;
		One; One; Zero
	]
)
module CharKey =
	struct
		type t = char
		let compare = Pervasives.compare
	end

(* Helpful class : creates a "hashmap" from char to 'a *)
module CharMap = Map.Make(CharKey)

(*
	HOF : given a tree, creates a function decoding code into a string
	As we have seen previously, an HOF of this format can be seen as a partial evaluation
*)
let decode (tr0 : huff_tree) : code -> string =
	(*
		Accumulator is the string we've collected
		tr is the current tree
		l is the remaining list
	*)
	let rec inner acc tr l = match (tr, l) with
		| Leaf(c, _), [] -> (* Reached the end of the code with correct length *)
			acc ^ (Char.escaped c)
		| _, [] -> (* Reached end of code but not tree : bad length of code *)
			raise Bad_code
		| Leaf(c, _), _ -> (* Reached some letter *)
			inner (acc ^ (Char.escaped c)) tr0 l
		| Node(left, _), Zero::t -> (* Recursive case : go deeper *)
			inner acc left t
		| Node(_, right), One::t -> (* Recursive case : go deeper *)
			inner acc right t
	in
	inner "" tr0

(* Creates a char -> float map with frequencies from the string *)
let freqs str : float CharMap.t =
	(* Increments the value in map m at key c *)
	let add_or_create c m =
		match CharMap.find_opt c m with
			| None -> CharMap.add c 1. m
			| Some v -> CharMap.remove c m |> CharMap.add c (v +. 1.)
	in
	(* Loop over each character in the string and keep adding to the map *)
	let rec aux acc str = function
		| (-1) -> acc
		| n ->
			aux (add_or_create str.[n] acc) str (n - 1)
	in
	aux CharMap.empty str (String.length str - 1)

(* Given the frequency map, create a tree *)
let create_from_freqs m =
	(* Use map entries to create a list of leaves * value *)
	let keys = CharMap.fold (fun key v l -> (Leaf (key, v), v) :: l) m [] in
	(* Sort the leaves *)
	let sort l = List.sort (
		fun a b -> compare (snd a) (snd b)
	) l in
	(* Fold operation; however, since the list is dynamically updated, need to do custom version *)
	let rec fold = function
		| [] -> raise (Failure "Map is empty")
		| h::[] -> fst h (* One element : done *)
		| (left, v_l)::(right, v_r)::t -> (* At least two elements*)
			fold @@ sort @@ (Node(left, right), v_l +. v_r) :: t
	in fold (sort keys)

(* Simply put together the last two functions *)
let create : string -> huff_tree = fun s -> create_from_freqs (freqs s);;

(*
	HOF : given a tree, creates a function encoding a string into code
	As we have seen previously, an HOF of this format can be seen as a partial evaluation
*)
let encode (tr0 : huff_tree) : string -> code =
	(* For each character in tree, get code "list" *)
	let rec list_all : huff_tree -> (char * code) list = function
		| Leaf (c, _) -> [(c, [])]
		| Node (left, right) ->
			let left = list_all left and right = list_all right in
			let prepend x = List.map (fun (c, l) -> (c, x :: l)) in
			(prepend Zero left) @ (prepend One right)
	in
	let l = list_all tr0 in
	(* A map from characters to corresponding code *)
	let m : code CharMap.t =
		List.fold_left (fun m (c, l) -> CharMap.add c l m) CharMap.empty l
	in
	(* Main loop : for each character, generate the code *)
	let rec aux acc str = function
		| (-1) -> acc
		| n -> aux (CharMap.find str.[n] m @ acc) str (n-1)
	in
	fun str -> aux [] str (String.length str - 1)

(* Continuation version *)
let encode tr0 : string -> code =
	let prepend x = List.map (fun (c, l) -> (c, x :: l)) in
	(* For each character in tree, get code "list" *)
	let rec list_all cont : huff_tree -> 'a = function
		| Leaf (c, _) -> cont [(c, [])]
		| Node (left, right) ->
			(* 2 levels of continuation *)
			list_all (
				fun left -> (list_all (
					fun right -> cont (prepend Zero left @ prepend One right)
				) right )
			) left
	in
	(* A map from characters to corresponding code *)
	let m : code CharMap.t =
		list_all (List.fold_left (fun m (c, l) -> CharMap.add c l m) CharMap.empty) tr0
	in
	(* Main loop : for each character, generate the code *)
	let rec aux acc str = function
		| (-1) -> acc
		| n -> aux (CharMap.find str.[n] m @ acc) str (n-1)
	in
	fun str -> aux [] str (String.length str - 1)
