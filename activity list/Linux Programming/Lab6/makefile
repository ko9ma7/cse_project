CC = gcc
CFLAGS = -g -Wall
OBJS1 = 7_1.o
OBJS2 = 7_2.o
OBJS3 = 7_3.o
OBJS4 = Copy.o
OBJS5 = Decrypt.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c) $(OBJS4:.o=.c) $(OBJS5:.o=.c)

all: 7_1.out 7_2.out 7_3.out Copy.out Decrypt.out

7_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

7_2.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

7_3.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)

Copy.out: $(OBJS4)
	$(CC) -o $@ $(OBJS4)

Decrypt.out: $(OBJS5)
	$(CC) -o $@ $(OBJS5)
