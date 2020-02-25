(* ---------------------------------------------------- *)

(* Processing finite objects lazily is also useful;
   it corresponds to demand driving compution.
*)
(* ---------------------------------------------------- *)
(* We define next a lazy list; this list is possibly
   finite; this is accomplished by a mutual recursive
   datatype.

   'a lazy_list defines a lazy list; we can observe the 
   head and its tail. For the tail we have two options:
   we have reached the end of the list indicated by the 
   constructor None or we have not reached the end 
   indicated by the constructor Some and we expose
   another lazy list of which we can observe the head and the tail.  

*)

(* ---------------------------------------------------- *)         
(* Q1 *)

(* 
   val take : int -> 'a lazy_list -> 'a list 
*)
let rec take n s = match n with
  | 0 -> []
  | n -> s.hd::(match force s.tl with
      | Some s' -> take (n-1) s'
      | None -> [] )


(* val map : ('a -> 'b) -> 'a lazy_list -> 'b lazy_list
*) let rec map (f: 'a -> 'b) (s : 'a lazy_list) : 'b lazy_list =
     {hd = f s.hd ; 
      tl = Susp (fun () -> (match force s.tl with
          | Some s' -> Some (map f s')
          | None -> None)) } 
     
(* 
  val append : 'a lazy_list -> ('a lazy_list) option susp -> 'a lazy_list
*) 
let rec append s1 s2 : 'a lazy_list = 
  {hd = s1.hd ;
   tl = Susp (fun () -> (match force s1.tl with
       | Some s' -> Some (append s' s2) 
       | None -> force s2 ))}


(* ---------------------------------------------------- *)
(* val interleave : 'a -> 'a list -> 'a list lazy_list *)
let rec interleave x l = 
  {hd = x::l ;
   tl = Susp (fun () -> match l with 
       | h::t -> Some (map (fun l -> h::l) (interleave x t))
       | [] -> None
     )}



(* ---------------------------------------------------- *)
(* val flatten : 'a lazy_list lazy_list -> 'a lazy_list = <fun>
*)
let rec flatten s = append s.hd (Susp (fun() -> match force s.tl with
    | Some s' -> Some (flatten s')
    | None -> None))


(* ---------------------------------------------------- *)
(* Permute *)
let rec permute l = match l with
  | [] -> {hd = [] ; tl = Susp (fun() -> None)}
  | h::t -> flatten (map (interleave h) (permute t))

(* ---------------------------------------------------- *)         
(* Q2 *)
                   
let rec hailstones n = 
  { hd = n ;
    tl = Susp (fun() -> Some (hailstones (if (n mod 2 = 0) then (n/2) 
                                          else ((3 * n) + 1 ))))}

    
    