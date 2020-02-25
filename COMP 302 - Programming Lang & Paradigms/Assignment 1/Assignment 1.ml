let square_root a =
  let rec findroot x acc =
    let rec x' =
      ((float a /. x) +. x) /. 2.0 in
    let rec diff =
      abs_float(x -. x') in
    if diff > acc then
      let rec x = x' in
      findroot x acc
    else x'
  in 
  if a > 0 then
    findroot 1.0 epsilon_float
  else
    raise Domain

square_root 4;;