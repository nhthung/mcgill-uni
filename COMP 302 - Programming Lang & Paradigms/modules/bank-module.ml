(* The power of abstraction and information hiding *)

module type CURRENCY = 
sig 
  type t 
  val unit : t 
  val plus : t -> t -> t 
  val prod : float -> t -> t 
  val toString : t -> string
end;;

module Float = 
struct 
  type t = float 
  let unit = 1.0 
  let plus = (+.) 
  let prod = ( *. ) 
  let toString  x = string_of_float x
end;;

(* Abstraction may also be used to produce two isomorphic but
   incompatible views of a same structure. For instance, all
   currencies are represented by floats; however, all currencies are
   certainly not equivalent and should not be mixed. Currencies are
   isomorphic but disjoint structures, with respective incompatible
   units Euro and Dollar. This is modeled in OCaml by a signature
   constraint / module type. *)  

(* Remark that multiplication became an external operation on floats
   in the signature CURRENCY. Constraining the signature of Float to
   be CURRENCY returns another, incompatible view of Float. Moreover,
   repeating this operation returns two isomorphic structures but with
   incompatible types t. *) 

module Euro = (Float : CURRENCY);; 
module Dollar = (Float : CURRENCY);;
 	
(* In Float the type t is concrete, so it can be used for
   "float". Conversely, it is abstract in modules Euro and
   Dollar. Thus, Euro.t and Dollar.t are incompatible.  
 
# let euro x = Euro.prod x Euro.unit;; 
val euro : float -> Euro.t = <fun>
# let x = Euro.plus (euro 10.0) (euro 20.0);;
val x : Euro.t = <abstr>
# Euro.toString x;;
- : string = "30."
# Euro.toString (Euro.plus (euro 10.0) (Dollar.unit));;
Characters 37-50:
  Euro.toString (Euro.plus (euro 10.0) (Dollar.unit));;
                                       ^^^^^^^^^^^^^
Error: This expression has type Dollar.t
       but an expression was expected of type Euro.t
# 

NOTE: We cannot mix units! This is a good thing!

*)

(* Let us now define a bank with clients generically - 
   it can be re-used in Europe, Canada, US, ...
   the concrete currency is opaque.
*)
module type CLIENT = (* client's view *)
sig 
  type t 
  type currency 
  val deposit : t -> currency -> currency 
  val retrieve : t -> currency -> currency 
end;; 

module type BANK = (* banker's view *) 
sig 
  include CLIENT   (* BANK inherits all the values declared in the
		      signature CLIENT ; 
		      if we subsequently declare names which already
		      are declared in CLIENT we are overshadowing the
		      previous names.
		   *)
  val create : unit -> t 
end;;

(* Functor *)
module Old_Bank (M : CURRENCY) : (BANK with type currency = M.t) = 
struct 
  type currency = M.t 
  type t = { mutable balance : currency } 

  let zero = M.prod 0.0 M.unit 
  and neg = M.prod (-1.0) 
  
  let create() = { balance = zero } 

  let deposit c x = 
    if x > zero then 
      c.balance <- M.plus c.balance x; 
      c.balance 
  
  let retrieve c x = 
    if c.balance > x then 
      deposit c (neg x) 
    else 
      c.balance 
end;; 


(* Illustrating inheritance and code reuse *)
module Post = Old_Bank (Euro);; 

module Client : (CLIENT with type currency = Post.currency and type t = Post.t) = Post;;

(*
 This model is fragile because all information lies in the account itself. For instance, if the client loses his account, he loses his money as well, since the bank does not keep any record. Moreover, security relies on type abstraction to be unbreakable…

However, the example already illustrates some interesting benefits of modularity: the clients and the banker have different views of the bank account. As a result an account can be created by the bank and used for deposit by both the bank and the client, but the client cannot create new accounts. 
*)

(*     	 	
let my_account = Post.create ();; 
Post.deposit my_account (euro 100.0); 
Client.deposit my_account (euro 100.0);;


(* Moreover, several accounts can be created in different currencies, with no possibility to mix one with another, such mistakes being detected by typechecking.
*)
  	 	
module Citybank = Old_Bank (Dollar);; 
let my_dollar_account = Citybank.create();;

Citybank.deposit my_account;; 
Citybank.deposit my_dollar_account (euro 100.0);;

(* Furthermore, the implementation of the bank can be changed while preserving its interface. We use this capability to build, a more robust —yet more realistic— implementation of the bank where the account book is maintained in the bank database while the client is only given an account number. *)
    	 	
module Bank (M : CURRENCY) : (BANK with type currency = M.t) = 
struct 
  let zero = M.prod 0.0 M.unit 
  and neg = M.prod (-1.0) 
  
  type t = int 
  type currency = M.t 
  type account = { number : int; mutable balance : currency } (* bank database *) 

  let all_accounts = Hashtbl.create 10 
  and last = ref 0 

  let account n = Hashtbl.find all_accounts n 

  let create() = 
    let n = incr last; !last 
    in 
    Hashtbl.add all_accounts n {number = n; balance = zero}; 
    n 

  let deposit n x = 
    let c = account n in 
    if x > zero then 
      c.balance <- M.plus c.balance x; 
      c.balance 

  let retrieve n x = 
    let c = account n in 
    if c.balance > x then (c.balance <- M.plus c.balance x; x) else zero 

end;; 

(* Using functor application we can create several banks. As a result of generativity of function application, they will have independent and private databases, as desired. *)
    	 	
module Central_Bank = Bank (Euro);; 
module Banque_de_France = Bank (Euro);;

(* Furthermore, since the two modules Old_bank and Bank have the same interface, one can be used instead of the other, so as to created banks running on different models.
*)
 	 	
module Old_post = Old_Bank(Euro) 
module Post = Bank(Euro) 
module Citybank = Bank(Dollar);;

(* All banks have the same interface, however they were built. In fact, it happens to be the case that the user cannot even observe the difference between either implementation; however, this would not be true in general. Indeed, such a property can not be enforced by the typechecker. *)
*)
