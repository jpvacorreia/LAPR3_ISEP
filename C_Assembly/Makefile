# estimate/Makefile 

estimateExecute: estimate.o  main.o 
	gcc -Wall estimate.o main.o -o estimateExecute

estimate.o: estimate.s 
	gcc -g -Wall -c estimate.s -o estimate.o 

main.o: main.c
	gcc -g -Wall -c main.c -o main.o

run: estimateExecute
	./estimateExecute
