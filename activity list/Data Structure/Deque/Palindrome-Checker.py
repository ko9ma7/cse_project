from pythonds3 import Deque

def palchecker(aString):
    chardeque = Deque()

    for ch in aString:
        chardeque.add_rear(ch)

    stillEqual = True
    while chardeque.size() > 1 and stillEqual:
        first = chardeque.remove_front()
        last = chardeque.remove_rear()
        if first != last:
            stillEqual = False

    return stillEqual

print(palchecker("lsdkjfskf"))
print(palchecker("radar"))