Handin: Final handin

Design Choices:

First each base case is checked in myID3Algorithm then if it doesn't hit any base cases I find the attribute with the
largest information gain out of the dataset which calls my remainder, entropy, and ratio methods. After the attribute
with the largest information gain is acquired I set it to be the root node, remove it from the attribute list, copy the
new data over, create a subtree by recursively calling myID3Algorithm, attach the subtree to my root node, and finally
return the root node. I decided to make four helper methods to get/check the number of classifications. This made it
very easy for me to access the number of classifications for a given value and dataset, check to see if all
classifications are the same for a dataset, and get the most frequent classification in a dataset ultimately allowing me
to factor out multiple lines of code. I also created separate remainder, entropy and ratio functions allowing me to make
my getLargestInformationGain much smaller and easier to read. My getLargestInformation gain stores the information gain
for each attribute in the dataset by calling my remainder and entropy functions. My remainder function calls my entropy
and ratio functions, and returns the remainder for the given attribute.

Known Bugs:
No known bugs, my tree looks exactly the same for every dataset in the demo and gets the same accuracy score for each
dataset as the demo except for mushrooms where it scores 0.2% higher which is very interesting to me.

Explanation of Test Cases:

entropyTest: Checked for 0 and 1 case, made sure it works for classification counts, and used ints to ensure it's
calculating the correct values

getRatioTest: Test using ints to make sure it's calculating the ratio correctly, test actual classification values to
ensure it works with passed in values in my algorithm

getClassificationCountTest: Test each value for each attribute in the dataset to ensure it's correctly calculating the
classification counts for each attribute

getLargestClassificationCountTest: Check that it's returning the correct classification for each dataset

getTotalClassificationCountTest: Check that it's returning the correct number of positive and negative classification's
for two datasets (didn't want to count num of classifications for larger datasets)

checkClassificationCountTest: Created a new dataset with the same classification to make sure it returns true when it
should, also test with dataset that doesn't have same classification

getRemainderTest: Calculated the remainder by hand and compared my calculations to the actual output

getLargestInformationGainTest: Used training data to test for getLargestInformationGain because I could compare my
result to the demo. Also made sure getLargestInformationGain is returning the same Attribute as the node.
