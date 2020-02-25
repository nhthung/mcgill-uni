(* --------------------------------------------------------------------*)
(* QUESTION 1: House of Cards                                          *)
(* --------------------------------------------------------------------*)

(* Q1: Comparing cards *)
(* Comparing two ranks *)
let dom_rank (r1 : rank) (r2 : rank) =
  match r1, r2 with
  | Ace, _
  | King, Queen  | King, Jack   | King, Ten | King, Nine
  | King, Eight  | King, Seven  | King, Six
  | Queen, Jack  | Queen, Ten   | Queen, Nine
  | Queen, Eight | Queen, Seven | Queen, Six
  | Jack, Ten    | Jack, Nine   | Jack, Eight
  | Jack, Seven  | Jack, Six
  | Ten, Nine    | Ten, Eight
  | Ten, Seven   | Ten, Six
  | Nine, Eight  | Nine, Seven
  | Nine, Six
  | Eight, Seven
  | Eight, Six
  | Seven, Six -> true
  | _, _ -> r1 = r2
    

(* Comparing two cards (r1, s1) and (r2, s2) *)
let dom_card (c1 : card) (c2 : card) =
  match c1, c2 with (r1, s1), (r2, s2) ->
    match s1 = s2,
          dom_suit s1 s2,
          dom_rank r1 r2
    with
    | true, _, true -> true
    | false, true, _ -> true 
    | _, _, _ -> false

(* Q2: Insertion Sort – Sorting cards in a hand *)
let rec insert (c : card) (h : hand) : hand =
  match h with
  | Empty -> Hand (c, Empty)
  | Hand (c', h') ->
      if dom_card c c' then
        Hand (c, Hand (c', h'))
      else
        Hand (c', insert c h')

let rec sort (h : hand) : hand =
  let rec sort' h acc =
    match h with
    | Empty -> acc
    | Hand (c', h') -> sort' h' (insert c' acc)
  in
  if h = Empty then
    Empty
  else
    sort' h Empty

(* Q3: Generating a deck of cards *)
let rec generate_deck (suits : suit list) (ranks : rank list) : card list =
  match suits, ranks with
  | [], _ | _, [] -> []
  | suit_h::suit_t, rank_h::rank_t ->
      (rank_h, suit_h)::
      (generate_deck [suit_h] rank_t)@
      (generate_deck suit_t ranks)

(* Q4: Shuffling a deck of cards *)
let split (deck : card list) (n : int) : card * card list = 
  let rec split' deck n acc =
    match n > 0, deck with
    | true, h::t -> split' t (n - 1) (acc @ [h])
    | false, h::t -> (h, acc @ t)
  in
  split' deck n []
    
let shuffle (deck : card list) : card list =
  let size = List.length deck in
  let rec select deck n acc = 
    let index = Random.int n in
    match n > 1, deck, split deck index with
    | true, _, (card', deck') -> select deck' (n - 1) (acc @ [card'])
    | false, h::[], _ -> acc @ [h]
  in
  if deck = [] then
    []
  else
    select deck size []

(* --------------------------------------------------------------------*)
(* QUESTION 2: Sparse Representation of Binary Numbers                 *)
(* ------------------------------------------------------------------- *)

(* Auxiliary function: pow n k
   
   pow : int -> int -> int
   
   Raises n to the power of k.
*) 
let pow (n : int) (k : int) =
  let rec pow' (n, k) = match (n, k) with
    | (_, 0) -> 1
    | (0, _) -> 0
    | (_, _) -> n * pow' (n, (k - 1)) 
  in
  if k >= 0 then
    pow' (n, k)
  else raise Domain

(* Auxiliary function: toBin dec
   
   toBin : int -> nat
  
   Converts an int to its binary representation
   as a list.
     
   Example: 4 is 100. Then toBin 4 returns [0; 0; 1]
*)      
let toBin (dec : int) : nat =
  let rec toBin' dec acc =
    match dec > 0 with
    | true -> toBin' (dec / 2) (acc @ [dec mod 2])
    | false -> acc 
  in
  if dec = 0 then
    []
  else toBin' dec []

(* Auxiliary function: toSbin dec
   
   toSbin: int -> nat
     
   Takes an int, get its toBin output,
   then converts it to its sparse binary representation.
   
   Example: toSbin 5 returns [1; 4]
*)
let toSbin (dec : int) : nat =
  let bin = toBin dec in
  let rec toSbin' (n : nat) exp acc =
    match n with
    | [] -> acc
    | h::t -> if h = 1 then
          toSbin' t (exp + 1) (acc @ [pow 2 exp])
        else
          toSbin' t (exp + 1) acc
  in
  toSbin' bin 0 []
    
(* toInt is useful as an auxiliary function for questions 1, 2 and 3.
   
   For this reason, question 4's position has been rearranged.
*)
(* Q4: Converting to integer - tail recursively *)
let rec toInt (n : nat) (acc : int) : int = 
  match n with
  | [] -> acc
  | h::t -> toInt t (acc + h)

let sbinToInt (n : nat) : int = toInt n 0    

(* Q1: Incrementing a sparse binary number *)
let inc (ws : nat ) : nat = toSbin (sbinToInt ws + 1)

(* Q2: Decrementing a sparse binary number *)
let dec (ws : nat) : nat = match ws with
  | [] -> raise Domain
  | _ -> toSbin (sbinToInt ws - 1)

(* The auxiliary functions above make question 3 simple.
   sbinToInt can convert m & n to ints,
   then the function could calculate and return toSbin (m + n).
   
   However, as the function is defined as recursive,
   I will respect that and use recursion instead,
   Solving the problem with only the functions inc and dec.
*)           
(* Q3: Adding sparse binary numbers *)
let rec add (m : nat) (n : nat) : nat  =
  match m, n with
  | _, [] -> m
  | _, _ -> add (inc m) (dec n)


