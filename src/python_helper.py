# this is meant to use python as it was intended by GOD HIMSELF:
# to script.
from os import listdir
import os
from os.path import isfile, join
import subprocess

path = "C:/Users/gabri/OneDrive/Desktop/Team12Compiler/src"

all_files = [f for f in listdir(path + "/phase3testcases")]
for f in all_files:
    string = "java Jott.java phase3testcases/" + f
    print (string)
    print(os.system(string))
    x = input()
