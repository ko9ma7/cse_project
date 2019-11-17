CC = gcc
CFLAGS = -g -Wall
OBJS1 = 4_1.o 
OBJS2 = 4_2.o 
OBJS3 = 4_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: 4_1.out 4_2.out 4_3.out

4_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)
		
4_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

4_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
