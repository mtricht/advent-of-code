import re


with open("input") as f:
    lines = f.read()
    pattern = re.compile(r"mul\((\d{1,3}),(\d{1,3})\)")
    matches = pattern.findall(lines)
    print(sum(int(x) * int(y) for x, y in matches))
    
    pattern = re.compile(r"do(?:n't)?\(\)|mul\((\d{1,3}),(\d{1,3})\)")
    matches = pattern.finditer(lines)
    sum = 0
    enabled = True
    for match in matches:
      if match.group(0) in ["do()", "don't()"]:
        enabled = match.group(0) == "do()"
        continue
      if enabled:
        sum += int(match.group(1)) * int(match.group(2))
    print(sum)
