name := "apache-spark-test"

organization := "com.github.dnvriend"

version := "1.0.0"

scalaVersion := "2.11.8"

fork in Test := true

parallelExecution in Test := false

licenses += ("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))

lazy val jobs = (project in file("jobs"))
  .settings(SbtHeaderConf.settings ++ ScalariformConf.settings ++ commonSettings)
  .dependsOn(datasources)
  .enablePlugins(AutomateHeaderPlugin)

lazy val datasources = (project in file("datasources"))
  .settings(SbtHeaderConf.settings ++ ScalariformConf.settings ++ commonSettings)
  .enablePlugins(AutomateHeaderPlugin)

lazy val helloworld = (project in file("helloworld"))
  .settings(SbtHeaderConf.settings ++ ScalariformConf.settings ++ commonSettings)
  .dependsOn(jobs)
  .enablePlugins(AutomateHeaderPlugin)

lazy val restpi = (project in file("restpi"))
  .settings(SbtHeaderConf.settings ++ ScalariformConf.settings ++ commonSettings)
  .dependsOn(jobs)
  .enablePlugins(AutomateHeaderPlugin)

lazy val commonSettings = Seq(
  parallelExecution in Test := false,
  fork in Test := true,
  libraryDependencies ++= deps
)

lazy val deps = { 
  val AkkaVersion = "2.4.9"
  val SparkVersion = "2.0.0"
  Seq(
      "org.scala-lang" % "scala-reflect" % "2.11.8",
      "org.apache.spark" %% "spark-core" % SparkVersion,
      "org.apache.spark" %% "spark-sql" % SparkVersion,
      "org.apache.spark" %% "spark-mllib" % SparkVersion,
      "com.github.fommil.netlib" % "all" % "1.1.2",
      "org.apache.spark" %% "spark-tags" % SparkVersion,
      "org.apache.spark" %% "spark-streaming" % SparkVersion,
      "org.apache.spark" %% "spark-streaming-kafka-0-10" % SparkVersion,
      "org.apache.bahir" %% "spark-streaming-twitter" % SparkVersion,
      "org.apache.bahir" %% "spark-streaming-akka" % SparkVersion,
      "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-M1",
//      "com.databricks" %% "spark-csv" % "1.4.0", // not necessary for spark v2.0.0
      "com.databricks" %% "spark-xml" % "0.3.3",
      "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http-spray-json-experimental" % AkkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.1.7",
      "org.postgresql" % "postgresql" % "9.4.1209",
      "com.h2database" % "h2" % "1.4.192",
      "com.lihaoyi" %% "pprint" % "0.4.1",
      "net.manub" %% "scalatest-embedded-kafka" % "0.7.1" % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
      "org.scalatest" %% "scalatest" % "2.2.6" % Test
    )
  }