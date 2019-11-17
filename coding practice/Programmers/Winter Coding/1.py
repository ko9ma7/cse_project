import math
def help(s_w, s_h):
    half_w = s_w / 2
    half_h = s_h / 2
    print('half', half_w, half_h)
    a = 0

    if s_w % 2 == 0 and s_h % 2 != 0:
        a = math.ceil(half_w * half_h)
    else:
        a = math.floor(half_w * half_h)

    print('a', a)
    return a

def solution(w,h):
    answer = 0
    cnt = 0
    origin_w = w
    origin_h = h

    if w == h:
        answer = w*w - w
    else:
        while True:
            w = w / 2
            w_r = w % 2
            h = h / 2
            h_r = h % 2
            cnt += 1

            print(w, h, cnt)

            if type(w_r) != int or type(h_r) != int:
                small_w = w * 2
                small_h = h * 2
                print('s', small_w, small_h)

                sub_block = 2 * help(small_w, small_h)
                print('sub', sub_block)
                print('cnt', cnt)
                answer = origin_w*origin_h - cnt*sub_block
                break

    return answer

w = 2
h = 3
print(solution(w, h))