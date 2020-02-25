type stats = { entries : int ref; lkp : int ref; }

let memo f stats =
  let store = Hashtbl.create 1000 in
  let num_lkps = ref 0 in
  let num_entries = ref 0 in 
  
  let rec g n = 
    match Hashtbl.find_opt store n with
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

let memo2 f =
  let store = Hashtbl.create 1000 in
  
  let rec g n = 
    match Hashtbl.find_opt store n with
    | Some v -> v
    | None ->
        let v = f g n in
        Hashtbl.add store n v;
        v
  in g
;;

let hQ self = function
  | 1
  | 2 -> 1
  | n -> self (n - self(n-1)) + self (n - self(n-2))
;;

let add global_stats local_stats =
  global_stats.entries := !(global_stats.entries) + !(local_stats.entries);
  global_stats.lkp := !(global_stats.lkp) + !(local_stats.lkp);
  global_stats
;;

let hofstadter_Q =
  let stats = {entries = ref 0; lkp = ref 0} in
 
  function n ->
    let local = {entries = ref 0; lkp = ref 0} in
    let res = memo hQ local n in
    (res, add stats local);;
;;

let hQ2 =
  function n ->
    let stats = {entries = ref 0; lkp = ref 0} in
    let res = memo hQ stats n in
    (res, stats)
;;
