/**
 * 
 */
package org.adaikiss.xun.hadoop.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author hlw
 *
 */
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    protected static final String TARGET_WORD = "Watson";

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        if (containsTargetWord(key)) {
            int wordCount = 0;
            for (IntWritable value: values) {
                wordCount += value.get();
            }
            context.write(key, new IntWritable(wordCount));
        }
    }

    private boolean containsTargetWord(Text key) {
        return key.toString().equals(TARGET_WORD);
    }
}
