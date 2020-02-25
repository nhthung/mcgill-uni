(* Exercise with List module functions *)

(* Given a string, return a list of characters *) 
let chars_of_string str = 
	let l = String.length str in
	let rec aux acc = function
		| (-1) -> acc
		| n -> aux (str.[n] :: acc) (n-1)
	in
	aux [] (l - 1)
;;

(* Given a list of characters, return a string *)
let string_of_chars cl = List.fold_left (fun str el -> str ^ (Char.escaped el)) "" cl;;

(* Same as above but using a reference for training on side effects and references and List.iter *)
let string_of_chars cl = 
	let r = ref "" in
	List.iter (fun el -> r := !r ^ (Char.escaped el)) cl;
	!r
;;

(* Given a generic list, create a list of lists of prefixes *)
let rec prefixes l = match l with
	| [] -> [[]]
	| el :: l' -> let ll = prefixes l' in [] :: List.map (fun l -> el :: l) ll
;;

(* Same as above but using HOFs *)	
let prefixes l = List.fold_right (fun el ll -> [] :: List.map (fun l -> el :: l) ll) l [[]];;

(* Put together : function that creates a list of prefixes of a string *)
(* The following are all equivalent *)
let str_prefixes str = List.map string_of_chars (prefixes (chars_of_string str));;

let str_prefixes str = List.map string_of_chars @@ prefixes @@ chars_of_string str;;

let str_prefixes str = str |> chars_of_string |> prefixes |> List.map string_of_chars;;