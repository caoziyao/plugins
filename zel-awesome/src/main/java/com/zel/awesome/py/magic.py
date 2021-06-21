# coding: utf-8

def magic(raw):
    stack = [[]]
    result = None
    for c in raw[1:]:
        if c == '[':
            current = stack[-1]
            tmp = []
            current.append(tmp)
            stack.append(tmp)
        elif c == ']':
            print('data', stack)
            result = stack.pop()
        else:
            current = stack[-1]
            current.append(c)
    return result


if __name__ == '__main__':
    raw = '[[a , b]]'

    result = magic(raw)

    print('rrr', result)