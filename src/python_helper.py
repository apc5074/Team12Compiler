# this is meant to use python as it was intended by GOD HIMSELF:
# to script.
from os import listdir
import os
from os.path import isfile, join
import subprocess

os.system("javac ./parser
          Nodes/*.java")
os.system("javac ./helpers/*.java")

all_files = [f for f in listdir("./phase3testcases")]
for f in all_files:
    string = "java Jott.java phase3testcases/" + f
    print (string)
    print(os.system(string))
    x = input()
