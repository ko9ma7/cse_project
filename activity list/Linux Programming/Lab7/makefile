CC = gcc
CFLAGS = -g -Wall
OBJS1 = 8_1.o
OBJS2 = 8_2.o
OBJS3 = 8_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: 8_1.out 8_2.out 8_3.out

8_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

8_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

8_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
