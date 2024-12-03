from collections import Counter


def distance_between_lists(lists):
    first_list = sorted([numbers[0] for numbers in lists])
    second_list = sorted([numbers[1] for numbers in lists])
    return sum(abs(item[0] - item[1]) for item in zip(first_list, second_list))


def similarity_between_lists(lists):
    counter = Counter([numbers[1] for numbers in lists])
    return sum(num * counter[num] for num in [numbers[0] for numbers in lists])


with open("input") as f:
    lines = f.read().splitlines()
    lists = [tuple(map(int, line.split())) for line in lines]
    print("total distance: {}".format(distance_between_lists(lists)))
    print("total similarity: {}".format(similarity_between_lists(lists)))
