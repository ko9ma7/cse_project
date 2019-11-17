C = gcc
CFLAGS = -g -Wall
OBJS1 = 9_1.o
OBJS2 = producer.o
OBJS3 = consumer.o
OBJS4 = 9_3.o

SRCS = $(OBJS1:.o=.c) $(OBJS2:.o=.c) $(OBJS3:.o=.c) $(OBJS4:.o=.c)

all: 9_1.out producer.out consumer.out  9_3.out

9_1.out: $(OBJS1)
	$(CC) -o $@ $(OBJS1)

producer.out: $(OBJS2)
	$(CC) -o $@ $(OBJS2)

consumer.out: $(OBJS3)
	$(CC) -o $@ $(OBJS3)

9_3.out: $(OBJS4)
	$(CC) -o $@ $(OBJS4)
