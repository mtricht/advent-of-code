from collections import Counter


def is_in_bounds(lines, line_index, char_index, offsets):
    for i, j in offsets:
        if not (0 <= line_index + i < len(lines)) or not (
            0 <= char_index + j < len(lines[line_index + i])
        ):
            return False
    return True


def get_sequence(lines, line_index, char_index, offsets):
    return "".join(lines[line_index + i][char_index + j] for i, j in offsets)


with open("input") as f:
    lines = f.read().splitlines()
    directions = {
        "horizontal_right": [(0, 0), (0, 1), (0, 2), (0, 3)],
        "horizontal_left": [(0, 0), (0, -1), (0, -2), (0, -3)],
        "vertical_down": [(0, 0), (1, 0), (2, 0), (3, 0)],
        "vertical_up": [(0, 0), (-1, 0), (-2, 0), (-3, 0)],
        "diagonal_down_right": [(0, 0), (1, 1), (2, 2), (3, 3)],
        "diagonal_down_left": [(0, 0), (1, -1), (2, -2), (3, -3)],
        "diagonal_up_right": [(0, 0), (-1, 1), (-2, 2), (-3, 3)],
        "diagonal_up_left": [(0, 0), (-1, -1), (-2, -2), (-3, -3)],
    }

    all_possibilities = []
    for line_index, line in enumerate(lines):
        for char_index, char in enumerate(line):
            for offsets in directions.values():
                if is_in_bounds(lines, line_index, char_index, offsets):
                    sequence = get_sequence(lines, line_index, char_index, offsets)
                    all_possibilities.append(sequence)

    print(Counter(all_possibilities)["XMAS"])
