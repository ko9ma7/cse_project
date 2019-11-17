CC = gcc
CFLAGS = -g -Wall
OBJS1 = Client.o
OBJS2 = Server.o
OBJS3 = 10_X.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c)

all: Client.out Server.out 10_X.out

Client.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

Server.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

10_X.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)
