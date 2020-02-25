# Calculate days alive througe age

	.data			# Data segment
YEARS:	.word 2			# .word YEARS = 2;
DAYS:	.word			# .word DAYS;

	.text			# Code segment
main:	
	lw   $s0, YEARS		# $s0 = YEARS;
	li   $s1, 0		# $s1 = 0;
	li   $t0, 0		# $t0 = 0;
	
while:	beq  $t0, $s0, exit	# while ( $t0 != $s0 ) {
	addi $s1, $s1, 365	# 	$s1 += 365;
	addi $t0, $t0, 1	#	$t0++;
	j    while		# }
	
exit:	sw   $s1, DAYS		# DAYS = $s1;
	
	li   $v0, 10		# Tell OS to use library function 10 (exit program)
	syscall			# Call the library function
