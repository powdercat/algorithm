T = int(input())
for test_case in range(1, T + 1):
    # A B C 중에 짝수가 있다면 -> 1이 이김
    # 전부 다 홀수라면 -> 2가 이김
    A, B, C = map(int, input().split())
    if (A % 2) * (B % 2) * (C % 2) == 1:
        print(2)
    else:
        print(1)