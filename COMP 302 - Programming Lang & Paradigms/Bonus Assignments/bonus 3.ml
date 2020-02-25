(* Question 1: unify *)
let rec unify (t1 : tp) (t2 : tp) : unit =
  match t1, t2 with
  (* unifying identical concrete types does nothing *)
  | Int, Int
  | Bool, Bool -> ()
  (* For type constructors, recursively unify the parts *)
  | Arrow (t1, t1'), Arrow (t2, t2') ->
      unify t1 t2; unify t1' t2'
                     
  | Product tl1, Product tl2 ->
      begin
        match tl1, tl2 with
        | [], [] -> ()
        | h1::t1, h2::t2 -> unify h1 h2; unify (Product t1) (Product t2)
        | x, [] | [], x -> unif_error UnifProductMismatch
      end
      
  | TVar a, _ -> unifyVar a t2
  | _, TVar b -> unifyVar b t1
  (* All other cases are mismatched types. *)
  | _, _ -> unif_error @@ UnifMismatch (t1, t2)

(* Unify a variable with a type *)
and unifyVar a t = match !a with
  | Some t' -> unify t' t
  | None ->
      begin
        match t with
        | TVar b -> (match !b with
            | Some x -> unify (TVar a) x
            | None -> if a == b then () else a := Some (TVar b))
        
        | _ ->
            if occurs a t then
              unif_error UnifOccursCheckFails
            else
              a := Some t
      end

(* Question 2: infer
   Copy your code for infer from hw10, and then adjust it to work with
   the new definition of exp in the prelude.
*)
let rec infer (ctx : context) (e : exp) : tp =
  match e with
  | Var x -> (match lookup x ctx with
      | Some r -> r
      | None -> type_error (FreeVariable x))
    
  | I _ -> Int
  | B _ -> Bool
  | Primop (po, exps) ->
      begin
        let (domain, range) =  primopType po in 
        let rec check exps ts = match exps, ts with
          | [], [] -> range
          | [], x -> type_error InvalidPrimop
          | x, [] -> type_error InvalidPrimop
          | e::es , t'::ts' -> unify (infer ctx e) t'; check es ts'
        
        in check exps domain
      end
  | If (e, e1, e2) -> (match infer ctx e with
      | Bool ->
          let a = infer ctx e1 in
          let b = infer ctx e2 in
          unify a b; a
    
      | TVar a1 ->
          unify (TVar a1) Bool;
          let a = infer ctx e1 in
          let b = infer ctx e2 in
          unify a b; a
    
      | x -> unify x Bool; Bool
    )
    
  | Fn (x, e) ->
      let a = TVar (ref None) in
      Arrow (a, infer (extend ctx (x, a)) e)
        
  | Apply (e1, e2) ->
      let t1 = infer ctx e1 in
      let t2 = infer ctx e2 in
      let a = TVar (ref None) in
      unify t1 (Arrow (t2, a)); a
      
  | Rec (x, e) ->
      let a = TVar (ref None) in
      let b = infer (extend ctx (x, a)) e in
      unify a b; a
      
  | Tuple es -> Product (List.map (fun x -> infer ctx x) es)
  | Let ([], e) -> infer ctx e
  | Let (dec::decs, e) -> 
      let infer_dec ctx dec =
        begin
          match dec with
          | Val (e, x) -> (x, infer ctx e)::ctx
          | Valtuple (e, nl) -> match (infer ctx e) with
            | Product e' -> (
                if (List.length e' <> List.length nl) then
                  unif_error @@ UnifProductMismatch
                else
                  List.map2 (fun e' x' -> (x', e')) e' nl
              ) @ ctx
                    
            | t -> unif_error @@ UnifMismatch (Product [], t) 
        end
      in
      let ctx' = infer_dec ctx dec in
      infer ctx' (Let (decs, e))
