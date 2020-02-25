(* 3 questions : 
	Decoder (code -> string) 
	Encoder (string -> code)
	Create (? -> tree) 
*)

(* 
	Huffman encoding tree : 
	classic binary tree with leaves containing character and frequency
*)
type huff_tree = Leaf of char * float | Node of huff_tree * huff_tree

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
);;

type binary = Zero | One ;;
type code = binary list ;;
exception Bad_code ;;

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
);;

module CharKey = 
	struct
		type t = char
		let compare = Pervasives.compare
	end

(* Helpful class : creates a "hashmap" from char to 'a *)
module CharMap = Map.Make(CharKey);;

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
;;

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
;;

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
;;

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
;;

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
;;