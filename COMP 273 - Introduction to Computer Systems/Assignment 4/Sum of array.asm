# Le, Nhat Hung - 260793376

	.data
array:	.word 0,0,0,0,0,0,0,0,0,0
prompt:	.asciiz "enter a number: "
result: .asciiz "the sum is "

	.text
	.globl Main
Main:
	la $t2, array
	addi $t0, $zero, 0	# int i = 0;
	
For:	la $a0, prompt		# for( i = 0; i < 10; i++ ) {
	li $v0, 4
	syscall			# 	printf("enter a number: ");
	
	li $v0, 5
	syscall
	
	add $t1, $t0, $t0
	add $t1, $t1, $t1
	add $t1, $t1, $t2
	
	sw $v0, 0($t1)		# 	scanf("%d", &array[i]);
	
	addi $t0, $t0, 1
	bne $t0, 10, For	# }
	
	add $a0, $zero, $t2	# array is $a0
	add $a1, $zero, $t1	# array + 10 is $a1
	
	jal Summation
	
	add $a0, $zero, $v0
	li $v0, 1
	syscall

	li $v0, 10
	syscall
	
	
Summation:
	addi $v0, $zero, 0	# int result = 0;
	
	subi $sp, $sp, 8
	sw $ra, 4($sp)
	sw $a0, 0($sp)
	
	bne $a0, $a1, L1	# if (a != last)

	jr $ra
	
L1:	addi $a0, $a0, 4
	jal Summation
	
	lw $t0, 0($sp)
	lw $ra, 4($sp)
	addi $sp, $sp, 8
	
	lw $t1, 0($t0)
	add $v0, $v0, $t1	# 	result = Summation(a + 1, last) + *a
	jr $ra
