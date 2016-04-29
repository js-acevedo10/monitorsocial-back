package utilidades;

import java.io.File;
import java.io.IOException;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ClassifierMachine {

	String[] mCategories;
	LMClassifier mClassifier;

	public void train() throws IOException, ClassNotFoundException {
		File trainDir;
		String[] categories;
		LMClassifier lmClassifier;
		trainDir = new File("src/machine/trainDirectory");
		categories = trainDir.list();
		int nGram = 7; //the nGram level, any value between 7 and 12 works
		lmClassifier = DynamicLMClassifier.createNGramProcess(categories, nGram);  
		for (int i = 0; i < categories.length; ++i) {
			String category = categories[i];
			Classification classification = new Classification(category);  
			File file = new File(trainDir, categories[i]);
			File[] trainFiles = file.listFiles();
			for (int j = 0; j < trainFiles.length; ++j) {
				File trainFile = trainFiles[j];
				String review = Files.readFromFile(trainFile, "ISO-8859-1");
				Classified classified = new Classified(review, classification);
				((ObjectHandler) lmClassifier).handle(classified);
			}
		}
		AbstractExternalizable.compileTo((Compilable) lmClassifier, new File("src/machine/classifier.txt"));  
	}

	public ClassifierMachine() {  
		try {
			mClassifier = (LMClassifier) AbstractExternalizable.readObject(new File("src/machine/classifier.txt"));  
			mCategories = mClassifier.categories();
		}
		catch (ClassNotFoundException e) {  
			e.printStackTrace();
		}
		catch (IOException e) {  
			e.printStackTrace();  
		}
	}

	public String classify(String text) {
		ConditionalClassification classification = mClassifier.classify(text);  
		return classification.bestCategory();
	}
}
