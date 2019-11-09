def solution(board, moves):
    basket = []
    cnt = 0

    for i in range(len(moves)):
        for j in range(len(board)):
            doll = board[j][moves[i] - 1]
            if doll != 0:
                board[j][moves[i] - 1] = 0
                # 바스켓에 들어오려는 인형이 바스켓에 들어있는 인형이랑 같은 경우
                if len(basket) != 0:
                    if basket[len(basket) - 1] == doll:
                        basket.pop()
                        cnt += 2
                        break
                basket.append(doll)
                break

    return cnt