package decisiontree;

import org.junit.Test;
import support.decisiontree.DataReader;
import support.decisiontree.DecisionTreeData;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * This class can be used to test the functionality of your MyID3 implementation.
 * Use the Heap stencil and your heap tests as a guide!
 * 
 */

public class MyID3Test {
	
	@Test
	public void simpleTest() {

	    
	    MyID3 id3 = new MyID3();

	    // This creates a DecisionTreeData object that you can use for testing.
	    DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");
	    // FILL
	    
	}

	/*
	Check the entropy for given input to ensure it's computing entropy correctly
	 */
	@Test
	public void entropyTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");

		//If all classifications are the same entropy should be 0, log2(0) case
		assertThat(myID3.getEntropy(new int[]{10, 0}), is(-0.0));

		//Other log2(0) case
		assertThat(myID3.getEntropy(new int[]{0, 10}), is(-0.0));

		//Perfectly split amount of classifications should return entropy of 0.5
		assertThat(myID3.getEntropy(new int[]{10, 10}), is(1.0));

		//Check that it works for whole data set
		assertThat(myID3.getEntropy(myID3.getTotalClassificationCount(shortData)), is(myID3.getEntropy(new int[]{3, 1})));

		//Check that it works for a given value
		assertThat(myID3.getEntropy(myID3.getClassificationCount("Yes", shortData, 0)), is(-0.0));
	}

	/*
	Test getRatio by passing in integers and classification counts
	 */
	@Test
	public void getRatioTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");

		//Baseline ratio tests
		assertThat(myID3.getRatio(new int[]{2,0}, shortData.getExamples()), is(0.5));
		assertThat(myID3.getRatio(new int[]{1,0}, shortData.getExamples()), is(0.25));
		assertThat(myID3.getRatio(new int[]{0,3}, shortData.getExamples()), is(0.75));
		assertThat(myID3.getRatio(new int[]{2,2}, shortData.getExamples()), is(1.0));

		//The ratio of the classification count to the examples should be 1
		assertThat(myID3.getRatio(myID3.getTotalClassificationCount(shortData), shortData.getExamples()), is(1.0));

		//Value "No" for attribute Alt has 3 total classifications, ratio should be 3/4
		assertThat(myID3.getRatio(myID3.getClassificationCount("No", shortData, 0), shortData.getExamples()), is(0.75));

	}

	/*
	Check that we are getting the correct classification count by testing method on each attribute in short-data
	 */
	@Test
	public void getClassificationCountTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");

		//Check classification counts for each attribute's value

		//Alt values
		System.out.println(myID3.getClassificationCount("Yes", shortData, 1)[0]);
		assertThat(myID3.getClassificationCount("No", shortData, 0), is(new int[]{2,1}));
		assertThat(myID3.getClassificationCount("Yes", shortData, 0), is(new int[]{1,0}));

		//Bar values
		assertThat(myID3.getClassificationCount(" No", shortData, 1), is(new int[]{0,1}));
		assertThat(myID3.getClassificationCount(" Yes", shortData, 1), is(new int[]{3,0}));

		//Fri values
		assertThat(myID3.getClassificationCount(" No", shortData, 2), is(new int[]{1,1}));
		assertThat(myID3.getClassificationCount(" Yes", shortData, 2), is(new int[]{2,0}));

		//Hun values
		assertThat(myID3.getClassificationCount(" No", shortData, 3), is(new int[]{2,0}));
		assertThat(myID3.getClassificationCount(" Yes", shortData, 3), is(new int[]{1,1}));

		//Pat values
		assertThat(myID3.getClassificationCount(" None", shortData, 4), is(new int[]{1,0}));
		assertThat(myID3.getClassificationCount(" Some", shortData, 4), is(new int[]{0,1}));
		assertThat(myID3.getClassificationCount(" Full", shortData, 4), is(new int[]{2,0}));

		//Price values
		assertThat(myID3.getClassificationCount(" $", shortData, 5), is(new int[]{2,0}));
		assertThat(myID3.getClassificationCount(" $$", shortData, 5), is(new int[]{0,1}));
		assertThat(myID3.getClassificationCount(" $$$", shortData, 5), is(new int[]{1,0}));

		//Rain values
		assertThat(myID3.getClassificationCount(" No", shortData, 6), is(new int[]{1,0}));
		assertThat(myID3.getClassificationCount(" Yes", shortData, 6), is(new int[]{2,1}));

		//Res Values
		assertThat(myID3.getClassificationCount(" No", shortData, 7), is(new int[]{2,0}));
		assertThat(myID3.getClassificationCount(" Yes", shortData, 7), is(new int[]{1,1}));

		//Type values
		assertThat(myID3.getClassificationCount(" Burger", shortData, 8), is(new int[]{2,0}));
		assertThat(myID3.getClassificationCount(" Thai", shortData, 8), is(new int[]{0,1}));
		assertThat(myID3.getClassificationCount(" Italian", shortData, 8), is(new int[]{1,0}));

		//Est values
		assertThat(myID3.getClassificationCount(" 0-10", shortData, 9), is(new int[]{1,1}));
		assertThat(myID3.getClassificationCount(" >60", shortData, 9), is(new int[]{1,0}));
		assertThat(myID3.getClassificationCount(" 10-30", shortData, 9), is(new int[]{1,0}));
	}

	/*
	Test by calling getLargestClassificationCount on different datasets and comparing it to expected value
	 */
	@Test
	public void getLargestClassificationCountTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");
		DecisionTreeData villainData = DataReader.readFile("src/decisiontree/decisiontree-data/villain-testing.csv");
		DecisionTreeData resumeData = DataReader.readFile("src/decisiontree/decisiontree-data/resume-testing.csv");
		DecisionTreeData mushroomData = DataReader.readFile("src/decisiontree/decisiontree-data/mushrooms-testing.csv");

		//Check that largest classification count is returning the most frequent classification for each data set
		assertThat(myID3.getLargestClassificationCount(shortData), is(" false"));
		assertThat(myID3.getLargestClassificationCount(villainData), is("hero"));
		assertThat(myID3.getLargestClassificationCount(resumeData), is("N"));
		assertThat(myID3.getLargestClassificationCount(mushroomData), is("p"));
	}

	/*
	Manually count number of positive and negative classifications in datasets and compare to getTotalClassificationCount
	result
	 */
	@Test
	public void getTotalClassificationCountTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");
		DecisionTreeData villainData = DataReader.readFile("src/decisiontree/decisiontree-data/villain-testing.csv");

		//Check that largest classification count is returning the most frequent classification for each data set
		assertThat(myID3.getTotalClassificationCount(shortData), is(new int[]{3,1}));
		assertThat(myID3.getTotalClassificationCount(villainData), is(new int[]{53, 10}));
	}

	/*
	Created a new dataset that has the same classification for every value to ensure that checkClassificationCount
	is returning true when every classification is the same.
	 */
	@Test
	public void checkClassificationCountTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");
		DecisionTreeData checkClassificationData = DataReader.readFile("src/decisiontree/decisiontree-data/check-classifications-testing.csv");

		//Data with different classifications
		assertThat(myID3.checkClassifications(shortData), is(false));

		//Data with same classifications
		assertThat(myID3.checkClassifications(checkClassificationData), is(true));

	}

	/*
	Calculated remainder for the first couple attributes in short-data-testing by hand and compared result to output of
	getRemainder
	 */
	@Test
	public void getRemainderTest(){
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-testing.csv");

		//Calculated remainder by hand
		assertThat(myID3.getRemainder(shortData, shortData.getAttributeList().get(0)), is(0.6887218755408672));
		assertThat(myID3.getRemainder(shortData, shortData.getAttributeList().get(1)), is(0.0));
		assertThat(myID3.getRemainder(shortData, shortData.getAttributeList().get(2)), is(0.5));

	}

	/*
	Check to see if getLargestInformationGain returning the same attribute as the rootNode. Also use training data to
	compare result from getLargestInformationGain to demo result.
	 */
	@Test
	public void getLargestInformationGainTest() {
		MyID3 myID3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");
		DecisionTreeData villainData = DataReader.readFile("src/decisiontree/decisiontree-data/villain-training.csv");
		DecisionTreeData resumeData = DataReader.readFile("src/decisiontree/decisiontree-data/resume-training.csv");
		DecisionTreeData mushroomData = DataReader.readFile("src/decisiontree/decisiontree-data/mushrooms-training.csv");

		//Check to see if getLargestInformationGain is returning same attribute as demo
		assertThat(myID3.getLargestInformationGain(shortData).getName(), is(" Pat"));
		assertThat(myID3.getLargestInformationGain(villainData).getName(), is("chipotle"));
		assertThat(myID3.getLargestInformationGain(resumeData).getName(), is("Fraternity"));
		assertThat(myID3.getLargestInformationGain(mushroomData).getName(), is("odor"));

		//Check to see if getLargestInformationGain is returning same attribute as root node
		assertThat(myID3.getLargestInformationGain(shortData).getName(), is(myID3.id3Trigger(shortData).getElement()));
		assertThat(myID3.getLargestInformationGain(villainData).getName(), is(myID3.id3Trigger(villainData).getElement()));
		assertThat(myID3.getLargestInformationGain(resumeData).getName(), is(myID3.id3Trigger(resumeData).getElement()));
		assertThat(myID3.getLargestInformationGain(mushroomData).getName(), is(myID3.id3Trigger(mushroomData).getElement()));

	}


}