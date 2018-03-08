package com.mengxiang.test001.job;


import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;


public class test002_fenci {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setMaster("local").setAppName("logistics");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile("./words2",3);
		
		/*
		 * 分词
		 */
	    //分词准备
		StopRecognition stop = new StopRecognition();
	    stop.insertStopNatures("w");//过滤掉标点
	    stop.insertStopNatures("m");//过滤掉m词性
	    stop.insertStopNatures("null");//过滤null词性
	    stop.insertStopNatures("<br />");//过滤<br　/>词性
	    stop.insertStopNatures(":");
	    stop.insertStopNatures("'");
	    
	   lines.map(new Function<String, String>() {

		private static final long serialVersionUID = 1L;

		@Override
		public String call(String words) throws Exception {
			//使用分词器
			String ff= DicAnalysis.parse(words).recognition(stop).toStringWithOutNature("|");
			return ff;
		}
	}).foreach(new VoidFunction<String>() {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void call(String line) throws Exception {
			System.out.println(line);
		}
	});
	   
	   
	    
	    
	   
		
		/*
		
		
		JavaRDD<String> word=lines.flatMap(new FlatMapFunction<String, String>() {

			*//**
			 * 
			 *//*
			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> call(String words) throws Exception {
				
				
				
				
				
				return Arrays.asList(words.split(" "));
			}
		});
		 JavaPairRDD<String, Integer>  wordPair=word.mapToPair(new PairFunction<String, String, Integer>() {

			*//**
			 * 
			 *//*
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<String, Integer> call(String word) throws Exception {
				return new Tuple2<String, Integer>(word, 1);
			}
		});
		 JavaPairRDD<String, Integer> wordcount=wordPair.aggregateByKey(0, new Function2<Integer,Integer,Integer>(){

			*//**
			 * 
			 *//*
			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer t1, Integer t2) throws Exception {
				return t1+t2;
			}}, new Function2<Integer,Integer,Integer>(){

				*//**
				 * 
				 *//*
				private static final long serialVersionUID = 1L;

				@Override
				public Integer call(Integer t3, Integer t4)
						throws Exception {
					return t3+t4;
				}});
		 JavaPairRDD<Integer, String> changePosition= wordcount.mapToPair(new PairFunction<Tuple2<String,Integer>, Integer, String>() {

			*//**
			 * 
			 *//*
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<Integer, String> call(Tuple2<String, Integer> pair)
					throws Exception {
				return new Tuple2<Integer, String>(pair._2,pair._1);
			}
		});
		 List<Tuple2<Integer, String>> list= changePosition.sortByKey(false).take(3);
		 for (Tuple2<Integer, String> tuple2 : list) {
			System.out.println(tuple2._2+"-----"+tuple2._1);
			
		}*/
		sc.stop();
	}
	
}
