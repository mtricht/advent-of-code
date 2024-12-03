def is_safe(report):
    steps = [
        int(report[index + 1]) - int(report[index]) for index in range(len(report) - 1)
    ]
    return all(0 < step <= 3 for step in steps) or all(-3 <= step < 0 for step in steps)


def count_safe_reports(lines):
    return [is_safe(line.split()) for line in lines].count(True)


def count_safe_reports_with_dampener(lines):
    count = 0
    for line in lines:
      report = line.split()
      if is_safe(report):
        count += 1
        continue
      for index in range(len(report)):
         potential_safe_report = report[:index] + report[index + 1:]
         if (is_safe(potential_safe_report)):
            count += 1
            break
    return count


with open("input") as f:
    lines = f.read().splitlines()
    print("total safe reports: {}".format(count_safe_reports(lines)))
    print("total safe reports with the dampener: {}".format(count_safe_reports_with_dampener(lines)))
