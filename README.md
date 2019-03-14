# SolverTree
This project contains two dictionaries represented differently. One dictionary is a HashTable, and the other is a prefix tree.
Then the SolverTree class contains TreeNodes associated with each character that a number can represent.
Numbers to letters key:
2 can be any of the following: a, b, or c
3 can be any of the following: d, e, or f
4 can be any of the following: g, h, or i
5 can be any of the following: j, k, or l
6 can be any of the following: m, n, or o
7 can be any of the following: p, q, r, or s
8 can be any of the following: t, u, or v
9 can be any of the following: w, x, y, or z

Then it prints all possible words twice given the users input.
First it uses the Dictionary Prefix Tree
Then it uses the Dictionary Hash Table

In the future I would like to adapt this project to utilize the Prefix tree to check if a sequence of characters is a prefix of any words in the dictionary. By doing this I could eliminate the need to add more nodes to the tree that represent words that I already know don't exist.
In this way I could decrease the time complexity of this project, because as it is, utilizing this solver tree to determine the possible words given user input is unrealistic given that it's time complexity is O(4^n)
