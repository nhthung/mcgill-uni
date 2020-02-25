for( int i = 0; i < height; i++ ) {

  System.out.print("*");

  for( int j = 0; j < width - 2; j++ ) {
    if ( i == 0 || i == height - 1)
      System.out.print("*");

    else
      System.out.print(" ");
  }

  System.out.println("*");
}
