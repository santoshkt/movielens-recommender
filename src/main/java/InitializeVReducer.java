import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class InitializeVReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	private static int noOfCommonFeatures;
	private static float initialValue;

	public void configure(JobConf conf) {
		String var2String = conf.get("noOfCommonFeatures");
		noOfCommonFeatures = Integer.parseInt(var2String);
		var2String = conf.get("INITIAL_VALUE");
		initialValue = Float.parseFloat(var2String);
	}

	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {

		// Input: (movieid, nullList)
		for (int i = 1; i <= noOfCommonFeatures; i++) {

			// Output2: (V featureID, movieID, featureVal)
			output.collect(null, new Text("V " + i + " " + key.toString() + " "
					+ initialValue));

		}
	}
}
