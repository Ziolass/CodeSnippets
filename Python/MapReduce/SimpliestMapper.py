#!/usr/bin/env python
import sys
for line in sys.stdin:
	array = line.strip().split(';')    
	print '%s\t%s' % (array[1], 1)