CC = gcc
CFLAGS = -g -Wall
OBJS1 = 5_1.o
OBJS2 = 5_2.o
OBJS3 = 5_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: 5_1.out 5_2.out 5_3.out

5_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

5_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

5_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
