type seasoning =
  | Salt
  | Pepper

type 'a kebab =
  | Bottom of 'a
  | Onion of 'a kebab
  | Lamb of 'a kebab
  | Tomato of 'a kebab

type rod = Sword | Skewer | Dagger

type plate = Large | Small

(* Is a kebab vegetarian? *)
let rec veg k = match k with
  | Bottom b -> true
  | Onion r -> veg r
  | Tomato r -> veg r
  | Lamb r -> false

let rec botof = function
  | Bottom b -> b
  | Onion r -> botof r
  | Tomato r -> botof r
  | Lamb r -> botof r

(* Top Lamb with Onion (not tail recursive) *)
let rec tlwo k = match k with
  | Bottom b -> Bottom b
  | Onion r -> Onion (tlwo r)
  | Tomato r -> (tlwo r)
  | Lamb r -> Onion (Lamb (tlwo r))

(* First attempt at tail-recursive *)
(* let tr_tlwo k =
 *   let rec f acc keb = match keb with
 *     | Bottom b -> acc
 *     | Onion k -> f (Onion (acc)) k
 *     | Lamb k -> f (Onion (Lamb (acc))) k
 *     | Tomato k -> f (Tomato (acc)) k
 *   in
 *   f (Bottom b) k *)

(* Functionning, tail-recursive 'Top Lamb with Onion' *)
(* Explanations in the follow-up document *)
let tr_tlwo k =
  let rec rev k = function
    | Bottom _ -> k
    | Onion acc' -> rev (Onion k) acc'
    | Lamb acc' -> rev (Lamb k) acc'
    | Tomato acc' -> rev (Tomato k) acc'
  in
  let rec f acc = function
    | Bottom b -> rev (Bottom b) acc
    | Onion k -> f (Onion (acc)) k
    | Lamb k -> f (Lamb (Onion (acc))) k
    | Tomato k -> f (Tomato (acc)) k
  in
  f (Bottom 0) k


