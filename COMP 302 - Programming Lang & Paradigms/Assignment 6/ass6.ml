let rec parseExp toklist = match toklist with
  | [] -> raise (Error "Expected an expression: Nothing to parse")
  | _ ->
      try parseSExp toklist
      with
      | SumExpr (exp, [SEMICOLON]) -> exp
      | _ -> raise (Error "Expected a single semicolon")


and parseSExp toklist =

  let count = ref 0 in
  let rec par prev rest =
    match rest with
    |PLUS::t ->
        (count := 0;
         (try parseSExp t
          with
          | SumExpr (exp, tok) -> raise ( SumExpr( Sum(prev, exp), tok ) )
         ))

    |SUB::t ->
        (count := 0;
         (try parseSExp t
          with
          | SumExpr (exp, tok) -> raise ( SumExpr( Minus(prev, exp), tok ) )
         ))

    |h::t -> (if !count = 0 then (count := 1;
                                  (try parsePExp (h::t)
                                   with
                                   | ProdExpr (exp, tok) -> par exp tok
                                  ))
              else raise ( SumExpr( prev, rest ) )
             )
    |[] -> raise ( SumExpr(prev, []) )

  in
  par (Int(0)) toklist


and parsePExp toklist =
  let count = ref 0 in
  let rec par prev rest =
    match rest with
    |TIMES::t ->
        (count := 0;
         (try parsePExp t
          with
          | ProdExpr (exp, tok) -> raise ( ProdExpr( Prod(prev, exp), tok ) )
         ))

    |DIV::t ->
        (count := 0;
         (try parsePExp t
          with
          | ProdExpr (exp, tok) -> raise ( ProdExpr( Div(prev, exp), tok ) )
         ))

    |h::t -> (if !count = 0 then (count := 1;
                                  (try parseAtom (h::t)
                                   with
                                   | AtomicExpr (exp, tok) -> par exp tok
                                  ))
              else raise ( ProdExpr( prev, rest ) )
             )
    |[] -> raise ( ProdExpr(prev, []) )

  in
  par (Int(0)) toklist


and parseAtom toklist =

  (match toklist with
   |INT(i)::x -> raise ( AtomicExpr (Int(i), x) )
   |LPAREN::tok -> (try parseSExp tok
                    with
                    | SumExpr(exp, RPAREN::t) -> raise ( AtomicExpr(exp, t) )
                    | SumExpr(exp, x) -> raise ( AtomicExpr(exp, x))

                   )
   |_ -> raise NotImplemented
  )
;;


(* parse : string -> exp *)
let parse string =
  parseExp (lex string) ;;

(* eval : string -> int *)
let eval e = eval' (parse e) ;;
