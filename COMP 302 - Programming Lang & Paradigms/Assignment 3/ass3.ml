(* -------------------------------------------------------------*)
(* QUESTION 1 : String manipulation  [20 points]                *)
(* -------------------------------------------------------------*)

(* string_explode : string -> char list *)
let string_explode s = tabulate (fun n -> String.get s n) (String.length s)

(* string_implode : char list -> string *)
let string_implode l = match l with
  | [] -> ""
  | h::t -> List.fold_right (fun h acc -> Char.escaped h ^ acc) l "" 
  

(* -------------------------------------------------------------*)
(* QUESTION 2 : Insert a string into a dictionary  [20 points]  *)
(* -------------------------------------------------------------*) 

(* Insert a word into a dictionary. Duplicate inserts are allowed *) 

let insert s t =
  (* ins: char list -> char trie list -> char trie list *)
  let rec ins l t = match l, t with
    | []            , _
    | _             , [Empty]         -> t @ (unroll l)
    | _             , Empty::t_tail   -> Empty :: ins l t_tail
    | _             , []              -> t @ unroll l
    | c_head::c_tail,
      Node (n_head, n_tail) :: t_tail ->
        if n_head = c_head then
          Node (n_head, ins c_tail n_tail) :: t_tail
        else
          Node (n_head, n_tail) :: ins l t_tail
  in
  ins (string_explode s) t

(* -------------------------------------------------------------*)
(* QUESTION 3 : Look up a string in a dictionary   [20 points]  *)
(* -------------------------------------------------------------*)

(* Look up a word in a dictionary *)

let lookup s t =
  (* lkp : char list -> char trie list -> bool *)
  let rec lkp l t =
    match l, t with
    | []            , Empty::t_tail       -> true
    | []            , []                  -> false
    | []            , Node (_, _)::t_tail -> lkp [] t_tail
    | l             , []
    | l             , [Empty]             -> false
    | l             , Empty::t_tail       -> lkp l t_tail
    | c_head::c_tail,
      Node (n_head, n_tail)::t_tail ->
        if c_head = n_head then
          lkp c_tail n_tail
        else
          lkp l t_tail
  in
  if t = [] then
    false
  else
    lkp (string_explode s) t

(* -------------------------------------------------------------*)
(* QUESTION 4 : Find all strings in a dictionary   [OPTIONAL]   *)
(* -------------------------------------------------------------*)

let to_words t =
  let rec to_words' t acc =
    match t with
    | [] -> acc
    | Empty::t_tail -> to_words' t_tail (acc @ [""])
    | Node (n_head, n_tail) :: t_tail -> 
        (to_words' n_tail (acc @ [n_head])) @
        (to_words' t_tail acc) 
  in
  if t = [] then
    []
  else
    to_words' t []

(* Find all strings which share the prefix p *) 

let find_all prefix t =
  (* find_all' : char list -> char trie list -> char list list *) 
  let rec find_all' l t =
    match l, t with
    | l, Empty::t_tail -> find_all' l t_tail
    | p_head::p_tail, Node(n_head, n_tail)::t_tail ->
        if p_head = n_head then []
        else []
  in
  if t = [] then
    []
  else
    find_all' (string_explode prefix) t

(* -------------------------------------------------------------*)
(* QUESTION 5 :  Logic functions   [OPTIONAL]                   *)
(* -------------------------------------------------------------*)

(* eval: labeled_pred -> labeled_pred -> int -> int -> bool *)
let eval (_, (p : int -> bool)) (_, (q : int -> bool)) (n : int) =
  raise NotImplemented
