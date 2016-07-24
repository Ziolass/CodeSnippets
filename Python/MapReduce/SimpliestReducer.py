#!/usr/bin/env python
import sys
current_product = None
current_count = 0
product = None
for line in sys.stdin:
	line = line.strip()
	product, count = line.split('\t', 1)
	count = int(count)
	if current_product == product:
		current_count += count
	else:
		if current_product:
			print '%s,%s' % (current_product, current_count)
    current_count = count
    current_product = product
if current_product == product:
	print '%s,%s' % (current_product, current_count)