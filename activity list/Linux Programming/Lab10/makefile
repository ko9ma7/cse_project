CC = gcc
CFLAGS = -g -Wall
OBJS1 = 11_1.o
OBJS2 = 11_2.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c)

all: 11_1.out 11_2.out

11_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

11_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)
