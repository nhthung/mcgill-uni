(* ————————————————————–—————————————————————————————————————————————– *)
(* QUESTION 1 *)
(* Helper function: given two expressions, we add or multiply
   them     *)
(* ————————————————————–—————————————————————————————————————————————– *)

let add e1 e2 = 
  
  let add' ex = 
    match ex with
    |Num(x) -> !x
    |Plus(x, e, e') -> !x
    |Times(x, e, e') -> !x
  in
  
  match e1 with
  |Num(n) -> n := !n + add' e2; Plus(n, e1, e2)
  |Plus(n, e, e') -> n := !n + add' e2; Plus(n, e1, e2)
  |Times(n, e, e') -> n := !n + add' e2; Plus(n, e1, e2)
      
      

let mult e1 e2 = 
  
  let mult' ex = 
    match ex with
    |Num(x) -> !x
    |Plus(x, e, e') -> !x
    |Times(x, e, e') -> !x
  in
  
  match e1 with
  |Num(n) -> n := !n * mult' e2; Times(n, e1, e2)
  |Plus(n, e, e') -> n := !n * mult' e2; Times(n, e1, e2)
  |Times(n, e, e') -> n := !n * mult' e2; Times(n, e1, e2)

(* ————————————————————–—————————————————————————————————————————————– *)
(* QUESTION 2                                                        *)
(* compute_column m f = c

   Given a spreadsheet m and a function f, compute the i-th value in
   the result column c by using the i-th value from each column in m.

   Example:
   m = [ [a1 ; a2 ; a3 ; a4] ;
         [b1 ; b2 ; b3 ; b4] ;
         [c1 ; c2 ; c3 ; c4] ]

  To compute the 2nd value in the new column, we call f with
  [a2 ; b2 ; c2]

   Generic type of compute_column:
     'a list list -> ('a list -> 'b) -> 'b list

   If it helps, you can think of the specific case where we have a
   spreadsheet containing expressions, i.e.
   compute_column: exp list list -> (exp list -> exp) -> exp list

   Use List.map to your advantage!

   Carefully design the condition when you stop.
*)
(* ————————————————————–—————————————————————————————————————————————– *)

let compute_column sheet (_, f) = 
  
  let ( spread : spreadsheet ) = [] in 
 
  let rec r1 (col : column) (spread : spreadsheet) = 
    match col with 
    |h::t -> (match spread 
              with
              |h'::t' -> (h'@[h])::(r1 t t') 
              |[] -> [h]::(r1 t [])
             )
    |[] -> spread
  in
  
  
  let rec r2 sheet spread = 
    match sheet with 
    |h::t -> r2 t (r1 h spread)
    |[] -> spread
  in
  
  List.map (fun x -> f x) (r2 sheet spread)
    


  
(* ————————————————————–—————————————————————————————————————————————– *)
(* QUESTION 3 *)
(* Implement a function update which given an expression will re-
   compute the values stored at each node. This function will be used
   after we have updated a given number.

   update  : exp -> unit

*)
(* ————————————————————–—————————————————————————————————————————————– *)
    (*
      let rec update expr = match expr with
        |Num(a) -> ()
        |Plus(b, ex, ex') -> (update ex); (update ex'); b:= (match (add ex ex') with Plus(x, _, _) -> !x)
        |Times(c, ex, ex') -> (update ex); (update ex'); c:= (match (mult ex ex') with Times(x, _, _) -> !x)
    
*)



let n ex = match ex with 
  |Num(x) -> !x
               (*
                 let add' e e' = 
                   n e + n e' 
    
                 let mult' e e' = 
                   n e * n e'               

                 let rec helper ex = match ex with 
                   |Num(a) ->  Num(a)
                   |Plus(b, ex1, ex2) -> (match add' (helper ex1) (helper ex2) with n -> b:= n; Num(ref n))
                   |Times(c, ex1, ex2) -> (match mult' (helper ex1) (helper ex2) with n -> c:= n; Num(ref n))
                                          *)
let rec helper ex = match ex with 
  |Num(a) ->  !a
  |Plus(b, ex1, ex2) -> b:= helper ex1 + helper ex2; !b
  |Times(c, ex1, ex2) -> c:= helper ex1 * helper ex2; !c

let rec update expr = (*match expr with*)
  let x = helper expr in ()
                         
                         

let update_sheet sheet = 
  (*
    let rec f un l = match l with 
      |h::t -> (update h); f () t 
      |[] -> ()
    in
  *)
  List.fold_left (fun () x ->  (List.fold_left (fun () y -> update y) () x)) () sheet 
    
    

(* EXTRA FUN:
   Our implementation traverses the whole expression and even worse
   the whole spreadsheet, if one number cell is being updated.

   If you are looking for a nice programming problem, think
   about how to update only those values which are parent nodes to the
   Number being updated. You might need to choose a different
   representation for expressions.

*)
(* ————————————————————–—————————————————————————————————————————————– *)
