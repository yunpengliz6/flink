package com.atguigu
import org.apache.flink.streaming.api.scala._
object wc {


    def main(args: Array[String]): Unit = {
      val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
      val socketDS: DataStream[String] = env.socketTextStream("hadoop201", 9999)
      val wordDS: DataStream[String] = socketDS.flatMap(_.split(" "))
      val resultDS: DataStream[(String, Int)] = wordDS.map((_,1)).keyBy(0).sum(1)
      resultDS.print("wc")
      env.execute("app")
    }



}
