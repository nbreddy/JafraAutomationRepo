javac -classpath .\ExternalJars\* .\src\com\cognizant\config\*.java .\src\com\cognizant\library\*.java .\src\com\cognizant\pom\EcomPages\*.java .\src\com\cognizant\report\*.java .\src\com\cognizant\support\*.java .\src\com\cognizant\util\*.java .\src\com\cognizant\executor\SuiteRunner.java

java -cp bin;ExternalJars/* com.cognizant.executor.SuiteRunner