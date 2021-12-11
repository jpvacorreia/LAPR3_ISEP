.section .data # section identifier ( data )

.section .text # section identifier ( text )
.global estimate


estimate : # function start

# prologue
pushl %ebp # save previous stack frame pointer
movl %esp , %ebp # the stack frame pointer for our function
pushl %esi
pushl %edi
pushl %ebx
# program
movl 8(%ebp), %ebx	  #Percentagem da bateria
movl 12(%ebp), %ecx   #Capacidade(Wh) da bateria
movl 16(%ebp), %esi	  #Capacidade(W) output do spot
movl $100, %eax

subl %ebx, %eax
mull %ecx
divl %esi

popl %ebx
popl %edi
popl %esi
# epilogue
movl %ebp , %esp # restore the stack pointer (" clear " the stack )
popl %ebp # restore the stack frame pointer
ret # return from the function
