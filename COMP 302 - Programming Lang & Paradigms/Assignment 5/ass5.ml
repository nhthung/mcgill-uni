(* ------------------------------------------------------------------------*)
(* Q 1 : Money in the bank (25 points)                                     *)
(* ------------------------------------------------------------------------*)

let new_account p =
  let balance = ref 0 in
  let pass = ref p in
  let counter = ref 0 in

  { update_passwd = (fun cur_pass new_pass ->
        if cur_pass = !pass then
          (pass := new_pass; counter := 0)
        else
          (counter := !counter + 1; raise wrong_pass)
      );

    deposit = (fun cur_pass amt ->
        match !counter < 3, cur_pass = !pass with
        | true, true -> balance := !balance + amt; counter := 0
        | true, false -> counter := !counter + 1; raise wrong_pass
        | false, _ -> raise too_many_attempts
      );

    retrieve = (fun cur_pass amt ->
        if !counter < 3 then
          match cur_pass = !pass, amt <= !balance with
          | true, true -> balance := !balance - amt; counter := 0
          | true, false -> counter := 0; raise no_money
          | false, _ -> counter := !counter + 1; raise wrong_pass
        else
          raise too_many_attempts
      );

    print_balance = (fun cur_pass ->
        match !counter < 3, cur_pass = !pass with
        | true, true -> counter := 0; !balance
        | true, false -> counter := !counter + 1; raise wrong_pass
        | false, _ -> raise too_many_attempts
      )
  }
;;


(* ------------------------------------------------------------------------*)
(* Q 2 : Memoization (75 points)                                           *)
(* ------------------------------------------------------------------------*)

(* Q 2.1 : Counting how many function calls are made *)

let rec num_recs n = match n with
  | 0
  | 1 -> 1
  | 2 -> 5
  | n -> (num_recs (n - 1)) * 3

let rec catalan_I n =
  let count = ref (num_recs n) in {num_rec = !count; result = catalan n}
;;


(* Q 2.2 : Memoization with a global store *)

let rec catalan_memo n =
  let rec catalan n =
    match Hashtbl.find_opt store n, n with
    | Some v, _ -> v
    | None, 0
    | None, 1 -> Hashtbl.add store n 1; 1
    | None, _ ->
        Hashtbl.add store n (sum (
            fun i -> catalan i * catalan (n - 1 - i)) (n - 1));
        sum (fun i -> catalan i * catalan (n - 1 - i)) (n - 1)
  in
  catalan n
;;


(* Q 2.3 : General memoization function *)

let memo f stats =
  let store = Hashtbl.create 1000 in
  let num_lkps = ref 0 in
  let num_entries = ref 0 in

  let rec g n = match Hashtbl.find_opt store n with
    | Some v ->
        num_lkps := !num_lkps + 1;
        stats.lkp := !num_lkps;
        v
    | None ->
        num_entries := !num_entries + 1;
        stats.entries := !num_entries;
        let v = f g n in
        Hashtbl.add store n v;
        v
  in g
;;

let memo f =
  let m = ref [] in
  fun x ->
    try
      List.assoc x !m
    with
      Not_found ->
        let y = f x in
        m := (x, y) :: !m ;
        y


(* Q 2.4 : Using memo to efficiently compute the Hofstadter Sequence Q *)

let hofstadter_Q n =
  raise NotImplemented
;;
