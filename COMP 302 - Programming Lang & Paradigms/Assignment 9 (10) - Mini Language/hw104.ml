(***** Question 1a: unused variables *****)

let rec unused_vars e = match e with
  | Var _ | I _ | B _ -> []
  | If (e, e1, e2) ->
      union (unused_vars e, union (unused_vars e1, unused_vars e2))
  | Primop (po, args) ->
      List.fold_right (fun e1 e2 -> union (unused_vars  e1, e2)) args []
  | Apply (e1, e2) -> union (unused_vars e1, unused_vars e2)
  | Fn (x, _, e) | Rec (x, _, e) ->
      if member x (freeVariables e) then
        unused_vars e
      else
        union ([x], unused_vars e)

  | Tuple exps -> List.fold_right (fun e1 e2 -> union (unused_vars e1, e2)) exps []

  | Let ([], e) -> unused_vars e

  | Let (Val (e,x)::decs, e2) -> union (delete (freeVariables e2) (union ([x], unused_vars e)), unused_vars (Let (decs, e2)))

  | Let (Valtuple (e,xl)::decs, e2) ->  union (delete (freeVariables e2) (union (xl, unused_vars e)) , unused_vars (Let (decs, e2)))

(* Question 1b: write your own tests for unused_vars *)

let unused_vars_tests : (exp, name list) tests =
  [ ( Fn ("x", Int, Tuple [ Var "x"; Var "x" ] )
    , []
    ); 
    ((Let ([Val (Var "x", "x")], Primop (Plus, [I 3; I 5])))
    , ["x"]
    ); 
    ( Rec ("x", Int, Tuple [ Var "x"; Var "x" ] ) , [] );
    ( Tuple ([ Var "x"; Var "x" ]), []); 
    ( Let ([], (Var "x")), []);

  ]

(* Question 2a: substitution *)

(** Applies the substitution s to each element of the list a. *)
let rec substArg s a = List.map (subst s) a

(** Applies the substitution (e', x), aka s, to exp.
    To implement some of the missing cases, you may need to use the
    `rename` function provided below. To see why, see the comment
    associated with `rename`.
*)
and subst ((e',x) as s) exp =  match exp with
  | Var y ->
      if x = y then e'
      else Var y
  | I n -> I n
  | B b -> B b
  | Primop(po, args) -> Primop(po, substArg s args)
  | If(e, e1, e2) ->
      If(subst s e, subst s e1, subst s e2)
  | Apply (e1, e2) -> Apply (subst s e1, subst s e2)
  | Fn (y, t, e) ->
      if y = x then
        Fn (y, t, e)
      else
      if member y (freeVariables e') then
        let (y,e1) = rename (y,e) in
        Fn (y, t, subst s e1)
      else
        Fn(y, t, subst s e)
  | Rec (y, t, e) ->
      if y = x then
        Rec (y, t, e)
      else
      if member y (freeVariables e') then
        let (y, e1) = rename (y,e) in
        Rec (y, t, subst s e1)
      else
        Rec (y, t, subst s e)

  | Tuple es -> Tuple (List.map (fun e -> subst s e) es)

  | Let([], e2) -> Let ([], subst s e2)

  | Let(dec1::decs, e2) ->
      (match dec1 with
       | Val(exp, name) -> let exp' = subst s exp in
           
           if name = x 
           then 
             (Let ((Val (exp', name))::decs, e2))
           else
             (
               if member name (freeVariables e') then
                 let (y, e4) = rename (name, Let(decs, e2)) in
                 match subst s e4 with 
                 | Let (l, expr) -> Let ((Val (exp', y))::l, expr)
               else
                 (
                   match subst s (Let (decs, e2)) with 
                   | Let (l, expr) -> Let ((Val (exp', name))::l, expr)
                 )
             )

       | Valtuple(exp, names) -> 
           if member x names
           then 
             Let ((Valtuple (subst s exp, names))::decs, e2)
           else
             let (ys, e4) = renameList names e' (Let (decs, e2)) in
             match subst s e4 with 
             | Let (l, expr) -> Let ((Valtuple (subst s exp, ys))::l, expr)
      )

and substList l e = match l with
  | [] -> e
  | (x,e')::pairs ->
      subst (x,e') (substList pairs e)

(** Replaces the variable x with a fresh version of itself.

    This is an important operation for implementing substitutions that
    avoid capture.
    e.g. If we naively compute [y/x](fun y => x) we get (fun y => y),
    but this doesn't have the right interpretation anymore; the
    variable y that we plugged in got "captured" by the `fun y`
    binder.
    The solution is to observe that the variable y introduced by the
    fun-expression *appears free* in the expression we are substituting for x.
    In this case, we must rename the bound variable y (introduced by
    `fun y`).
    e.g We want to compute [y/x](fun y => x). We first rename the
    bound variable y, and instead compute [y/x](fun y1 => x).
    This gives (fun y1 => y), which has the right interpretation.
*)
and rename (x, e) =
  let x' = freshVar x in
  (x', subst (Var x', x) e)

(** Performs multiple renamings on the same expression. *)
and renameAll e = match e with
  | ([], e) -> ([], e)
  | (x::xs, e) ->
      let (x', e) = rename (x, e) in
      let (xs', e) = renameAll (xs, e) in
      (x' :: xs', e)

and renameList names e' exp =
  if List.exists (fun name -> member name (freeVariables e')) names then
    renameAll(names, exp)
  else
    (names, exp)

(* Question 2b: write your own tests for subst *)

let subst_tests : (subst * exp, exp) tests =
  [ ( ( (Var "x", "y"), Tuple [ Var "y" ] )
    , Tuple [ Var "x" ]
    ); 
    (((I 8, "x"), (Let ([Val (Var "x", "x")], Primop (Plus, [Var "x"; I 5]))))
    ,(Let ([Val (I 8, "x")], Primop (Plus, [Var "x"; I 5]))) 
    );
    (((I 8, "y"), (Let ([Val (Var "x", "")], Primop (Plus, [Var "y"; I 5]))))
    ,(Let ([Val (Var "x", "")], Primop (Plus, [I 8; I 5]))) 
    );
    (((Var "x", "y"), (Let ([Val (I 7, "x"); Val (I 9, "x")], Primop (Plus, [Var "y"; Var "x"]))))
    ,(Let ([Val (I 7, "1x"); Val (I 9, "2x")], Primop (Plus, [Var "x"; Var "2x"]))) 
    );
    (((Var "x", "y"), (Let ([Valtuple (I 7, ["x"; "z"])], Primop (Plus, [Primop (Plus, [Var "y"; Var "x"]); Var "z"])))),
     (Let ([Valtuple (I 7, ["1x"; "z"])], Primop (Plus, [Primop (Plus, [Var "x"; Var "1x"]); Var "z"])))
    );
  ]

(* Question 3a: evaluation *)

let rec evalList (exps : exp list) =
  List.map eval exps

and eval (exp : exp) : exp = match exp with
  (* Values evaluate to themselves *)
  | I _ -> exp
  | B _ -> exp
  | Fn _ -> exp

  (* This evaluator is _not_ environment-based. Variables should never
  appear during evaluation since they should be substituted away when
  eliminating binding constructs, e.g. function applications and lets.
  Therefore, if we encounter a variable, we raise an error.
*)
  | Var x -> raise (Stuck ("Free variable (" ^ x ^ ") during evaluation"))

  (* primitive operations +, -, *, <, = *)
  | Primop(po, args) ->
      let argvalues = evalList args in
      (match evalOp(po, argvalues) with
       | None -> raise (Stuck "Bad arguments to primitive operation")
       | Some v -> v)

  | If(e, e1, e2) ->
      (match eval e with
       | B true -> eval e1
       | B false -> eval e2
       | _ -> raise (Stuck "Scrutinee of If is not true or false"))

  | Rec (f, _, e) -> eval (subst (exp, f) e)
  | Apply (e1, e2) ->
      (match eval e1 with
       | Fn(x,_,e) -> eval (subst (e2,x) e)
       | _ -> raise (Stuck "Left term of application is not an Fn"))

  | Tuple es -> Tuple (List.map (fun x -> eval x) es)
  | Let([], e2) -> eval e2
  | Let(dec1::decs, e2) ->
      (match dec1 with
       | Val(e1, x) -> eval (subst (eval e1, x) (Let (decs, e2)))
       | Valtuple(e1, xs) -> (
           match eval e1 with 
           | Tuple es -> eval (substList (List.combine es xs) (Let (decs, e2)))
           | _ -> raise (Stuck "NM$L")
         )
      )

(* Question 3b: write your own tests for eval *)

let eval_tests : (exp, exp) tests =
  [(Let([Val(Primop(Plus, [I 3; I 2]), "x")],
        Let([Val( Primop(Plus, [I 1; Var "x"]),"y"); 
             Val(Primop(Plus, [Var "x"; Var "y"]),"x")], 
            Let([Valtuple(Tuple[],[])], Primop(Plus, [Var "x"; Var "y"])))), I 17);
   (Let([Val(Primop(Plus, [I 3; I 2]), "x")], 
        Primop(Plus, [I 1; Var "x"])), I 6);
   (Let([Val(Primop(Plus, [I 4; I 2]), "x")], 
        Primop(Plus, [I 1; Var "x"])), I 7);
   (Let([(Valtuple(Tuple([Primop(Plus, [I 3; I 2]); I 1]), ["y";"x"]));
         Val(Primop(Plus, [I 3; I 2]), "x")], 
        Primop(Plus, [Var "y"; Var "x"])), I 10);
   (Tuple([Primop(Times, [I 3; I 2]); 
           Primop(Equals, [I 3; I 2])]), Tuple([I 6; B false]))
  ]

(* Question 4a: type inference *)

let rec infer ctx e : tp = match e with
  | Var x -> (try lookup x ctx
              with NotFound -> raise (TypeError ("Found free variable")))
  | I _ -> Int
  | B _ -> Bool
  | Primop (po, exps) ->
      let (domain, range) =  primopType po in
      let rec check exps ts = match exps , ts with
        | [] , [] -> range
        | e::es , t::ts ->
            let t' = infer ctx e in
            if t' = t then check es ts
            else type_mismatch t t'
      in
      check exps domain

  | If (e, e1, e2) ->
      (match infer ctx e with
       | Bool -> let t1 = infer ctx e1 in
           let t2 = infer ctx e2 in
           if t1 = t2 then t1
           else type_mismatch t1 t2
       | t -> type_mismatch Bool t)

  | Fn (x,t,e) -> Arrow(t, infer (extend ctx (x,t)) e)

  | Apply (e1, e2) -> (
      match infer ctx e1 with
      | Arrow (t1, t2) -> ( let it2 = (infer ctx e2) in 
                            if t1 = it2 then t2 
                            else type_mismatch t1 it2 
                          )
      | t -> type_mismatch (infer ctx e2) t
    )
  | Rec (f, t, e) -> ( let infrt = infer (extend ctx (f, t)) e in 
                       if infrt = t then t
                       else type_mismatch t infrt
          
                     )
  | Tuple es -> Product (List.map (fun x -> infer ctx x) es)
  | Let ([], e) -> infer ctx e
  | Let (dec::decs, e) ->
      let ctx' = infer_dec ctx dec  in
      infer ctx' (Let(decs, e))

(** Extends the context with declarations made in Val or Valtuple. *)
and infer_dec ctx dec = match dec with
  | Val (e, x) -> extend ctx (x, (infer ctx e))
  | Valtuple (e, nl) -> (
      match infer ctx e with
      | Product ets -> extend_list ctx (List.combine nl ets) 
    )

(* Question 4b: write your own tests for infer *)

let infer_tests : (context * exp, tp) tests =
  [(([], Tuple [ I 3; I 3 ]), Product [ Int; Int ]);
   (([], Rec("x", Int,  Primop(Plus, [Var "x"; I 2]))), Int);
   (([], Tuple [ I 2; I 4 ]), Product [ Int; Int ]);
   (([], Let([Valtuple(Tuple([B false; B true]),["x"; "y"])], I 1)), Int);
   (([], Let([Valtuple(Tuple([B false; B true]),["x"; "y"])], Tuple([Var "x"; Var "y"]))), Product([Bool; Bool]))
  ]
