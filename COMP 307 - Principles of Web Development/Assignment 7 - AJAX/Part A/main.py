#!/usr/bin/python

import sys, json, os.path

data = json.load(sys.stdin)

# Create file and initialize json structure if doesn't exists
if not os.path.isfile("user.log"):
    f = open("user.log", "w")
    json.dump({'Users': []}, f)
    f.close()

f = open("user.log", "r")
content = json.load(f)
content["Users"].append(data)
f.close()

f = open("user.log", "w")
f.write(json.dumps(content, indent=4))
f.close()

print('Content-Type: application/json\n\n')
print('The command completed succsesfully')
