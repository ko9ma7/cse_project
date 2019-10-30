def solution(skill, skill_trees):
    answer = 0
    for skill_tree in skill_trees:
        if isPossible(skill, skill_tree):
            answer += 1
    return answer

def isPossible(skill, skill_tree):
    tmp = ''
    for s in skill_tree:
        if list(skill).__contains__(s):
            tmp += s

    if skill.startswith(tmp):
        print('시작')
        return True
    return False