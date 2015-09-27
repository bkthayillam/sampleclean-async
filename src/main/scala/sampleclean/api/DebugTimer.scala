package sampleclean.api

import java.util.Calendar
import java.text.SimpleDateFormat
import scala.collection.mutable.LinkedList

/**
 * Provides an information gathering tool to find 
 * out the different timestamps at which different 
 * parts of the code are run
 *
 */
class DebugTimer() extends Serializable{
	var records = List[InfoTrackerNode]();
	

	def record(name:String):InfoTrackerNode = {
		val temp:InfoTrackerNode = new InfoTrackerNode(name);
		records ::= temp;
		temp;
	}

	override def toString():String = {
		var outString:String = "\n";
		var x:InfoTrackerNode = new InfoTrackerNode("Null");
		for( x <- records.reverse ){
			outString += "[name: " + x.codePoint + ", duration: "+ x.duration()  + "]\n";
		}
		outString
	}
	
}

class InfoTrackerNode(name: String) extends Serializable{
	val codePoint:String = name;
	var startTime:java.util.Date = _;
	var endTime:java.util.Date = _;
	var cal:Calendar = _;

	def logStart() {
		cal = Calendar.getInstance()
		startTime = cal.getTime()
	}

	def logEnd() {
		cal = Calendar.getInstance()
		endTime = cal.getTime()
	}

	def duration():Long = {
		(endTime.getTime() - startTime.getTime())/1000
	}
	
}
