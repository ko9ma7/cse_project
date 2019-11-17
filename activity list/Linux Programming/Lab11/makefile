CC = gcc
CFLAGS = -g -Wall
OBJS1 = 12_1_Server.o
OBJS2 = 12_1_Client.o
OBJS3 = 12_2.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: 12_1_Server.out 12_1_Client.out 12_2.out

12_1_Server.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

12_1_Client.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

12_2.out: $(OBJS3)
	$(CC) -pthread -o $@ $(OBJS3)
