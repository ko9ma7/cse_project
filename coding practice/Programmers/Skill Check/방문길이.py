# class Stack:
#     def __init__(self):
#         self.items = []
#
#     def isEmpty(self):
#         return self.items == []
#
#     def push(self, item):
#         self.items.append(item)
#
#     def pop(self):
#         return self.items.pop()
#
#     def peek(self):
#         return self.items[len(self.items) - 1]
#
#     def size(self):
#         return len(self.items)
#
# def solution(dirs):
#     stack = Stack()
#     position = [0, 0]
#     stack.push([position[0], position[1]])
#     for idx, d in enumerate(list(dirs)):
#
#         if d == 'U':
#             if position[0] > 5 or position[0] < -5 or position[1] > 5 or position[1] < -5:
#                 stack.pop()
#                 position[1] -= 1
#             else:
#                 position[1] += 1
#         elif d == 'D':
#             if position[0] > 5 or position[0] < -5 or position[1] > 5 or position[1] < -5:
#                 stack.pop()
#                 position[1] += 1
#             else:
#                 position[1] -= 1
#         elif d == 'R':
#             if position[0] > 5 or position[0] < -5 or position[1] > 5 or position[1] < -5:
#                 stack.pop()
#                 position[0] -= 1
#             else:
#                 position[0] += 1
#         elif d == 'L':
#             if position[0] > 5 or position[0] < -5 or position[1] > 5 or position[1] < -5:
#
#                 stack.pop()
#                 position[0] += 1
#             else:
#                 position[0] -= 1
#
#         stack.push([position[0], position[1]])
#
#     answer = []
#     while stack.isEmpty() != True:
#         s = stack.pop()
#         answer.append(s)
#     print(answer)
#
#     answer = list(set(map(tuple, answer)))
#     print(answer)
#     return len(answer)


def solution(dirs):
    routes = set()
    x, y = 0, 0
    for dir in dirs:
        dx = 1 if dir == "R" else (-1 if dir == "L" else 0)
        dy = 1 if dir == "U" else (-1 if dir == "D" else 0)
        new_x, new_y = x+dx, y+dy
        print(new_x, new_y)
        route = tuple(sorted([(new_x, new_y), (x, y)]))
        if abs(new_x) <= 5 and abs(new_y) <= 5:
            x, y = new_x, new_y
            routes.add(route)
            print(route)
            print(routes)

    return len(routes)

# print(solution('ULURRDLLU'))
print(solution('LULLLLLLU'))