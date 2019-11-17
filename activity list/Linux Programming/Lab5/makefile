CC = gcc
CFLAGS = -g -Wall
OBJS1 = 6_1.o
OBJS2 = 6_2.o 
OBJS3 = 6_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: 6_1.out 6_2.out 6_3.out

6_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1) -lm

6_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

6_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
