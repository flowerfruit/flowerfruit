package com.mengxiang.test001.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class Test01 {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setMaster("local").setAppName("logistics");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile("./words",3);
		JavaRDD<String> mapPartitionsWithIndex = lines.mapPartitionsWithIndex(new Function2<Integer, Iterator<String>, Iterator<String>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<String> call(Integer index, Iterator<String> iter)
					throws Exception {
				List<String> list = new ArrayList<String>();
				while(iter.hasNext()){
					String s = iter.next();
					list.add(s+"~");
					System.out.println("partition id is "+index +",value is "+s );
				}
				return list.iterator();
			}
		}, true);
		
		
		mapPartitionsWithIndex.collect();
		
		JavaRDD<String> word=lines.flatMap(new FlatMapFunction<String, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> call(String words) throws Exception {
				
				return Arrays.asList(words.split(" "));
			}
		});
		 JavaPairRDD<String, Integer>  wordPair=word.mapToPair(new PairFunction<String, String, Integer>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<String, Integer> call(String word) throws Exception {
				return new Tuple2<String, Integer>(word, 1);
			}
		});
		 JavaPairRDD<String, Integer> wordcount=wordPair.aggregateByKey(0, new Function2<Integer,Integer,Integer>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer t1, Integer t2) throws Exception {
				return t1+t2;
			}}, new Function2<Integer,Integer,Integer>(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Integer call(Integer t3, Integer t4)
						throws Exception {
					return t3+t4;
				}});
		 JavaPairRDD<Integer, String> changePosition= wordcount.mapToPair(new PairFunction<Tuple2<String,Integer>, Integer, String>() {

			/**
			 * 
			 */
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
			
		}
		sc.stop();
	}
}
