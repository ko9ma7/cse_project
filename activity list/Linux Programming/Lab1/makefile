CC = gcc
CFLAGS = -g -Wall
OBJS1 = Lab2_1.o 
OBJS2 = Lab2_2.o 
OBJS3 = Lab2_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: Lab2_1.out Lab2_2.out Lab2_3.out

Lab2_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)
		
Lab2_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

Lab2_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
