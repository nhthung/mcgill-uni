exception NotImplemented

(* Question 1 : Let's have cake! *)

type price = float
type weight = float
type calories = int
type ingredient = Nuts | Gluten | Soy | Dairy

type cupcake = Cupcake of price * weight * calories * ingredient list

let c1 = Cupcake (2.5, 80.3, 250, [Dairy; Nuts])
let c2 = Cupcake (2.75, 90.5, 275, [Dairy; Soy])
let c3 = Cupcake (3.05, 100.4, 303, [Dairy; Gluten; Nuts])
let c4 = Cupcake (3.25, 120.4, 330, [Dairy; Gluten ])

let cupcakes = [c1 ; c2 ; c3 ; c4]

(* Question 2 : Generic Tree Traversals *)

type 'a tree = Node of 'a * 'a tree * 'a tree | Empty

(* -------------------------------------------------------------*)
(* QUESTION 1 : Let's have cake!                                *)
(* -------------------------------------------------------------*)

(* allergy_free : ingredient list -> cupcake list -> cupcake list *)
let allergy_free allergens cupcakes =
  match allergens, cupcakes with
  | [], _
  | _ , [] -> cupcakes 
  | _ , _  ->
      List.filter
        (fun c ->
           match c with Cupcake (_, _, _, il) ->
             List.for_all
               (fun i ->
                  not (
                    List.exists
                      (fun a -> a = i)
                      allergens
                  )
               )
               il
        )
        cupcakes

(* -------------------------------------------------------------*)
(* QUESTION 2 : Generic Tree Traversals                         *)
(* -------------------------------------------------------------*)

(* map_tree : ('a -> 'b) -> 'a tree -> 'b tree *)
let rec map_tree (f: 'a -> 'b) (t: 'a tree) = match t with
  | Empty -> Empty
  | Node (n, c1, c2) -> Node (f n, map_tree (f) (c1), map_tree (f) (c2)) 

(* delete_data : ('a * 'b) tree -> 'a tree *)
let delete_data (t: ('a * 'b) tree) = match t with
  | Empty -> Empty
  | Node ((n1, n2), _, _) -> map_tree (fun (n1, n2) -> n1) t

(* fold_tree : ('a * 'b ' * 'b -> 'b) -> 'b -> 'a tree -> 'b *)
let rec fold_tree (f: 'a * 'b * 'b -> 'b) (e: 'b) (t: 'a tree) =
  match t with
  | Empty -> e
  | Node (n, c1, c2) -> f (n, fold_tree f e c1, fold_tree f e c2)

(* size : 'a tree -> int *)
let size tr = fold_tree (fun (n, c1, c2) -> 1 + c1 + c2) 0 tr

(* reflect : 'a tree -> 'a tree *)
let reflect tr = fold_tree (fun (n, c1, c2) -> Node (n, c2, c1)) Empty tr

(* inorder : 'a tree -> 'a list *)
let inorder tr = fold_tree (fun (n, c1, c2) -> c1 @ [n] @ c2) [] tr

allergy_free [Dairy; Nuts] cupcakes;;