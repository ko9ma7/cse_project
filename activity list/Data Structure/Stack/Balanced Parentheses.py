from pythonds3 import Stack

def parChecker(symbolString):
    s = Stack()
    balanced = True
    index = 0

    while index < len(symbolString) and balanced:
        symbol = symbolString[index]
        # '(', '[', '{'인 경우는 stack에 push
        if symbol in '([{':
            s.push(symbol)
        else:
            if s.is_empty():
                balanced = False
            # ')', ']', '}'인 경우는 stack에서 pop
            else:
                top = s.pop()
                if not matches(top, symbol):
                    balanaced = False

        index += 1

    # 스택에 아무것도 남지 않으면서 balanced가 True로 유지된다면 parentheses가 되므로
    if balanced and s.is_empty():
        return True
    else:
        return False

def matches(open, close):
    opens = '([{'
    closes = ')]}'
    return opens.index(open) == closes.index(close)

print(parChecker('({([])})'))
print(parChecker('[([([{]]))}))'))