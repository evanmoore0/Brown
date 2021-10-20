package decisiontree;

import support.decisiontree.Attribute;
import support.decisiontree.DecisionTreeData;
import support.decisiontree.DecisionTreeNode;
import support.decisiontree.ID3;
import java.util.ArrayList;


/**
  * This class implements my id3 algorithm which creates a decision tree based on training data
  */
public class MyID3 implements ID3 {

    public MyID3() {
    }

    @Override
    public DecisionTreeNode id3Trigger(DecisionTreeData data) {

        return myID3Algorithm(data, null);
    }

    /*
        * Recursively creates decision tree based on importance values
     */
    private DecisionTreeNode myID3Algorithm(DecisionTreeData data, DecisionTreeData parentData) {
        //Node to be added to tree
        DecisionTreeNode rootNode = new DecisionTreeNode();

        //First base case, if the data is empty create a new node with most frequent classification in parent data
        if (data.getExamples().length == 0) {
            rootNode = new DecisionTreeNode();
            rootNode.setElement(getLargestClassificationCount(parentData));
            return rootNode;

            //Second base case, if attributes is empty return new node with most frequent classification in data
        } else if (data.getAttributeList().size() == 0) {
            rootNode = new DecisionTreeNode();
            rootNode.setElement(getLargestClassificationCount(data));
            return rootNode;

            //Third base case, if all examples in data have the same classification return new node with
            // that classification
        } else if (checkClassifications(data) == true) {
            rootNode = new DecisionTreeNode();
            rootNode.setElement(getLargestClassificationCount(data));
            return rootNode;

            //Otherwise get the attribute with the largest information gain, remove it from the list of attributes,
            //create a new data set using the attributes values, create a subtree using recursion and add to tree
        } else {

            //Attribute with largest information gain
            Attribute largestInfoGain = getLargestInformationGain(data);

            //Root node
            rootNode.setElement(largestInfoGain.getName());
            ArrayList<Attribute> attributeList = data.getAttributeList();

            //Remove the attribute
            attributeList.remove(largestInfoGain);

            //Create new data set
            for(String value: largestInfoGain.getValues()) {
                int[] classificationSize = getClassificationCount(value, data, largestInfoGain.getColumn());
                String[][] new_data = new String[classificationSize[0] + classificationSize[1]][attributeList.size()];

                int i = 0;
                for(String[] example: data.getExamples()) {
                    if(example[largestInfoGain.getColumn()].equals(value)) {
                        new_data[i] = example;
                        i++;
                    }
                }

                //Add child to tree and recursively call algorithm
                DecisionTreeData new_data_decision = new DecisionTreeData(new_data, attributeList, data.getClassifications());
                DecisionTreeNode subtree = myID3Algorithm(new_data_decision, data);
                rootNode.addChild(value, subtree);
            }
            return rootNode;
        }
    }

    //Returns the attribute with the largest information gain
    public Attribute getLargestInformationGain(DecisionTreeData data) {
        int[] totalClassificationCount = getTotalClassificationCount(data);
        double[] infoGain = new double[data.getAttributeList().size()];

        //For each attribute find the information gain
        for(int i = 0; i < data.getAttributeList().size(); i++) {
            infoGain[i] = getEntropy(totalClassificationCount) - getRemainder(data, data.getAttributeList().get(i));
        }

        //Finds largest information gain out of attributes
        double maxInfo = infoGain[0];
        int index = 0;
        for(int i = 0; i < infoGain.length; i++) {
            if(maxInfo < infoGain[i]) {
                index = i;
                maxInfo = infoGain[i];
            }
        }
        return data.getAttributeList().get(index);

    }

    //Returns the ratio of total classifications for a value to total classifications in examples
    public double getRatio(int[] classificationCount, String[][] examples) {

        return (classificationCount[0] + classificationCount[1])/(double)examples.length;
    }

    //Calculates the entropy for a given classification count (value or whole data set)
    public double getEntropy(int[] classificationCount) {
        double q = (double) classificationCount[0]/(classificationCount[0] + classificationCount[1]);

        double firstPart = (q * (Math.log(q)/Math.log(2)));
        double secondPart = ((1-q)*((Math.log(1-q))/Math.log(2)));

        if (q == 1) {
            return - firstPart;
        } else if(q == 0) {
            return -secondPart;
        }

        return  -(firstPart + secondPart);
    }

    //Returns the remainder for a given attribute
    public double getRemainder(DecisionTreeData data, Attribute attribute) {
        double remainder = 0;

        //For each value within the given attribute increment remainder by ratio of classifications and entropy of value
        //classification count
        for(String value: attribute.getValues()) {
            int[] classificationCount = getClassificationCount(value, data, attribute.getColumn());
            if(classificationCount[0] + classificationCount[1] == 0) {
                continue;
            }
            remainder += getRatio(classificationCount, data.getExamples()) * getEntropy(classificationCount);

        }
        return remainder;
    }

    //Returns the largest classification in the data set
    public String getLargestClassificationCount(DecisionTreeData data) {
        int[] totalClassificationCount = getTotalClassificationCount(data);

        if(totalClassificationCount[0] > totalClassificationCount[1]) {
            return data.getClassifications()[0];
        }
        return data.getClassifications()[1];
    }

    //Returns the total number of classifications
    public int[] getTotalClassificationCount(DecisionTreeData data) {
        int[] totalClassificationCount = new int[2];
        for(String[] example: data.getExamples()) {
            if(example[example.length-1].equals(data.getClassifications()[0])) {
                totalClassificationCount[0] +=1;
            } else {
                totalClassificationCount[1] +=1;
            }
        }
        return totalClassificationCount;
    }

    //Returns the number of positive and negative classifications for a given value and returns it in an array
    public int[] getClassificationCount(String value, DecisionTreeData data, int attributeColumn) {
        int[] valueClassificationCount = new int[2];
        for(String[] example: data.getExamples()) {
            if(value.equals(example[attributeColumn]) && example[example.length-1].equals(data.getClassifications()[0])) {
                valueClassificationCount[0] +=1;
            } else if(value.equals(example[attributeColumn])) {
                valueClassificationCount[1] +=1;
            }
        }
        return valueClassificationCount;
    }

    //Returns true if all classifications in examples are the same
    public Boolean checkClassifications(DecisionTreeData data) {
        String previousClassification = data.getExamples()[0][0];
        for(String[] example: data.getExamples()) {
            if(previousClassification.equals(data.getExamples()[0][0])) {
                previousClassification = example[example.length-1];
                continue;
            }else if(example[example.length-1].equals(previousClassification)) {
                previousClassification = example[example.length-1];
                continue;
            }
            return false;
        }
        return true;
    }
}
